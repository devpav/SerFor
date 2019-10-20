package by.devpav.serfor.services.impl.image;

import by.devpav.serfor.exceptions.ImageNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.isNull;

@Component
public class ImageFinderImpl implements ImageFinder {

    @Override
    public Resource loadImage(String name, String sourceDirectory) {
        final Path pathSourceDirectory = Paths.get(sourceDirectory);
        final Path normalize = pathSourceDirectory.resolve(name).normalize();

        if (Files.notExists(normalize)) throw new ImageNotFoundException("Image isn't found with name [" + name + "]");

        Resource resource = null;
        try {
            resource = new UrlResource(pathSourceDirectory.resolve(name).normalize().toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (isNull(resource) || !resource.exists())
            throw new ImageNotFoundException("Image isn't found with name [" + name + "]");

        return resource;
    }

}
