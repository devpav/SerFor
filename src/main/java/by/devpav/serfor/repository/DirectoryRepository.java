package by.devpav.serfor.repository;

import by.devpav.serfor.domain.VirtualDirectory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectoryRepository extends BasicEntityRepository<VirtualDirectory> {

    VirtualDirectory findByName(String name);

    List<VirtualDirectory> findByRealm_Name(String realmName);

    VirtualDirectory findByRealm_NameAndWidthAndHeight(String realm, Integer width, Integer height);

}
