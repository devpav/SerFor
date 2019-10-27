package by.devpav.serfor.resources;

import by.devpav.serfor.domain.dtos.VirtualDirectoryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VirtualDirectoryResource {

    public ResponseEntity<List<VirtualDirectoryDTO>> getByRealmName(String realmName);

}
