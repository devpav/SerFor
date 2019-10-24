package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.VirtualDirectory;
import by.devpav.serfor.repository.DirectoryRepository;
import by.devpav.serfor.services.VirtualDirectoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VirtualDirectoryServiceImpl extends AbstractBasicEntityService<VirtualDirectory>
        implements VirtualDirectoryService {

    private DirectoryRepository directoryRepository;

    public VirtualDirectoryServiceImpl(DirectoryRepository directoryRepository) {
        super(directoryRepository);
        this.directoryRepository = directoryRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public VirtualDirectory findByName(String name) {
        return directoryRepository.findByName(name);
    }

    @Override
    public List<VirtualDirectory> findByRealmName(String realmName) {
        return directoryRepository.findByRealm_Name(realmName);
    }

}
