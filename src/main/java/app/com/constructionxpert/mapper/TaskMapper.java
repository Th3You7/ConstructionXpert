package app.com.constructionxpert.mapper;

import app.com.constructionxpert.dtos.TaskDTO;
import app.com.constructionxpert.entity.Task;
import org.mapstruct.factory.Mappers;

public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO toDTO(Task task);
    Task toEntity(TaskDTO taskDTO);
}
