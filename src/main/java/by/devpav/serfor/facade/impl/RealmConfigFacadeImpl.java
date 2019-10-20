package by.devpav.serfor.facade.impl;

import by.devpav.serfor.domain.RealmConfig;
import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import by.devpav.serfor.facade.RealmConfigFacade;
import by.devpav.serfor.facade.mappers.RealmConfigMapper;
import by.devpav.serfor.services.RealmConfigService;
import org.springframework.stereotype.Component;

@Component
public class RealmConfigFacadeImpl extends AbstractBasicEntityFacade<RealmConfig, RealmConfigDTO>
        implements RealmConfigFacade {

    public RealmConfigFacadeImpl(RealmConfigService realmConfigService,
                                 RealmConfigMapper realmConfigMapper) {
        super(realmConfigService, realmConfigMapper);
    }

}
