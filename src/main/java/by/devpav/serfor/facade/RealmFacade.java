package by.devpav.serfor.facade;

import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import by.devpav.serfor.domain.dtos.RealmDTO;

public interface RealmFacade extends BasicEntityFacade<RealmDTO> {

    public RealmConfigDTO getRealmConfig(String realmName);
    public RealmDTO getRealmByName(String realmName);

}
