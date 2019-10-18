package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.BasicEntity;
import by.devpav.serfor.repository.BasicEntityRepository;
import by.devpav.serfor.services.BasicEntityService;

public abstract class AbstractBasicEntityService<T extends BasicEntity>
        extends AbstractService<T, Long> implements BasicEntityService<T> {

    public AbstractBasicEntityService(BasicEntityRepository<T> basicEntityRepository) {
        super(basicEntityRepository);
    }

}
