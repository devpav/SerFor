package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.dtos.BasicEntityDTO;
import by.devpav.serfor.facade.BasicEntityFacade;
import by.devpav.serfor.resources.BasicEntityResource;
import by.devpav.serfor.util.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class AbstractBasicEntityResource<DTO extends BasicEntityDTO> implements BasicEntityResource<DTO> {

    private BasicEntityFacade<DTO> basicEntityFacade;

    public AbstractBasicEntityResource(BasicEntityFacade<DTO> basicEntityFacade) {
        this.basicEntityFacade = basicEntityFacade;
    }


    @Override
    @GetMapping
    public ResponseEntity<List<DTO>> findAll() {
        final List<DTO> entities = basicEntityFacade.findAll();
        return HttpResponse.responseCollection(entities);
    }

    @Override
    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTO entity) {
        final DTO createdEntity = basicEntityFacade.create(entity);
        return HttpResponse.responseCreatedEntity(createdEntity);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable("id") Long id) {
        final Optional<DTO> foundEntity = basicEntityFacade.findById(id);
        return HttpResponse.responseEntity(foundEntity.orElse(null));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        basicEntityFacade.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/[{ids}]")
    public ResponseEntity<?> deleteByIds(@PathVariable("ids") Long[] ids) {
        basicEntityFacade.deleteByIds(ids);
        return ResponseEntity.ok().build();
    }

}
