package app.com.constructionxpert.mapper;

import app.com.constructionxpert.entity.Assignment;
import org.mapstruct.factory.Mappers;

public interface AssignmentMapper {
    AssignmentMapper INSTANCE = Mappers.getMapper(AssignmentMapper.class);

    AssignmentMapper toDto(Assignment assignment);
    Assignment toEntity(Assignment assignment);
}
