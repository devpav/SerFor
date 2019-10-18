package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.services.DirectoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionaries")
public class DirectoryResourceImpl extends AbstractBasicEntityResource<Directory> {

    public DirectoryResourceImpl(DirectoryService directoryService) {
        super(directoryService);
    }

}
