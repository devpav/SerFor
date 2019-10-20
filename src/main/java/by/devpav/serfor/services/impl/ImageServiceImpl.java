package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.exceptions.ObjectThrow;
import by.devpav.serfor.factory.DirectoryFactory;
import by.devpav.serfor.factory.ImageFactory;
import by.devpav.serfor.repository.ImageRepository;
import by.devpav.serfor.services.DirectoryService;
import by.devpav.serfor.services.ImageService;
import by.devpav.serfor.services.ImageUploader;
import by.devpav.serfor.services.RealmService;
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

    private final ImageUploader imageUploader;
    private final RealmService realmService;
    private final DirectoryService directoryService;
    private final ImageRepository imageRepository;
    private final DirectoryFactory directoryFactory;
    private final ImageFactory imageFactory;

    public ImageServiceImpl(ImageRepository imageRepository,
                            ImageUploader imageUploader,
                            RealmService realmService,
                            DirectoryService directoryService,
                            DirectoryFactory directoryFactory,
                            ImageFactory imageFactory) {
        super(imageRepository);
        this.imageUploader = imageUploader;
        this.realmService = realmService;
        this.directoryService = directoryService;
        this.imageRepository = imageRepository;
        this.directoryFactory = directoryFactory;
        this.imageFactory = imageFactory;
    }


    @Override
    public Image create(Image entity) {
        return super.create(entity);
    }

    @Override
    @Transactional
    public Image upload(MultipartFile multipartFile, String realm) {
        ObjectThrow.requireNotNullThrow(multipartFile, "MultipartFile mustn't be is null");
        ObjectThrow.requireNotNullThrow(realm, "Realm mustn't be is null");

        final String originalFilename = multipartFile.getOriginalFilename();

        byte[] bytes = null;
        try {
            bytes = multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectThrow.requireNotNullThrow(bytes, "Data mustn't be is null");

        final Realm realmEntity = realmService.findRealmByName(realm);

        ObjectThrow.requireRealmExists(realmEntity, "Realm [" + realm + "] not found");

        Directory originFolder = null;

        if (nonNull( realmEntity.getRealmConfig())) {
            final String realmCommonDir = realmEntity.getRealmConfig().getRealmDir();
            originFolder = directoryService.findByName(realmCommonDir);
        }

        if (isNull(originFolder)) {
            final String originalFolderName = "origin_folder";
            originFolder = directoryService.findByName(originalFolderName);

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
            originFolder = directoryService.create(originFolder);
            realmEntity.getDirectories().add(originFolder);
            realmService.update(realmEntity);
        }

        final String uploadFileName = imageUploader.upload(originalFilename, originFolder.getName(), bytes);
        final Image image = imageFactory.create(uploadFileName, (long) bytes.length, originFolder);

        return imageRepository.save(image);
    }

}
