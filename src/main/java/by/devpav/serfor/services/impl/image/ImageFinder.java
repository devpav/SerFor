package by.devpav.serfor.services.impl.image;

import org.springframework.core.io.Resource;

public interface ImageFinder {

    Resource loadImage(String name, String sourceDirectory);

}
