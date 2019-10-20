package by.devpav.serfor.services;

import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.RealmConfig;

public interface RealmService extends BasicEntityService<Realm> {

    Realm findRealmByName(String name);

    RealmConfig getRealmConfig(String realmName);

}
