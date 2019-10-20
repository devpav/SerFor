package by.devpav.serfor.resources;

import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import by.devpav.serfor.domain.dtos.RealmDTO;
import org.springframework.http.ResponseEntity;

public interface RealmResource extends BasicEntityResource<RealmDTO> {

    public ResponseEntity<RealmConfigDTO> getRealmConfig(String realmName);

}
