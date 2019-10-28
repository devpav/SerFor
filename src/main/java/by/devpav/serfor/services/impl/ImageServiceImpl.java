package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.VirtualDirectory;
import by.devpav.serfor.exceptions.EntityNotFoundException;
import by.devpav.serfor.exceptions.ImageNotFoundException;
import by.devpav.serfor.exceptions.ObjectThrow;
import by.devpav.serfor.repository.ImageRepository;
import by.devpav.serfor.services.ImageService;
import by.devpav.serfor.services.RealmService;
import by.devpav.serfor.services.VirtualDirectoryService;
import by.devpav.serfor.services.directories.SerForDirectoryManager;
import by.devpav.serfor.services.files.SerForFileManager;
import by.devpav.serfor.services.files.SerForImage;
import by.devpav.serfor.services.impl.image.ImageLoader;
import by.devpav.serfor.services.impl.image.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ImageServiceImpl extends AbstractBasicEntityService<Image> implements ImageService {

    @Autowired
    private ImageUploader imageUploader;
    @Autowired
    private RealmService realmService;
    @Autowired
    private VirtualDirectoryService virtualDirectoryService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private SerForDirectoryManager serForDirectoryManager;
    @Autowired
    private SerForFileManager serForFileManager;
    @Autowired
    private ImageLoader imageLoader;

    public ImageServiceImpl(ImageRepository imageRepository) {
        super(imageRepository);
    }

    @Override
    public Image create(Image entity) {
        return super.create(entity);
    }

    @Override
    @Transactional
    public Image uploadOriginalImage(MultipartFile multipartFile, String realm) {
        ObjectThrow.requireNotNullThrow(multipartFile, "MultipartFile mustn't be is null");
        ObjectThrow.requireNotNullThrow(realm, "Realm mustn't be is null");

        final Realm foundRealm = realmService.findRealmByName(realm);

        if (isNull(foundRealm)) {
            throw new EntityNotFoundException("Realm with name " + realm + " not found");
        }

        final SerForImage uploadedImage = imageUploader.upload(multipartFile);

        final Image originalImage = new Image(
                uploadedImage.getOriginName(),
                uploadedImage.getVirtualName(),
                uploadedImage.getSize()
        );

        final Integer width = uploadedImage.getWidth();
        final Integer height = uploadedImage.getHeight();

        VirtualDirectory virtualDirectory = virtualDirectoryService.findVirtualDirectory(realm, width, height);

        if (isNull(virtualDirectory)) {
            final String nameVDirectory = width + "_x_" + height;
            virtualDirectory = virtualDirectoryService.buildDirectory(nameVDirectory, width, height, foundRealm);
        }

        originalImage.setVirtualDirectory(virtualDirectory);
        originalImage.setParentImage(null);

        return imageRepository.save(originalImage);
    }

    @Transactional
    @Override
    public Image getResizedImage(String realm, String originalNameImage, Integer width, Integer height) {
        ObjectThrow.requireNotNullThrow(realm, "Realm name mustn't be is null");
        ObjectThrow.requireNotNullThrow(realm, "Image name mustn't be is null");

        final Realm realmByName = realmService.findRealmByName(realm);
        ObjectThrow.requireNotNullThrow(realmByName, "Realm mustn't be is null");

        final Image image = imageRepository.findByVirtualName(originalNameImage);

        if (isNull(image)) {
            throw new ImageNotFoundException("Image not found by original name");
        }

        if (nonNull(image.getParentImage())) {
            throw new ImageNotFoundException("Image isn't original image");
        }

        VirtualDirectory virtualDirectory = virtualDirectoryService.findVirtualDirectory(realm, width, height);

        if (isNull(virtualDirectory)) {
            final String nameVDirectory = width + "_x_" + height;
            virtualDirectory = virtualDirectoryService.buildDirectory(nameVDirectory, width, height, realmByName);
        }

        if (isNull(virtualDirectory.getImages())) {
            virtualDirectory.setImages(new HashSet<>());
            // throw new ImageNotFoundException("Image Set not found inside virtual directory");
        }

        final Image imageFromVirtualDirectory = virtualDirectory.getImages().stream()
                .filter(item -> nonNull(item.getId()))
                .filter(item -> item.getId().equals(image.getId()))
                .findFirst()
                .orElse(null);

        if (nonNull(imageFromVirtualDirectory)) {
            final String virtualName = imageFromVirtualDirectory.getVirtualName();

            if (!serForFileManager.isExists(virtualName, serForDirectoryManager.getSerForFolder())) {
                throw new ImageNotFoundException("Image not found on the disk");
            }

            return imageFromVirtualDirectory;
        }

        final SerForImage serForImage = imageUploader.resizeAndUpload(originalNameImage, width, height);

        final Image resizedName = new Image(
                serForImage.getOriginName(),
                serForImage.getVirtualName(),
                serForImage.getSize(),
                virtualDirectory,
                image
        );

        return imageRepository.save(resizedName);
    }

    @Override
    public Resource loadImageByName(String imageName, String realm, String vdir) {
        final VirtualDirectory virtualDirectory = virtualDirectoryService.findByName(vdir);
        if (isNull(virtualDirectory)) {
            throw new EntityNotFoundException("Virtual directory not found");
        }

        if (isNull(virtualDirectory.getImages())) {
            throw new EntityNotFoundException("Image not found inside virtual directory by name [" + vdir + "]");
        }

        final Image foundImage = virtualDirectory.getImages().stream()
                .filter(image -> image.getOriginalName().equals(imageName))
                .findFirst()
                .orElseThrow(() ->
                        new EntityNotFoundException("Image not found inside virtual directory by name [" + vdir + "]")
                );

        return imageLoader.loadImage(foundImage.getVirtualName());
    }

}
