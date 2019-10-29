package by.devpav.serfor.services;

import by.devpav.serfor.domain.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService extends BasicEntityService<Image> {

    /** The method for loading original image in realmDir folder.
     * @param multipartFile the multipartFile has bytes future of picture
     * @param realm realm is name created realm
     * */
    Image uploadOriginalImage(MultipartFile multipartFile, String realm);

    Image getResizedImage(String realm, String originalNameImage, Integer width, Integer height);

    /**
     * @param imageName The image name which we want given
     * @param realm The name of the area in which we work
     * @param vdir The name virtual directory of the area in which we work
     * @return resource of image
     */
    Resource loadImageByName(String imageName, String realm, String vdir);


    Resource getImageResourceCache(String realmName, String originalImageName, Integer width, Integer height);

}
