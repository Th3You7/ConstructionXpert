package app.com.constructionxpert.mapper;

import app.com.constructionxpert.dtos.AssignmentDTO;
import app.com.constructionxpert.entity.Assignment;
import org.mapstruct.factory.Mappers;

public interface AssignmentMapper {
    AssignmentMapper INSTANCE = Mappers.getMapper(AssignmentMapper.class);

    AssignmentDTO toDto(Assignment assignment);
    Assignment toEntity(AssignmentDTO assignmentDTO);
}
