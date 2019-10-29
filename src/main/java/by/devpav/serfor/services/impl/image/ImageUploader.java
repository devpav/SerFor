package by.devpav.serfor.services.impl.image;

import by.devpav.serfor.services.files.SerForImage;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {

    SerForImage resizeAndUpload(String fileName, Integer width, Integer height);
    SerForImage upload(MultipartFile multipartFile);
    SerForImage upload(MultipartFile multipartFile, Integer width, Integer height);

}
