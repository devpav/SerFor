package by.devpav.serfor.services;

import by.devpav.serfor.domain.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService extends BasicEntityService<Image> {

    /** The method for loading original image in realmDir folder.
     * @param multipartFile the multipartFile has bytes future of picture
     * @param realm realm is name created realm
     * */
    Image uploadOriginalImage(MultipartFile multipartFile, String realm);
}
