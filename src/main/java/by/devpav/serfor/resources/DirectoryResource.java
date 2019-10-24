package by.devpav.serfor.resources;

import by.devpav.serfor.domain.dtos.DirectoryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DirectoryResource {

    public ResponseEntity<List<DirectoryDTO>> getByRealmName(String realmName);

}
