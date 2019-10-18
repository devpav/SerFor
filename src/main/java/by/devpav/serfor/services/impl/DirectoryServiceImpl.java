package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.repository.DirectoryRepository;
import by.devpav.serfor.services.DirectoryService;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceImpl extends AbstractBasicEntityService<Directory> implements DirectoryService {

    public DirectoryServiceImpl(DirectoryRepository directoryRepository) {
        super(directoryRepository);
    }

}
