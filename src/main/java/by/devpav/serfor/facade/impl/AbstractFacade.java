package by.devpav.serfor.facade.impl;

import by.devpav.serfor.facade.Facade;
import by.devpav.serfor.facade.mappers.Mapper;
import by.devpav.serfor.services.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractFacade<T, DTO, ID> implements Facade<DTO, ID> {

    private Service<T, ID> service;
    private Mapper<T, DTO> mapper;

    public AbstractFacade(Service<T, ID> service, Mapper<T, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @Override
    public List<DTO> findAll() {
        return service.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DTO create(DTO entity) {
        final T mappedEntity = mapper.toEntity(entity);
        final T createdEntity = service.create(mappedEntity);
        return mapper.toDTO(createdEntity);
    }

    @Override
    public Optional<DTO> findById(ID id) {
        final Optional<T> foundOptionalEntity = service.findById(id);
        return foundOptionalEntity.map(mapper::toDTO);
    }

    @Override
    public void deleteById(ID id) {
        service.deleteById(id);
    }

    @Override
    public void deleteByIds(ID[] ids) {
        service.deleteByIds(ids);
    }

}
