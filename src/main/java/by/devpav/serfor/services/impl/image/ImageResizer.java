package by.devpav.serfor.services.impl.image;

import java.awt.image.BufferedImage;

public interface ImageResizer {

    BufferedImage resize(BufferedImage img, int height, int width);

}
