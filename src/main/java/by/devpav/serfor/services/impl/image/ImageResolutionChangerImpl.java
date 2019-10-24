package by.devpav.serfor.services.impl.image;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.VirtualDirectory;
import org.springframework.stereotype.Component;

@Component
public class ImageResolutionChangerImpl implements ImageResolutionChanger {

    private final ImageResizer imageResizer;

    public ImageResolutionChangerImpl(ImageResizer imageResizer) {
        this.imageResizer = imageResizer;
    }


    @Override
    public Image changeResolution(Image image, VirtualDirectory virtualDirectory) {
/*
        requireNonNull(image, "Image mustn't be is null");
        requireNonNull(virtualDirectory, "VirtualDirectory mustn't be is null");

        final Integer width = virtualDirectory.getWidth();
        final Integer height = virtualDirectory.getHeight();

        imageResizer.resize()

        imageUploader.upload(image.getName(), )
*/

        return null;
    }

}
