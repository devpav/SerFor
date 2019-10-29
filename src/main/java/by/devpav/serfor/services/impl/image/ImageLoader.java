package by.devpav.serfor.services.impl.image;

import org.springframework.core.io.Resource;

import java.nio.file.Path;

public interface ImageLoader {

    Resource loadImage(Path pathToFile);

}
