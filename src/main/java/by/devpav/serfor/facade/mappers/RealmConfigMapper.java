package by.devpav.serfor.facade.mappers;

import by.devpav.serfor.domain.RealmConfig;
import by.devpav.serfor.domain.dtos.RealmConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RealmConfigMapper extends BasicEntityMapper<RealmConfig, RealmConfigDTO> {

    @Override
    RealmConfig toEntity(RealmConfigDTO realmConfigDTO);

    @Override
    RealmConfigDTO toDTO(RealmConfig object);

}
