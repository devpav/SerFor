package by.devpav.serfor.facade.mappers;

import by.devpav.serfor.domain.RealmConfig;
import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RealmConfigMapper extends BasicEntityMapper<RealmConfig, RealmConfigDTO> {

    @Override
    RealmConfig toEntity(RealmConfigDTO realmConfigDTO);

    @Override
    RealmConfigDTO toDTO(RealmConfig object);

}
