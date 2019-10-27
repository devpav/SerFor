package by.devpav.serfor.services.impl.image;

import java.awt.image.BufferedImage;

public interface ImageResizer {

    /** The method resizes different images.
     * @param img the BufferedImage of image which needs resize
     * @param height picture height
     * @param width picture width
     * */
    BufferedImage resize(BufferedImage img, int height, int width);

}
