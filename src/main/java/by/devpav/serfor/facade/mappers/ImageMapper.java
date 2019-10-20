package by.devpav.serfor.facade.mappers;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.dtos.ImageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper extends BasicEntityMapper<Image, ImageDTO> {
}
