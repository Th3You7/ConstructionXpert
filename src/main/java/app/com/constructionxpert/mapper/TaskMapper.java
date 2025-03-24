package app.com.constructionxpert.mapper;

import app.com.constructionxpert.dtos.TaskDTO;
import app.com.constructionxpert.entity.Task;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;



@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    TaskDTO toDTO(Task task);
    Task toEntity(TaskDTO taskDTO);


}
