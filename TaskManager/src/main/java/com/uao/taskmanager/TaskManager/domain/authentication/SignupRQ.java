package com.uao.taskmanager.TaskManager.domain.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRQ {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
}
