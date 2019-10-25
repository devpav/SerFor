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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @PostMapping(value = "{realmName}/original/upload", produces = {
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<ImageDTO> uploadOriginImage(@RequestParam("image") MultipartFile multipart,
                                                      @PathVariable("realmName") String realmName) {
        final ImageDTO uploadedImage = imageFacade.uploadOriginalImage(multipart, realmName);
        return HttpResponse.responseEntity(uploadedImage);
    }

    @GetMapping("{realmName}/directories")
    public ResponseEntity<List<VirtualDirectoryDTO>> getDirectoriesByRealmName(
            @PathVariable("realmName") String realmName) {
        final List<VirtualDirectoryDTO> directories = directoryFacade.findByRealmName(realmName);
        return HttpResponse.responseCollection(directories);
    }

    @GetMapping("{realmName}/{originalName}")
    public ResponseEntity<ImageDTO> getImageResize(@PathVariable String realmName,
                                                   @PathVariable String originalName,
                                                   @RequestParam("width") Integer width,
                                                   @RequestParam("height") Integer height) {
        ImageDTO resizedImage = imageFacade.getResizedImage(realmName, originalName, width, height);
        return ResponseEntity.ok(resizedImage);
    }

    @GetMapping
    public ResponseEntity<List<RealmDTO>> getRealms() {
        List<RealmDTO> realms = realmFacade.findAll();
        return HttpResponse.responseCollection(realms);
    }

    @GetMapping("/{realmName}")
    public ResponseEntity<RealmDTO> getRealmByName(@PathVariable String realmName) {
        RealmDTO realm = realmFacade.getRealmByName(realmName);
        return HttpResponse.responseEntity(realm);
    }

    @PostMapping
    public ResponseEntity<RealmDTO> createRealm(@RequestBody RealmDTO realm) {
        return HttpResponse.responseEntity(realmFacade.create(realm));
    }

    @Override()
    @GetMapping("/{realmName}/config")
    public ResponseEntity<RealmConfigDTO> getRealmConfig(@PathVariable String realmName) {
        final RealmConfigDTO realmConfig = realmFacade.getRealmConfig(realmName);
        return HttpResponse.responseEntity(realmConfig);
    }

}
