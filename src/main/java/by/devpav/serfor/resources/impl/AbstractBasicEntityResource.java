package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.dtos.BasicEntityDTO;
import by.devpav.serfor.facade.BasicEntityFacade;
import by.devpav.serfor.resources.BasicEntityResource;

public abstract class AbstractBasicEntityResource<DTO extends BasicEntityDTO> implements BasicEntityResource<DTO> {

    private BasicEntityFacade<DTO> basicEntityFacade;

    public AbstractBasicEntityResource(BasicEntityFacade<DTO> basicEntityFacade) {
        this.basicEntityFacade = basicEntityFacade;
    }

}
