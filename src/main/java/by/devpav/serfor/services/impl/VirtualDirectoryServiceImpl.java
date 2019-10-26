package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Realm;
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
    @Transactional(readOnly = true)
    public List<VirtualDirectory> findByRealmName(String realmName) {
        return directoryRepository.findByRealm_Name(realmName);
    }

    @Override
    @Transactional(readOnly = true)
    public VirtualDirectory findVirtualDirectory(final String realmName, final Integer width, final Integer height) {
        return directoryRepository.findByRealm_NameAndWidthAndHeight(realmName, width, height);
    }

    @Transactional
    public VirtualDirectory buildDirectory(final String name, Integer width, Integer height, final Realm realm) {
        final VirtualDirectory virtualDirectory = new VirtualDirectory(name, width, height);
        virtualDirectory.setRealm(realm);
        return this.create(virtualDirectory);
    }

}
