package by.devpav.serfor.services.impl.image;

import org.springframework.core.io.Resource;

public interface ImageLoader {

    public Resource loadImage(String imageName);

}
