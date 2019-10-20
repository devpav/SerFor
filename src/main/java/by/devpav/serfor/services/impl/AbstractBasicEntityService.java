package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.BasicEntity;
import by.devpav.serfor.repository.BasicEntityRepository;
import by.devpav.serfor.services.BasicEntityService;

import static by.devpav.serfor.exceptions.ObjectThrow.entityNotFoundThrow;
import static by.devpav.serfor.exceptions.ObjectThrow.requireNotNullThrow;

public abstract class AbstractBasicEntityService<T extends BasicEntity>
        extends AbstractService<T, Long> implements BasicEntityService<T> {

    private BasicEntityRepository<T> basicEntityRepository;

    public AbstractBasicEntityService(BasicEntityRepository<T> basicEntityRepository) {
        super(basicEntityRepository);
        this.basicEntityRepository = basicEntityRepository;
    }

    @Override
    public T update(T entity) {
        requireNotNullThrow(entity, "Entity mustn't be is null [update]");
        requireNotNullThrow(entity.getId(), "Entity ID mustn't be is null [update]");

        final boolean isExistsById = basicEntityRepository.existsById(entity.getId());

        entityNotFoundThrow(isExistsById, "Entity not found by id [update]");

        return basicEntityRepository.save(entity);
    }
}
