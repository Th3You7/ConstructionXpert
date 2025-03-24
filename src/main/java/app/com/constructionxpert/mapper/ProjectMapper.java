package app.com.constructionxpert.mapper;

import app.com.constructionxpert.dtos.TaskDTO;
import app.com.constructionxpert.entity.Project;
import app.com.constructionxpert.dtos.ProjectDTO;
import app.com.constructionxpert.entity.Task;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO toDto(Project project);
    Project toEntity(ProjectDTO projectDTO);
    @AfterMapping()
    default Set<TaskDTO> mapTasks(Project project, @MappingTarget ProjectMapper projectMapper) {
        if (project.getTasks() == null) {
            return Collections.emptySet();
        }
        return project.getTasks().stream()
                .map(TaskMapper.INSTANCE::toDTO) // Use TaskMapper to map each Task to TaskDTO
                .collect(Collectors.toSet());
    }
}
