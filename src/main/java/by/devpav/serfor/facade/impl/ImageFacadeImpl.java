package by.devpav.serfor.facade.impl;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.dtos.ImageDTO;
import by.devpav.serfor.facade.ImageFacade;
import by.devpav.serfor.facade.mappers.ImageMapper;
import by.devpav.serfor.services.ImageService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageFacadeImpl extends AbstractBasicEntityFacade<Image, ImageDTO> implements ImageFacade {

    private final ImageService imageService;

    public ImageFacadeImpl(ImageService imageService,
                           ImageMapper imageMapper) {
        super(imageService, imageMapper);
        this.imageService = imageService;
    }

    @Override
    public Image upload(MultipartFile multipartFile, String realm) {

        return imageService.upload(multipartFile, realm);
    }

}
