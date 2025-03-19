package app.com.constructionxpert.mapper;

import app.com.constructionxpert.dtos.AllocatedResourceDTO;
import app.com.constructionxpert.entity.AllocatedResource;
import org.mapstruct.BeanMapping;
import org.mapstruct.factory.Mappers;

public interface AllocatedResourceMapper {
    AllocatedResourceMapper INSTANCE = Mappers.getMapper(AllocatedResourceMapper.class);

    @BeanMapping
    AllocatedResourceDTO toDTO(AllocatedResource allocatedResource);
    AllocatedResource toEntity(AllocatedResourceDTO allocatedResourceDTO);
}
