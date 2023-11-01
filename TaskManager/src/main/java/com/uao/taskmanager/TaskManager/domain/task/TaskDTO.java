package com.uao.taskmanager.TaskManager.domain.task;

import com.uao.taskmanager.TaskManager.entity.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {


  private Integer id;
  @NonNull private String description;
  @NonNull private State state;
}
