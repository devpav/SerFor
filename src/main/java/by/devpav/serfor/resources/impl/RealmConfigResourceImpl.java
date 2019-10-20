package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import by.devpav.serfor.facade.impl.RealmConfigFacadeImpl;
import by.devpav.serfor.resources.RealmConfigResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/realm-configs")
public class RealmConfigResourceImpl extends AbstractBasicEntityResource<RealmConfigDTO>
        implements RealmConfigResource {

    public RealmConfigResourceImpl(RealmConfigFacadeImpl realmConfigFacadeImpl) {
        super(realmConfigFacadeImpl);
    }

}
