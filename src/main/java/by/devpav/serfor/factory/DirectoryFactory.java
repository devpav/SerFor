package by.devpav.serfor.factory;

import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.domain.Realm;
import org.springframework.stereotype.Component;

@Component
public class DirectoryFactory {

    public Directory create(String name, Realm realm, Boolean origin) {
        Directory directory = new Directory();
        directory.setName(name);
        directory.setRealm(realm);
        directory.setOrigin(origin);
        return directory;
    }

}
