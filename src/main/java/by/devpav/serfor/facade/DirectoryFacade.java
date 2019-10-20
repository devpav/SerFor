package by.devpav.serfor.facade;

import by.devpav.serfor.domain.dtos.DirectoryDTO;

import java.util.List;

public interface DirectoryFacade extends BasicEntityFacade<DirectoryDTO> {

    public List<DirectoryDTO> findByRealmName(String realmName);

}
