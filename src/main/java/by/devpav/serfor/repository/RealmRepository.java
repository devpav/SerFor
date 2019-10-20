package by.devpav.serfor.repository;

import by.devpav.serfor.domain.Realm;
import org.springframework.stereotype.Repository;

@Repository
public interface RealmRepository extends BasicEntityRepository<Realm> {

    Realm findByName(String name);

}
