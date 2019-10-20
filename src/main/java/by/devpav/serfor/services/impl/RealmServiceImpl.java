package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.BasicEntity;
import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.RealmConfig;
import by.devpav.serfor.repository.RealmRepository;
import by.devpav.serfor.services.DirectoryService;
import by.devpav.serfor.services.RealmService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class RealmServiceImpl extends AbstractBasicEntityService<Realm> implements RealmService {

    private final RealmRepository realmRepository;
    private final DirectoryService directoryService;

    public RealmServiceImpl(RealmRepository realmRepository,
                            DirectoryService directoryService) {
        super(realmRepository);
        this.realmRepository = realmRepository;
        this.directoryService = directoryService;
    }


    @Override
    @Transactional
    public Realm update(Realm entity) {
        final Realm update = super.update(entity);

        if (nonNull(update.getDirectories())) {
            final Long[] longs = update.getDirectories().stream().map(BasicEntity::getId).toArray(Long[]::new);
            final List<Directory> references = directoryService.getReferences(longs);
            update.setDirectories(new HashSet<>(references));
        }

        return update;
    }

    @Override
    @Transactional(readOnly = true)
    public Realm findRealmByName(String name) {
        return realmRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public RealmConfig getRealmConfig(String realmName) {
        final Realm foundRealm = realmRepository.findByName(realmName);
        if (isNull(foundRealm)) return null;
        return foundRealm.getRealmConfig();
    }

    @Override
    public Realm create(Realm entity) {
        return super.create(entity);
    }
}
