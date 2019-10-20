package by.devpav.serfor.facade;

import java.util.List;
import java.util.Optional;

public interface Facade<DTO, ID> {

    List<DTO> findAll();
    DTO create(DTO entity);
    Optional<DTO> findById(ID id);

    void deleteById(ID id);
    void deleteByIds(ID[] ids);

}
