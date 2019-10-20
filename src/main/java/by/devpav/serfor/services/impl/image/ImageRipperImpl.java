package by.devpav.serfor.services.impl.image;

import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ImageRipperImpl implements ImageRipper {

    @Override
    public String getImageExtension(String filename) {
        if (isNull(filename)) return null;
        final int lastIndex = filename.lastIndexOf(".") + 1;
        return filename.contains(".") ? filename.substring(lastIndex) : null;
    }

}
