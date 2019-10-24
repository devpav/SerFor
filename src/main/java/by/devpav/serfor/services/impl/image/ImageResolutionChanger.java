package by.devpav.serfor.services.impl.image;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.VirtualDirectory;

public interface ImageResolutionChanger {

    Image changeResolution(Image image, VirtualDirectory virtualDirectory);

}
