package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.dtos.DirectoryDTO;
import by.devpav.serfor.facade.DirectoryFacade;
import by.devpav.serfor.resources.DirectoryResource;
import by.devpav.serfor.util.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{realmName}")
public class DirectoryResourceImpl extends AbstractBasicEntityResource<DirectoryDTO> implements DirectoryResource {

    private final DirectoryFacade directoryFacade;

    public DirectoryResourceImpl(DirectoryFacade directoryFacade) {
        super(directoryFacade);
        this.directoryFacade = directoryFacade;
    }


    @Override
    @GetMapping("/directories")
    public ResponseEntity<List<DirectoryDTO>> getByRealmName(@PathVariable String realmName) {
        final List<DirectoryDTO> directories = directoryFacade.findByRealmName(realmName);
        return HttpResponse.responseCollection(directories);
    }

    @GetMapping("/{directory}/{image}")
    public ResponseEntity<List<DirectoryDTO>> getByRealmName(@PathVariable String realmName,
                                                             @PathVariable String directory,
                                                             @PathVariable String image) {
        final List<DirectoryDTO> directories = directoryFacade.findByRealmName(realmName);
        return HttpResponse.responseCollection(directories);
    }

}
