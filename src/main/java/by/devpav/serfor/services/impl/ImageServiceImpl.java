package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.repository.ImageRepository;
import by.devpav.serfor.services.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl extends AbstractBasicEntityService<Image> implements ImageService {

    public ImageServiceImpl(ImageRepository imageRepository) {
        super(imageRepository);
    }

}
