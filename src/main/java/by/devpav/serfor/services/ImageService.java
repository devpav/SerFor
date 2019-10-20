package by.devpav.serfor.services;

import by.devpav.serfor.domain.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService extends BasicEntityService<Image> {

    public Image upload(MultipartFile multipartFile, String realm);
}
