package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.dtos.ImageDTO;
import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import by.devpav.serfor.domain.dtos.RealmDTO;
import by.devpav.serfor.domain.dtos.VirtualDirectoryDTO;
import by.devpav.serfor.facade.DirectoryFacade;
import by.devpav.serfor.facade.ImageFacade;
import by.devpav.serfor.facade.RealmFacade;
import by.devpav.serfor.resources.RealmResource;
import by.devpav.serfor.util.HttpResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/realms")
public class RealmResourceImpl extends AbstractBasicEntityResource<RealmDTO> implements RealmResource {

    private final DirectoryFacade directoryFacade;

    private final ImageFacade imageFacade;
    private final RealmFacade realmFacade;

    public RealmResourceImpl(RealmFacade realmFacade,
                             DirectoryFacade directoryFacade,
                             ImageFacade imageFacade) {
        super(realmFacade);
        this.directoryFacade = directoryFacade;
        this.imageFacade = imageFacade;
        this.realmFacade = realmFacade;
    }


    @PostMapping(
            value = "{realmName}/original/upload",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ImageDTO> uploadOriginImage(@RequestParam("image") MultipartFile multipart,
                                                      @PathVariable("realmName") String realmName) {
        final ImageDTO uploadedImage = imageFacade.uploadOriginalImage(multipart, realmName);
        return HttpResponse.responseEntity(uploadedImage);
    }

    @GetMapping("{realmName}/directories")
    public ResponseEntity<List<VirtualDirectoryDTO>> getDirectoriesByRealmName(@PathVariable("realmName") String realmName) {
        final List<VirtualDirectoryDTO> directories = directoryFacade.findByRealmName(realmName);
        return HttpResponse.responseCollection(directories);
    }

    @PostMapping(value = "{realmName}/{originalName:.+}")
    public ResponseEntity<ImageDTO> getImageResize(@PathVariable String realmName,
                                                   @PathVariable String originalName,
                                                   @RequestParam("width") Integer width,
                                                   @RequestParam("height") Integer height) {
        final ImageDTO resizedImage = imageFacade.getResizedImage(realmName, originalName, width, height);
        return HttpResponse.responseEntity(resizedImage);
    }

    @GetMapping
    public ResponseEntity<List<RealmDTO>> getRealms() {
        return HttpResponse.responseCollection(realmFacade.findAll());
    }

    @GetMapping("/{realmName}")
    public ResponseEntity<RealmDTO> getRealmByName(@PathVariable String realmName) {
        return HttpResponse.responseEntity(realmFacade.getRealmByName(realmName));
    }

    @PostMapping
    public ResponseEntity<RealmDTO> createRealm(@RequestBody RealmDTO realm) {
        return HttpResponse.responseCreatedEntity(realmFacade.create(realm));
    }

    @PutMapping
    public ResponseEntity<RealmDTO> updateRealm(@RequestBody RealmDTO realm) {
        return HttpResponse.responseCreatedEntity(realmFacade.update(realm));
    }

    @GetMapping("/{realmName}/{vdir}/images/{originalName:.+}")
    public ResponseEntity<Resource> getRealmConfig(@PathVariable("realmName") String realmName,
                                                   @PathVariable("vdir") String vdir,
                                                   @PathVariable String originalName,
                                                   HttpServletRequest httpServletRequest) {
        final Resource resource = imageFacade.loadImageByName(originalName, realmName, vdir);

        String contentType = "application/octet-stream";

        try {
            contentType = httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return HttpResponse.responseResource(resource, contentType);
    }

    @Override
    @GetMapping("/{realmName}/config")
    public ResponseEntity<RealmConfigDTO> getRealmConfig(@PathVariable String realmName) {
        return HttpResponse.responseEntity(realmFacade.getRealmConfig(realmName));
    }

}
