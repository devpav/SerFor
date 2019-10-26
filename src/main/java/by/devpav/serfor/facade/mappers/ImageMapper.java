package by.devpav.serfor.facade.mappers;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.dtos.ImageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ImageMapper extends BasicEntityMapper<Image, ImageDTO> {

    @Override
    @Mappings({
            @Mapping(target = "virtualDirectory", ignore = true),
            @Mapping(target = "parentImage", ignore = true)
    })
    Image toEntity(ImageDTO imageDTO);

    @Override
    @Mappings({
            @Mapping(source = "virtualDirectory.name", target = "folder"),
            @Mapping(source = "parentImage.virtualName", target = "parent")
    })
    ImageDTO toDTO(Image object);

}
