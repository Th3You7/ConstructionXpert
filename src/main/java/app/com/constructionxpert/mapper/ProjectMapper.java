package app.com.constructionxpert.mapper;

import app.com.constructionxpert.Project;
import app.com.constructionxpert.dtos.ProjectDTO;
import org.mapstruct.factory.Mappers;

public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO toDto(Project project);
    Project toEntity(ProjectDTO projectDTO);
}
