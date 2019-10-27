package by.devpav.serfor.factory;

import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.VirtualDirectory;
import org.springframework.stereotype.Component;

@Component
public class DirectoryFactory {

    public VirtualDirectory create(String name, Realm realm, Boolean origin) {
        VirtualDirectory virtualDirectory = new VirtualDirectory();
        virtualDirectory.setName(name);
        virtualDirectory.setRealm(realm);
        virtualDirectory.setOrigin(origin);
        return virtualDirectory;
    }

}
