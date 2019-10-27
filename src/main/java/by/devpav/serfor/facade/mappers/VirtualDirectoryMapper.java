package by.devpav.serfor.facade.mappers;

import by.devpav.serfor.domain.VirtualDirectory;
import by.devpav.serfor.domain.dtos.VirtualDirectoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VirtualDirectoryMapper extends BasicEntityMapper<VirtualDirectory, VirtualDirectoryDTO> {

    @Override
    VirtualDirectory toEntity(VirtualDirectoryDTO virtualDirectoryDTO);

    @Override
    VirtualDirectoryDTO toDTO(VirtualDirectory object);

}
