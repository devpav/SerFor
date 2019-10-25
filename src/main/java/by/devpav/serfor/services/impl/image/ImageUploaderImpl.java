package by.devpav.serfor.services.impl.image;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.VirtualDirectory;
import by.devpav.serfor.services.ImageUploader;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageUploaderImpl implements ImageUploader {

    private final ImageRipper imageRipper;
    private final ImageNameGenerator imageNameGenerator;
    private final ImageResizer imageResizer;

    private final static String propertyUserHome = System.getProperty("user.home");

    public ImageUploaderImpl(ImageRipper imageRipper,
                             ImageNameGenerator imageNameGenerator,
                             ImageResizer imageResizer) {
        this.imageRipper = imageRipper;
        this.imageNameGenerator = imageNameGenerator;
        this.imageResizer = imageResizer;
    }

    @Override
    public String upload(String imageName, String directory, int width, int height, byte[] bytes) {
        final Path pathHomeDirectory = Paths.get(propertyUserHome).resolve(directory);

        try(final InputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
            final BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            final BufferedImage resizedBufferedImage = imageResizer.resize(bufferedImage, height, width);

            final String generateImageName = imageNameGenerator.generateImageName(imageName, height, width);

            final Path newImagePath = pathHomeDirectory.resolve(generateImageName);

            final String imageExtension = imageRipper.getImageExtension(generateImageName);

            boolean isWritten;
            try (final BufferedOutputStream bufferedOutputStream =
                         new BufferedOutputStream(new FileOutputStream(newImagePath.toFile()))) {
                isWritten = ImageIO.write(resizedBufferedImage, imageExtension, bufferedOutputStream);
            }
            return isWritten ? generateImageName : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String upload(String imageName, String directory, byte[] bytes) {
        final Path pathHomeDirectory = Paths.get(propertyUserHome).resolve(directory);

        final String generateImageName = imageNameGenerator.generateImageName(imageName);
        final File file = pathHomeDirectory.resolve(generateImageName).toFile();
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            bufferedOutputStream.write(bytes);
            return generateImageName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Path upload(final Image originalImage, final VirtualDirectory virtualDirectory) {
        Path pathToResizedImage = null;

        try {
            final Path resizedImage = Paths.get(System.getProperty("user.home"))
                    .resolve(originalImage.getVirtualName());
            final BufferedImage bufferedImage = ImageIO.read(resizedImage.toFile());

            final String imageOriginName = originalImage.getOriginName();
            final Integer directoryHeight = virtualDirectory.getHeight();
            final Integer directoryWidth = virtualDirectory.getWidth();

            final String generateImageName =
                    imageNameGenerator.generateImageName(imageOriginName, directoryHeight, directoryWidth);

            final String imageExtension = imageRipper.getImageExtension(imageOriginName);

            final BufferedImage bufferedResizedImage =
                    imageResizer.resize(bufferedImage, directoryHeight, directoryWidth);

            pathToResizedImage = Paths.get(System.getProperty("user.home")).resolve(generateImageName);

            final FileOutputStream fileOutputStream = new FileOutputStream(pathToResizedImage.toFile());
            try (final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                final boolean isWrote = ImageIO.write(bufferedResizedImage, imageExtension, bufferedOutputStream);

                if (!isWrote) return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pathToResizedImage;
    }
}
