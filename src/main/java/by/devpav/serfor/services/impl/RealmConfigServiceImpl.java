package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.RealmConfig;
import by.devpav.serfor.repository.RealmConfigRepository;
import by.devpav.serfor.services.RealmConfigService;
import org.springframework.stereotype.Service;

@Service
public class RealmConfigServiceImpl extends AbstractBasicEntityService<RealmConfig> implements RealmConfigService {

    public RealmConfigServiceImpl(RealmConfigRepository realmConfigRepository) {
        super(realmConfigRepository);
    }

}
