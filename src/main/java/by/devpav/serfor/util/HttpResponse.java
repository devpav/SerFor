package by.devpav.serfor.util;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Collection;

import static java.util.Objects.isNull;

public class HttpResponse {

    public static <T, C extends Collection<T>> ResponseEntity<C> responseCollection(C collection) {
        return (isNull(collection) || collection.isEmpty())
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(collection)
                : ResponseEntity.ok(collection);
    }

    public static <T> ResponseEntity<T> responseEntity(T entity) {
        return isNull(entity)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(entity);
    }

    public static <T> ResponseEntity<T> responseCreatedEntity(T entity) {
        return isNull(entity)
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    public static ResponseEntity<Resource> responseResource(Resource resource, String contentType) {
        long sizeResource = 0L;
        try {
            sizeResource = resource.getFile().length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .contentLength(sizeResource)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
