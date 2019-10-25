package by.devpav.serfor.facade;

import by.devpav.serfor.domain.dtos.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ImageFacade extends BasicEntityFacade<ImageDTO> {

    ImageDTO uploadOriginalImage(MultipartFile multipartFile, String realm);
    ImageDTO getResizedImage(String realm, String originalNameImage, Integer width, Integer height);

}
