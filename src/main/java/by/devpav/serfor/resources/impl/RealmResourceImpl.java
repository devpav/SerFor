package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import by.devpav.serfor.domain.dtos.RealmDTO;
import by.devpav.serfor.facade.RealmFacade;
import by.devpav.serfor.resources.RealmResource;
import by.devpav.serfor.util.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/realms")
public class RealmResourceImpl extends AbstractBasicEntityResource<RealmDTO> implements RealmResource {

    private final RealmFacade realmFacade;

    public RealmResourceImpl(RealmFacade realmFacade) {
        super(realmFacade);
        this.realmFacade = realmFacade;
    }

    @Override
    @GetMapping("/{realmName}/config")
    public ResponseEntity<RealmConfigDTO> getRealmConfig(@PathVariable  String realmName) {
        final RealmConfigDTO realmConfig = realmFacade.getRealmConfig(realmName);
        return HttpResponse.responseEntity(realmConfig);
    }

    @Override
    public ResponseEntity<RealmDTO> update(@RequestBody RealmDTO entityDTO) {
        return super.update(entityDTO);
    }
}
