package com.uao.taskmanager.TaskManager.controller;

import com.uao.taskmanager.TaskManager.domain.authentication.JwtAuthenticationRS;
import com.uao.taskmanager.TaskManager.domain.authentication.SigninRQ;
import com.uao.taskmanager.TaskManager.domain.authentication.SignupRQ;
import com.uao.taskmanager.TaskManager.domain.task.TaskDTO;
import com.uao.taskmanager.TaskManager.service.AuthenticationService;
import com.uao.taskmanager.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("v1/task")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @GetMapping
  public ResponseEntity<List<TaskDTO>> findTasksByUser() {
    return ResponseEntity.ok(taskService.findTasksByUser());
  }

  @PostMapping
  public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
    return ResponseEntity.ok(taskService.createTask(taskDTO));
  }

  @PutMapping("{id}")
  public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable Integer id) {
    return ResponseEntity.ok(taskService.updateTask(id, taskDTO));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<TaskDTO> deleteTask(@RequestBody TaskDTO taskDTO, @PathVariable Integer id) {
    return ResponseEntity.ok(taskService.deleteTask(id));
  }

}
