package by.devpav.serfor.facade;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.dtos.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ImageFacade extends BasicEntityFacade<ImageDTO> {

    Image upload(MultipartFile multipartFile, String realm);

}
