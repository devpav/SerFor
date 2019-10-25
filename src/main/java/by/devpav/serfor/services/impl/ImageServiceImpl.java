package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.VirtualDirectory;
import by.devpav.serfor.exceptions.ImageNotFoundException;
import by.devpav.serfor.exceptions.ObjectThrow;
import by.devpav.serfor.factory.DirectoryFactory;
import by.devpav.serfor.factory.ImageFactory;
import by.devpav.serfor.repository.ImageRepository;
import by.devpav.serfor.services.ImageService;
import by.devpav.serfor.services.ImageUploader;
import by.devpav.serfor.services.RealmService;
import by.devpav.serfor.services.VirtualDirectoryService;
import by.devpav.serfor.services.impl.image.ImageResizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    private DirectoryFactory directoryFactory;
    @Autowired
    private ImageFactory imageFactory;
    @Autowired
    private ImageResizer imageResizer;

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

        final String originalFilename = multipartFile.getOriginalFilename();

        byte[] bytes = new byte[]{};
        try {
            bytes = multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectThrow.requireNotNullThrow(bytes, "Data mustn't be is null");

        final Realm realmEntity = realmService.findRealmByName(realm);

        ObjectThrow.requireRealmExists(realmEntity, "Realm [" + realm + "] not found");

        VirtualDirectory originFolder = null;

        if (nonNull(realmEntity.getRealmConfig())) {
            final String realmVirtualDirectory = realmEntity.getRealmConfig().getRealmVirtualDirectory();
            originFolder = virtualDirectoryService.findByName(realmVirtualDirectory);
        }

        if (isNull(originFolder)) {
            final String originalFolderName = "origin_folder";
            originFolder = virtualDirectoryService.findByName(originalFolderName);

            if (isNull(originFolder)) {
                final Path resolve = Paths.get(System.getProperty("user.home")).resolve(originalFolderName);
                if (!resolve.toFile().exists()) {
                    try {
                        Files.createDirectory(resolve);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            originFolder = directoryFactory.create(originalFolderName, realmEntity, true);
            originFolder = virtualDirectoryService.create(originFolder);
            realmEntity.getDirectories().add(originFolder);

            realmService.update(realmEntity);
        }

        final String uploadFileName = imageUploader.upload(originalFilename, originFolder.getName(), bytes);
        final Image image = imageFactory.create(uploadFileName, (long) bytes.length, originFolder);

        return imageRepository.save(image);
    }

    @Transactional
    public Image getResizedImage(String realm, String originalNameImage, Integer width, Integer height) {
        ObjectThrow.requireNotNullThrow(realm, "Realm name mustn't be is null");
        ObjectThrow.requireNotNullThrow(realm, "Image name mustn't be is null");

        final Realm realmByName = realmService.findRealmByName(realm);
        ObjectThrow.requireNotNullThrow(realmByName, "Realm mustn't be is null");

        final Image image = imageRepository.findByVirtualName(originalNameImage);

        if (isNull(image)) {
            throw new ImageNotFoundException("Image not found by original name");
        }

        if (!image.getOriginName().equals(image.getVirtualName())) {
            throw new ImageNotFoundException("Image not found by original name");
        }

        VirtualDirectory virtualDirectory = virtualDirectoryService.findVirtualDirectory(realm, width, height);

        if (isNull(virtualDirectory)) {
            final String nameVirtualDirectory = width + "_x_" + height;
            virtualDirectory = new VirtualDirectory(nameVirtualDirectory, width, height);
            virtualDirectory = virtualDirectoryService.create(virtualDirectory);
        }

        if (isNull(virtualDirectory.getImages())) {
            throw new ImageNotFoundException("Image Set not found inside virtual directory");
        }

        final Image imageFromVirtualDirectory = virtualDirectory.getImages().stream()
                .filter(item -> nonNull(item.getId()))
                .filter(item -> item.getId().equals(image.getId()))
                .findFirst()
                .orElse(null);

        final String homeDirectory = System.getProperty("user.home");
        final Path pathHomeDirectory = Paths.get(homeDirectory).resolve("ser_for");

        if (nonNull(imageFromVirtualDirectory)) {
            final String virtualName = imageFromVirtualDirectory.getVirtualName();
            final Path serFor = pathHomeDirectory.resolve(virtualName);

            if (Files.notExists(serFor)) {
                throw new ImageNotFoundException("Image not found on the disk");
            }

            return imageFromVirtualDirectory;
        }

        final Path uploadPath = imageUploader.upload(imageFromVirtualDirectory, virtualDirectory);

        // TODO author devpav Append logic for create new entity in database.

        return null;
    }

}
