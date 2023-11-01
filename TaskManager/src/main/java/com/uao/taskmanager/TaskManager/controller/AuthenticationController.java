package com.uao.taskmanager.TaskManager.controller;

import com.uao.taskmanager.TaskManager.domain.authentication.JwtAuthenticationRS;
import com.uao.taskmanager.TaskManager.domain.authentication.SigninRQ;
import com.uao.taskmanager.TaskManager.domain.authentication.SignupRQ;
import com.uao.taskmanager.TaskManager.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("v1/authentication")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;


  @PostMapping("signin")
  public ResponseEntity<JwtAuthenticationRS> signin(@RequestBody SigninRQ signinRQ) {

    return ResponseEntity.ok(authenticationService.signin(signinRQ));

  }

  @PostMapping("signup")
  public ResponseEntity<JwtAuthenticationRS> signUp(@RequestBody SignupRQ signupRQ) {

    return ResponseEntity.ok(authenticationService.signup(signupRQ));
  }
}
