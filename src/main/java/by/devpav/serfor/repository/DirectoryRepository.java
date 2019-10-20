package by.devpav.serfor.repository;

import by.devpav.serfor.domain.Directory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectoryRepository extends BasicEntityRepository<Directory> {

    Directory findByName(String name);

    List<Directory> findByRealm_Name(String realmName);

}
