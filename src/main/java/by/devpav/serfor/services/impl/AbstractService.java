package by.devpav.serfor.services.impl;

import by.devpav.serfor.services.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.devpav.serfor.exceptions.ObjectThrow.requireNotNullThrow;

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

}
