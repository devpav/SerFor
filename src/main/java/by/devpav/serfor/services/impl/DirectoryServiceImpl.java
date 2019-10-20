package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.repository.DirectoryRepository;
import by.devpav.serfor.services.DirectoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DirectoryServiceImpl extends AbstractBasicEntityService<Directory> implements DirectoryService {

    private DirectoryRepository directoryRepository;

    public DirectoryServiceImpl(DirectoryRepository directoryRepository) {
        super(directoryRepository);
        this.directoryRepository = directoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Directory findByName(String name) {
        return directoryRepository.findByName(name);
    }

    @Override
    public List<Directory> findByRealmName(String realmName) {
        return directoryRepository.findByRealm_Name(realmName);
    }

}
