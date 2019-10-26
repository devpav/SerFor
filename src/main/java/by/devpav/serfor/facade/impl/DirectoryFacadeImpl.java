package by.devpav.serfor.facade.impl;

import by.devpav.serfor.domain.VirtualDirectory;
import by.devpav.serfor.domain.dtos.VirtualDirectoryDTO;
import by.devpav.serfor.facade.DirectoryFacade;
import by.devpav.serfor.facade.mappers.VirtualDirectoryMapper;
import by.devpav.serfor.services.VirtualDirectoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectoryFacadeImpl extends AbstractBasicEntityFacade<VirtualDirectory, VirtualDirectoryDTO> implements DirectoryFacade {

    private final VirtualDirectoryService virtualDirectoryService;
    private final VirtualDirectoryMapper virtualDirectoryMapper;

    public DirectoryFacadeImpl(VirtualDirectoryService virtualDirectoryService,
                               VirtualDirectoryMapper virtualDirectoryMapper) {
        super(virtualDirectoryService, virtualDirectoryMapper);
        this.virtualDirectoryService = virtualDirectoryService;
        this.virtualDirectoryMapper = virtualDirectoryMapper;
    }

    @Override
    public List<VirtualDirectoryDTO> findByRealmName(String realmName) {
        final List<VirtualDirectory> directories = virtualDirectoryService.findByRealmName(realmName);
        return virtualDirectoryMapper.toDTO(directories);
    }

}
