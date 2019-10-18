package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.BasicEntity;
import by.devpav.serfor.resources.BasicEntityResource;
import by.devpav.serfor.services.BasicEntityService;
import by.devpav.serfor.util.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
public abstract class AbstractBasicEntityResource<T extends BasicEntity> implements BasicEntityResource<T> {

    private BasicEntityService<T> basicEntityResource;

    public AbstractBasicEntityResource(BasicEntityService<T> basicEntityResource) {
        this.basicEntityResource = basicEntityResource;
    }


    @Override
    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        final List<T> entities = basicEntityResource.findAll();
        return HttpResponse.responseCollection(entities);
    }

    @Override
    @PostMapping
    public ResponseEntity<T> create(T entity) {
        final T savedEntity = basicEntityResource.create(entity);
        return HttpResponse.responseEntity(savedEntity);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable("id") Long id) {
        final Optional<T> foundEntity = basicEntityResource.findById(id);
        return HttpResponse.responseEntity(foundEntity.orElse(null));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        basicEntityResource.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/[{ids}]")
    public ResponseEntity<?> deleteByIds(@PathVariable("ids") Long[] ids) {
        basicEntityResource.deleteByIds(ids);
        return ResponseEntity.ok().build();
    }

}
