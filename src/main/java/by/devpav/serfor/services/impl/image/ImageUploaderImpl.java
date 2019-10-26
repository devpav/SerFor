package by.devpav.serfor.services.impl.image;

import by.devpav.serfor.exceptions.image.ImageAlreadyExistsException;
import by.devpav.serfor.services.directories.SerForDirectoryManager;
import by.devpav.serfor.services.files.SerForFileManager;
import by.devpav.serfor.services.files.SerForImage;
import by.devpav.serfor.services.files.SerForImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class ImageUploaderImpl implements ImageUploader {

    @Autowired
    private ImageRipper imageRipper;
    @Autowired
    private ImageNameGenerator imageNameGenerator;
    @Autowired
    private ImageResizer imageResizer;
    @Autowired
    private SerForDirectoryManager serForDirectoryManager;
    @Autowired
    private SerForFileManager serForFileManager;

    @Override
    public SerForImage resizeAndUpload(String fileName, final Integer width, final Integer height) {
        final Path imagePath = serForDirectoryManager.buildImagePath(fileName);

        InputStream inputStream = null;

        try {
            inputStream = Files.newInputStream(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String imageExtension = imageRipper.getImageExtension(fileName);
        final String generateVirtualName = imageNameGenerator.generate(height, width, imageExtension);

        final Function<BufferedImage, BufferedImage> converter =
                bufferedImage -> imageResizer.resize(bufferedImage, height, width);

        final BufferedImage bufferedImage = upload(inputStream, converter, generateVirtualName);

        if (bufferedImage == null) {
            return null;
        }

        final Path targetFileImage = serForDirectoryManager.buildImagePath(generateVirtualName);

        return SerForImageEntity.getBuilder()
                .width(bufferedImage.getWidth())
                .height(bufferedImage.getHeight())
                .size(serForFileManager.getFileSize(generateVirtualName, targetFileImage))
                .virtualName(generateVirtualName)
                .originName(fileName)
                .build();
    }

    @Override
    public SerForImage upload(MultipartFile multipartFile, Integer width, Integer height) {
        final Function<BufferedImage, BufferedImage> converter =
                bufferedImage -> imageResizer.resize(bufferedImage, height, width);

        final String originalFilename = multipartFile.getOriginalFilename();

        final String imageExtension = imageRipper.getImageExtension(originalFilename);
        final String generateImageName = imageNameGenerator.generate(height, width, imageExtension);

        BufferedImage bufferedImage = null;
        try(InputStream multipartFileInputStream = multipartFile.getInputStream()) {
            bufferedImage = upload(multipartFileInputStream, converter, generateImageName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bufferedImage == null) {
            return null;
        }

        return SerForImageEntity.getBuilder()
                .width(bufferedImage.getWidth())
                .height(bufferedImage.getHeight())
                .size(multipartFile.getSize())
                .virtualName(generateImageName)
                .originName(generateImageName)
                .build();
    }

    @Override
    public SerForImage upload(MultipartFile multipartFile) {
        InputStream fileInputStream = null;

        try {
            fileInputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String originalFilename = multipartFile.getOriginalFilename();
        final String imageExtension = imageRipper.getImageExtension(originalFilename);
        final String generateImageName = imageNameGenerator.generate(imageExtension);

        final Function<BufferedImage, BufferedImage> converter = bufferedImage -> bufferedImage;
        final BufferedImage bufferedImage = upload(fileInputStream, converter, generateImageName);

        if (bufferedImage == null) {
            return null;
        }

        return SerForImageEntity.getBuilder()
                .width(bufferedImage.getWidth())
                .height(bufferedImage.getHeight())
                .size(multipartFile.getSize())
                .virtualName(generateImageName)
                .originName(generateImageName)
                .build();
    }

    private BufferedImage upload(final InputStream inputStream,
                                 final Function<BufferedImage, BufferedImage> converter,
                                 final String targetImageName,
                                 final String ... extensions) {
        final String extension = imageRipper.getImageExtension(targetImageName);

        if (extension == null ) {
            throw new RuntimeException("Extension mustn't be is Null");
        }

        final Path buildImagePath = serForDirectoryManager.buildImagePath(targetImageName);

        if (Files.exists(buildImagePath)) {
            throw new ImageAlreadyExistsException("Image with name" + targetImageName + " already exists on the disk");
        }

        if (extensions != null && extensions.length > 0) {
            Stream.of(extensions).filter(Objects::nonNull)
                    .filter(item -> item.toLowerCase().equals(extension.toLowerCase()))
                    .findFirst().orElseThrow(() -> new RuntimeException("Extension not supported"));
        }

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(inputStream);

            bufferedImage = converter.apply(bufferedImage);

            final FileOutputStream fileOutputStream = new FileOutputStream(buildImagePath.toFile());
            try (final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                final boolean isUploaded = ImageIO.write(bufferedImage, extension, bufferedOutputStream);
                if (!isUploaded) {
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedImage;
    }
}
