package by.devpav.serfor.resources;

import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import org.springframework.http.ResponseEntity;

public interface RealmResource {

    public ResponseEntity<RealmConfigDTO> getRealmConfig(String realmName);

}
