package by.devpav.serfor.facade.mappers;

import java.util.List;

public interface Mapper<T, DTO> {

    T toEntity(DTO dto);
    DTO toDTO(T object);

    List<T> toEntity(List<DTO> dtos);
    List<DTO> toDTO(List<T> dtos);

}
