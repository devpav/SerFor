package by.devpav.serfor.services.impl;

import by.devpav.serfor.services.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.devpav.serfor.exceptions.ObjectThrow.requireNotNullThrow;
import static java.util.Objects.isNull;

public abstract class AbstractService<T, ID> implements Service<T, ID> {

    private JpaRepository<T, ID> jpaRepository;

    public AbstractService(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    @Transactional
    public T create(T entity) {
        requireNotNullThrow(entity, "Entity mustn't be is null [create]");
        return jpaRepository.save(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        requireNotNullThrow(entity, "Entity mustn't be is null [update]");
        return jpaRepository.save(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        requireNotNullThrow(id, "ID mustn't be is null [findById]");
        return jpaRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        requireNotNullThrow(id, "ID mustn't be is null [deleteById]");
        jpaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByIds(ID[] ids) {
        for (ID id : ids) this.deleteById(id);
    }

    @Override
    public T getReference(ID id) {
        return jpaRepository.getOne(id);
    }

    @Override
    public List<T> getReferences(ID[] ids) {
        if (isNull(ids)) return Collections.emptyList();
        return Stream.of(ids).filter(Objects::nonNull).map(jpaRepository::getOne).collect(Collectors.toList());
    }
}
