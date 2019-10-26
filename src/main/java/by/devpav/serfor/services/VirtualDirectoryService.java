package by.devpav.serfor.services;

import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.VirtualDirectory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VirtualDirectoryService extends BasicEntityService<VirtualDirectory> {

    VirtualDirectory findByName(String name);
    List<VirtualDirectory> findByRealmName(String realmName);
    VirtualDirectory findVirtualDirectory(String realmName, Integer width, Integer height);
    VirtualDirectory buildDirectory(String name, Integer width, Integer height, Realm realm);

}
