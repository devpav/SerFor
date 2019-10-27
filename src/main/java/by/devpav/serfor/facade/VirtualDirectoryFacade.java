package by.devpav.serfor.facade;

import by.devpav.serfor.domain.dtos.VirtualDirectoryDTO;

import java.util.List;

public interface VirtualDirectoryFacade extends BasicEntityFacade<VirtualDirectoryDTO> {

    public List<VirtualDirectoryDTO> findByRealmName(String realmName);

}
