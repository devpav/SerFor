package by.devpav.serfor.facade.mappers;

import by.devpav.serfor.domain.RealmConfig;
import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RealmConfigMapper extends BasicEntityMapper<RealmConfig, RealmConfigDTO> {
}
