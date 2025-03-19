package app.com.constructionxpert.mapper;

import app.com.constructionxpert.dtos.ResourceDTO;
import app.com.constructionxpert.entity.Resource;
import org.mapstruct.factory.Mappers;

public interface ResourceMapper {
    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    ResourceDTO toDTO(Resource resource);
    Resource toEntity(ResourceDTO resourceDTO);
}
