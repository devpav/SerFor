package by.devpav.serfor.factory;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.VirtualDirectory;
import org.springframework.stereotype.Component;

@Component
public class ImageFactory {

    public Image create(String filename, Long length, VirtualDirectory virtualDirectory) {
        final Image image = new Image(filename, length);
        image.setVirtualDirectory(virtualDirectory);
        return image;
    }
}
