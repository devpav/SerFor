package by.devpav.serfor.facade.mappers;

import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.domain.dtos.DirectoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectoryMapper extends BasicEntityMapper<Directory, DirectoryDTO> {

    @Override
    Directory toEntity(DirectoryDTO directoryDTO);

    @Override
    DirectoryDTO toDTO(Directory object);

}
