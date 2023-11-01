package com.uao.taskmanager.TaskManager.service;

import com.uao.taskmanager.TaskManager.domain.task.TaskDTO;
import com.uao.taskmanager.TaskManager.mapper.TaskMapper;
import com.uao.taskmanager.TaskManager.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private TaskMapper taskMapper;

  @Autowired
  private TaskRepository taskRepository;

  public List<TaskDTO> findTasksByUser() {

    var user = authenticationService.getCurrentuser();

    return taskRepository.findByUser(user).stream().map(taskMapper::buildDTO).toList();

  }
  public TaskDTO createTask(TaskDTO taskDTO) {
    var user = authenticationService.getCurrentuser();
    if(taskDTO.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Incorrect parameters");
    }
    var task = taskMapper.buildEntity(taskDTO);
    task.setUser(user);
    taskRepository.save(task);
    return taskMapper.buildDTO(task);

  }

  public TaskDTO updateTask(Integer taskId, TaskDTO taskDTO) {
    var user = authenticationService.getCurrentuser();

    var task = taskRepository.findById(taskId).get();

    if(taskDTO.getDescription().isEmpty()) {
      throw new IllegalArgumentException("Incorrect parameters");
    }

    task.setDescription(taskDTO.getDescription());
    task.setState(taskDTO.getState());
    taskRepository.save(task);
    return taskMapper.buildDTO(task);
  }

  public TaskDTO deleteTask(Integer taskId) {
    var user = authenticationService.getCurrentuser();
    var task = taskRepository.findById(taskId)
            .orElseThrow(() -> new EntityNotFoundException("Task not found."));

    if (!task.getUser().getId().equals(user.getId())) {
      throw new AccessDeniedException("Access denied. You are not the owner of this task.");
    }else{
      taskRepository.delete(task);
    }

    return taskMapper.buildDTO(task);
  }

}
