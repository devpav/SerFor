package by.devpav.serfor.facade.mappers;

import by.devpav.serfor.domain.BasicEntity;
import by.devpav.serfor.domain.dtos.BasicEntityDTO;

public interface BasicEntityMapper<T extends BasicEntity, DTO extends BasicEntityDTO> extends Mapper<T, DTO> {
}
