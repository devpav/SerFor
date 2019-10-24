package by.devpav.serfor.facade.impl;

import by.devpav.serfor.domain.VirtualDirectory;
import by.devpav.serfor.domain.dtos.VirtualDirectoryDTO;
import by.devpav.serfor.facade.DirectoryFacade;
import by.devpav.serfor.facade.mappers.DirectoryMapper;
import by.devpav.serfor.services.VirtualDirectoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectoryFacadeImpl extends AbstractBasicEntityFacade<VirtualDirectory, VirtualDirectoryDTO> implements DirectoryFacade {

    private final VirtualDirectoryService virtualDirectoryService;
    private final DirectoryMapper directoryMapper;

    public DirectoryFacadeImpl(VirtualDirectoryService virtualDirectoryService,
                               DirectoryMapper directoryMapper) {
        super(virtualDirectoryService, directoryMapper);
        this.virtualDirectoryService = virtualDirectoryService;
        this.directoryMapper = directoryMapper;
    }

    @Override
    public List<VirtualDirectoryDTO> findByRealmName(String realmName) {
        final List<VirtualDirectory> directories = virtualDirectoryService.findByRealmName(realmName);
        return directoryMapper.toDTO(directories);
    }

}
