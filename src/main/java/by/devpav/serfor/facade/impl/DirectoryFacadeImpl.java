package by.devpav.serfor.facade.impl;

import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.domain.dtos.DirectoryDTO;
import by.devpav.serfor.facade.DirectoryFacade;
import by.devpav.serfor.facade.mappers.DirectoryMapper;
import by.devpav.serfor.services.DirectoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectoryFacadeImpl extends AbstractBasicEntityFacade<Directory, DirectoryDTO> implements DirectoryFacade {

    private final DirectoryService directoryService;
    private final DirectoryMapper directoryMapper;

    public DirectoryFacadeImpl(DirectoryService directoryService,
                               DirectoryMapper directoryMapper) {
        super(directoryService, directoryMapper);
        this.directoryService = directoryService;
        this.directoryMapper = directoryMapper;
    }

    @Override
    public List<DirectoryDTO> findByRealmName(String realmName) {
        final List<Directory> directories = directoryService.findByRealmName(realmName);
        return directoryMapper.toDTO(directories);
    }
}
