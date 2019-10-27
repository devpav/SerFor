package by.devpav.serfor.services.impl.image;

import by.devpav.serfor.exceptions.ImageNotFoundException;
import by.devpav.serfor.services.directories.SerForDirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.isNull;

@Component
public class ImageLoaderImpl implements ImageLoader {

    @Autowired
    private SerForDirectoryManager serForDirectoryManager;

    @Override
    public Resource loadImage(String imageName) {
        final Path imagePath = serForDirectoryManager.buildImagePath(imageName);

        final ImageNotFoundException imageNotFoundException =
                new ImageNotFoundException("Image isn't found with name [" + imageName + "]");

        if (Files.notExists(imagePath)) {
            throw imageNotFoundException;
        }

        Resource resource = null;
        try {
            resource = new UrlResource(imagePath.normalize().toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (isNull(resource) || !resource.exists()) {
            throw imageNotFoundException;
        }

        return resource;
    }

}
