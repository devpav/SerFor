package by.devpav.serfor.services;

import java.util.List;
import java.util.Optional;

public interface Service<T, ID> {

    List<T> findAll();
    T create(T entity);
    Optional<T> findById(ID id);

    void deleteById(ID id);
    void deleteByIds(ID[] ids);

}
