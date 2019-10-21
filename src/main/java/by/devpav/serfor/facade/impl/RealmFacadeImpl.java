package by.devpav.serfor.facade.impl;

import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.RealmConfig;
import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import by.devpav.serfor.domain.dtos.RealmDTO;
import by.devpav.serfor.facade.RealmFacade;
import by.devpav.serfor.facade.mappers.RealmConfigMapper;
import by.devpav.serfor.facade.mappers.RealmMapper;
import by.devpav.serfor.services.RealmService;
import org.springframework.stereotype.Component;

@Component
public class RealmFacadeImpl extends AbstractBasicEntityFacade<Realm, RealmDTO> implements RealmFacade {

    private final RealmService realmService;
    private final RealmMapper realmMapper;
    private final RealmConfigMapper realmConfigMapper;

    public RealmFacadeImpl(RealmService realmService,
                           RealmMapper realmMapper,
                           RealmConfigMapper realmConfigMapper) {
        super(realmService, realmMapper);
        this.realmService = realmService;
        this.realmMapper = realmMapper;
        this.realmConfigMapper = realmConfigMapper;
    }

    @Override
    public RealmConfigDTO getRealmConfig(String realmName) {
        final RealmConfig realmConfig = realmService.getRealmConfig(realmName);
        return realmConfigMapper.toDTO(realmConfig);
    }

}
