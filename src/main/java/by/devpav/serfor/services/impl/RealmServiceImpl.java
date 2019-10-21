package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.RealmConfig;
import by.devpav.serfor.exceptions.EntityNotFoundException;
import by.devpav.serfor.exceptions.realm.RealmMustNotContainsNameIsNull;
import by.devpav.serfor.exceptions.realm.RealmWithNameAlreadyExists;
import by.devpav.serfor.repository.RealmRepository;
import by.devpav.serfor.services.RealmService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static by.devpav.serfor.exceptions.ObjectThrow.requireNotNullThrow;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class RealmServiceImpl extends AbstractBasicEntityService<Realm> implements RealmService {

    private final RealmRepository realmRepository;

    public RealmServiceImpl(RealmRepository realmRepository) {
        super(realmRepository);
        this.realmRepository = realmRepository;
    }


    @Override
    @Transactional
    public Realm update(Realm entity) {
        requireNotNullThrow(entity, "Entity mustn't be is null [update]");
        requireNotNullThrow(entity.getId(), "Entity ID mustn't be is null [update]");

        final Realm foundRealm = realmRepository.findById(entity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity with ID [" + entity.getId() + "] not found"));

        if (isNull(entity.getName())) {
            throw new RealmMustNotContainsNameIsNull();
        }

        final Realm realm = realmRepository.findByName(entity.getName());

        if (nonNull(realm)) {
            throw new RealmWithNameAlreadyExists();
        }

        foundRealm.setName(entity.getName());

        return realmRepository.save(foundRealm);
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
        if (nonNull(entity) && nonNull(entity.getRealmConfig()))
            entity.getRealmConfig().setRealm(entity);
        return super.create(entity);
    }

}
