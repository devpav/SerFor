package by.devpav.serfor.service


import by.devpav.serfor.domain.Image
import by.devpav.serfor.exceptions.EntityNotFoundException
import by.devpav.serfor.exceptions.ImageNotFoundException
import by.devpav.serfor.exceptions.ObjectThrow
import by.devpav.serfor.exceptions.image.ImageContainsBadData
import by.devpav.serfor.repository.ImageRepository
import by.devpav.serfor.services.ImageService
import by.devpav.serfor.services.RealmService
import by.devpav.serfor.services.VirtualDirectoryService
import by.devpav.serfor.services.directories.SerForDirectoryManager
import by.devpav.serfor.services.files.SerForFileManager
import by.devpav.serfor.services.impl.AbstractBasicEntityService
import by.devpav.serfor.services.impl.image.ImageLoader
import by.devpav.serfor.services.impl.image.ImageUploader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
open class ImageServiceImpl(imageRepository: ImageRepository) : AbstractBasicEntityService<Image>(imageRepository),
        ImageService {

    @Autowired
    private lateinit var imageUploader: ImageUploader
    @Autowired
    private lateinit var realmService: RealmService
    @Autowired
    private lateinit var virtualDirectoryService: VirtualDirectoryService
    @Autowired
    private lateinit var imageRepository: ImageRepository
    @Autowired
    private lateinit var serForDirectoryManager: SerForDirectoryManager
    @Autowired
    private lateinit var serForFileManager: SerForFileManager
    @Autowired
    private lateinit var imageLoader: ImageLoader


    @Transactional
    override fun uploadOriginalImage(multipartFile: MultipartFile, realm: String): Image {
        ObjectThrow.requireNotNullThrow(multipartFile, "MultipartFile mustn't be is null")
        ObjectThrow.requireNotNullThrow(realm, "Realm mustn't be is null")

        val foundRealm = realmService.findRealmByName(realm)

        foundRealm ?: throw EntityNotFoundException("Realm with name $realm not found")

        val uploadedImage = imageUploader.upload(multipartFile)

        val originalImage = Image(uploadedImage.originName, uploadedImage.virtualName, uploadedImage.size)

        val width = uploadedImage.width
        val height = uploadedImage.height

        var virtualDirectory = virtualDirectoryService.findVirtualDirectory(realm, width, height)
        virtualDirectory = virtualDirectory
                ?: virtualDirectoryService.buildDirectory("${width}_x_${height}", width, height, foundRealm)

        originalImage.virtualDirectory = virtualDirectory
        originalImage.parentImage = null

        return imageRepository.save(originalImage)
    }

    @Transactional
    override fun getResizedImage(realm: String, originalNameImage: String, width: Int?, height: Int?): Image {
        ObjectThrow.requireNotNullThrow(realm, "Realm name mustn't be is null")
        ObjectThrow.requireNotNullThrow(realm, "Image name mustn't be is null")

        val realmByName = realmService.findRealmByName(realm)
        ObjectThrow.requireNotNullThrow(realmByName, "Realm mustn't be is null")

        val image = imageRepository.findByVirtualName(originalNameImage)
                ?: throw ImageNotFoundException("Image not found by original name")

        if (image.parentImage == null)
            throw ImageNotFoundException("Image isn't original image")

        var virtualDirectory = virtualDirectoryService.findVirtualDirectory(realm, width, height)

        virtualDirectory = virtualDirectory
                ?: virtualDirectoryService.buildDirectory("${width}_x_${height}", width, height, realmByName)

        virtualDirectory.images = virtualDirectory.images ?: HashSet()

        val foundImage: Image? = virtualDirectory.images!!
                .filter { it.id != null }
                .find { it.id == image.id }

        if (foundImage != null) {
            val virtualName = foundImage.virtualName
            val existsPhysicalFile = serForFileManager.isExists(virtualName, serForDirectoryManager.serForFolder)
            return if (existsPhysicalFile) foundImage else throw ImageNotFoundException("Image not found on the disk")
        }

        val serForImage = imageUploader.resizeAndUpload(originalNameImage, width, height)

        val resizedName =
                Image(serForImage.originName, serForImage.virtualName, serForImage.size, virtualDirectory, image)

        return imageRepository.save(resizedName)
    }

    override fun loadImageByName(imageName: String, realm: String, vdir: String): Resource {
        val virtualDirectory = virtualDirectoryService.findByName(vdir)

        virtualDirectory ?: throw EntityNotFoundException("Virtual directory not found")

        val entityNotFoundException =
                EntityNotFoundException("Image not found inside virtual directory by name [$vdir]")

        virtualDirectory.images ?: throw entityNotFoundException

        val image: Image = virtualDirectory.images!!.find { it.originalName == imageName } ?:
                throw EntityNotFoundException("Image not found inside virtual directory by name [$vdir]")

        val virtualName = image.virtualName ?: throw ImageContainsBadData("Image contains virtual name is null")
        val realmImagePath = serForDirectoryManager.getRealmDirectory(realm).resolve(virtualName)

        return imageLoader.loadImage(realmImagePath)
    }

}
