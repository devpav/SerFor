package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.dtos.DirectoryDTO;
import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import by.devpav.serfor.domain.dtos.RealmDTO;
import by.devpav.serfor.facade.DirectoryFacade;
import by.devpav.serfor.facade.ImageFacade;
import by.devpav.serfor.facade.RealmFacade;
import by.devpav.serfor.resources.RealmResource;
import by.devpav.serfor.util.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/realms/")
public class RealmResourceImpl extends AbstractBasicEntityResource<RealmDTO> implements RealmResource {

    private final DirectoryFacade directoryFacade;

    private final ImageFacade imageFacade;
    private final RealmFacade realmFacade;

    public RealmResourceImpl(RealmFacade realmFacade, DirectoryFacade directoryFacade, ImageFacade imageFacade) {
        super(realmFacade);
        this.directoryFacade = directoryFacade;
        this.imageFacade = imageFacade;
        this.realmFacade = realmFacade;
    }


    @GetMapping("{realmName}/original/upload")
    public ResponseEntity<Image> uploadOriginImage(@RequestParam("image") MultipartFile multipart,
                                                   @PathVariable String realmName) {
        final Image image = imageFacade.upload(multipart, realmName);
        return HttpResponse.responseEntity(image);
    }

    @GetMapping("{realmName}/directories")
    public ResponseEntity<List<DirectoryDTO>> getImageByRealmName(@PathVariable String realmName) {
        final List<DirectoryDTO> directories = directoryFacade.findByRealmName(realmName);
        return HttpResponse.responseCollection(directories);
    }

    @GetMapping
    public ResponseEntity<List<DirectoryDTO>> getImageByDirectoryAndRealm(@PathVariable String realmName,
                                                                          @PathVariable String directory,
                                                                          @PathVariable String image) {
        final List<DirectoryDTO> directories = directoryFacade.findByRealmName(realmName);
        return HttpResponse.responseCollection(directories);
    }



    @GetMapping
    public ResponseEntity<List<RealmDTO>> getRealms() {
        List<RealmDTO> realms = realmFacade.findAll();
        return HttpResponse.responseCollection(realms);
    }

    @PostMapping
    public ResponseEntity<List<RealmDTO>> createRealm(@RequestBody RealmDTO realm) {
        return HttpResponse.responseEntity(realmFacade.create(realm));
    }


    @Override
    @GetMapping
    public ResponseEntity<RealmConfigDTO> getRealmConfig(@PathVariable String realmName) {
        final RealmConfigDTO realmConfig = realmFacade.getRealmConfig(realmName);
        return HttpResponse.responseEntity(realmConfig);
    }
}
