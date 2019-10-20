package by.devpav.serfor.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static java.util.Objects.isNull;

public class HttpResponse {

    public static <T, C extends Collection<T>> ResponseEntity<C> responseCollection(C collection) {
        return (isNull(collection) || collection.isEmpty())
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(collection)
                : ResponseEntity.ok(collection);
    }

    public static <T> ResponseEntity<T> responseEntity(T entity) {
        return (isNull(entity)) ? ResponseEntity.noContent().build() : ResponseEntity.ok(entity);
    }

    public static <T> ResponseEntity<T> responseCreatedEntity(T entity) {
        return (isNull(entity))
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

}
