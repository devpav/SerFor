package by.devpav.serfor.facade.impl;

import by.devpav.serfor.domain.BasicEntity;
import by.devpav.serfor.domain.dtos.BasicEntityDTO;
import by.devpav.serfor.facade.BasicEntityFacade;
import by.devpav.serfor.facade.mappers.BasicEntityMapper;
import by.devpav.serfor.services.BasicEntityService;

public abstract class AbstractBasicEntityFacade<T extends BasicEntity, DTO extends BasicEntityDTO>
        extends AbstractFacade<T, DTO, Long> implements BasicEntityFacade<DTO> {

    public AbstractBasicEntityFacade(BasicEntityService<T> basicEntityService,
                                     BasicEntityMapper<T, DTO> basicEntityMapper) {
        super(basicEntityService, basicEntityMapper);
    }

}
