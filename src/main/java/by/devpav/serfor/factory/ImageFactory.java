package by.devpav.serfor.factory;

import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.domain.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageFactory {

    public Image create(String filename, Long length, Directory directory) {
        final Image image = new Image(filename, length);
        image.setDirectory(directory);
        return image;
    }
}
