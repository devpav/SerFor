package by.devpav.serfor.services;

import by.devpav.serfor.domain.Directory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DirectoryService extends BasicEntityService<Directory> {

    Directory findByName(String name);
    List<Directory> findByRealmName(String realmName);

}
