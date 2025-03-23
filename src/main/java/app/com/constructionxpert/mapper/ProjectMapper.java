package app.com.constructionxpert.mapper;

import app.com.constructionxpert.entity.Project;
import app.com.constructionxpert.dtos.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO toDto(Project project);
    Project toEntity(ProjectDTO projectDTO);
}
