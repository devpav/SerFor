package by.devpav.serfor.util;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static java.util.Objects.isNull;

public class HttpResponse {

    public static <T, C extends Collection<T>> ResponseEntity<C> responseCollection(C collection) {
        return (isNull(collection) || collection.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(collection);
    }

    public static <T> ResponseEntity<T> responseEntity(T entity) {
        return (isNull(entity)) ? ResponseEntity.noContent().build() : ResponseEntity.ok(entity);
    }

}
