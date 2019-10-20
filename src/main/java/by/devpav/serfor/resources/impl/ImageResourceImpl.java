package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.dtos.ImageDTO;
import by.devpav.serfor.facade.ImageFacade;
import by.devpav.serfor.resources.ImageResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/{realm}/images")
public class ImageResourceImpl extends AbstractBasicEntityResource<ImageDTO> implements ImageResource {

    private final ImageFacade imageFacade;

    public ImageResourceImpl(ImageFacade imageFacade) {
        super(imageFacade);
        this.imageFacade = imageFacade;
    }

    @PostMapping(value = "/upload", produces = {
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity upload(@RequestParam("image") MultipartFile multipart, @PathVariable String realm) {
        return ResponseEntity.ok(imageFacade.upload(multipart, realm));
    }

}
