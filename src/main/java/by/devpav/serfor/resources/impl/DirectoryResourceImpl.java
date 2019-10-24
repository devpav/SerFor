package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.dtos.DirectoryDTO;
import by.devpav.serfor.facade.DirectoryFacade;
import by.devpav.serfor.facade.ImageFacade;
import by.devpav.serfor.resources.DirectoryResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{realmName}")
public class DirectoryResourceImpl extends AbstractBasicEntityResource<DirectoryDTO> implements DirectoryResource {

    private final DirectoryFacade directoryFacade;

    private final ImageFacade imageFacade;

    public DirectoryResourceImpl(DirectoryFacade directoryFacade, ImageFacade imageFacade) {
        super(directoryFacade);
        this.directoryFacade = directoryFacade;
        this.imageFacade = imageFacade;
    }

    @Override
    public ResponseEntity<List<DirectoryDTO>> getByRealmName(String realmName) {
        return null;
    }
}
