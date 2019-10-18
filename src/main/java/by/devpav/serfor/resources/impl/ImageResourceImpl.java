package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.services.ImageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageResourceImpl extends AbstractBasicEntityResource<Image> {

    public ImageResourceImpl(ImageService imageService) {
        super(imageService);
    }

}
