package by.devpav.serfor.resources;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Resource<T, ID> {

    ResponseEntity<List<T>> findAll();
    ResponseEntity<T> create(T entity);
    ResponseEntity<T> findById(ID id);
    ResponseEntity<T> update(T entity);

    ResponseEntity<?> deleteById(ID id);
    ResponseEntity<?> deleteByIds(ID[] ids);

}
