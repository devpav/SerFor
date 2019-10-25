package by.devpav.serfor.services.impl.image;

import org.springframework.stereotype.Component;

@Component
public class ImageResolutionChangerImpl implements ImageResolutionChanger {

    private final ImageResizer imageResizer;

    public ImageResolutionChangerImpl(ImageResizer imageResizer) {
        this.imageResizer = imageResizer;
    }


    @Override
    public byte[] changeResolution(byte[] bytes, int width, int height) {

        return new byte[0];
    }

}
