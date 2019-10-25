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
    private final ImageMapper imageMapper;

    public ImageFacadeImpl(ImageService imageService,
                           ImageMapper imageMapper) {
        super(imageService, imageMapper);
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @Override
    public ImageDTO uploadOriginalImage(MultipartFile multipartFile, String realm) {
        final Image uploadedImage = imageService.uploadOriginalImage(multipartFile, realm);
        return imageMapper.toDTO(uploadedImage);
    }

    @Override
    public ImageDTO getResizedImage(String realm, String originalNameImage, Integer width, Integer height) {
        Image resizedImage = imageService.getResizedImage(realm, originalNameImage, width, height);
        return imageMapper.toDTO(resizedImage);
    }

}
