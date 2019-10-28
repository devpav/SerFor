package by.devpav.serfor.facade.mappers;

import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.dtos.RealmDTO;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, uses = {
        VirtualDirectoryMapper.class,
        RealmConfigMapper.class
})
public interface RealmMapper extends BasicEntityMapper<Realm, RealmDTO> {

    @Override
    Realm toEntity(RealmDTO realmDTO);

    @Override
    RealmDTO toDTO(Realm object);

}
