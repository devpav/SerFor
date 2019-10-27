package by.devpav.serfor.facade;

import by.devpav.serfor.domain.dtos.ImageDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageFacade extends BasicEntityFacade<ImageDTO> {

    ImageDTO uploadOriginalImage(MultipartFile multipartFile, String realm);
    ImageDTO getResizedImage(String realm, String originalNameImage, Integer width, Integer height);

    /**
     * @param imageName The image name which we want given
     * @param realm The name of the area in which we work
     * @param vdir The name virtual directory of the area in which we work
     * @return resource of image
     */
    Resource loadImageByName(String imageName, String realm, String vdir);
}
