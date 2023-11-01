package com.uao.taskmanager.TaskManager.mapper;

import com.uao.taskmanager.TaskManager.domain.task.TaskDTO;
import com.uao.taskmanager.TaskManager.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO buildDTO(Task task);
    Task buildEntity(TaskDTO taskDTO);

}
