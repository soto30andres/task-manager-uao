package com.uao.taskmanager.TaskManager.service;

import com.uao.taskmanager.TaskManager.domain.authentication.JwtAuthenticationRS;
import com.uao.taskmanager.TaskManager.domain.authentication.SigninRQ;
import com.uao.taskmanager.TaskManager.domain.authentication.SignupRQ;
import com.uao.taskmanager.TaskManager.entity.Role;
import com.uao.taskmanager.TaskManager.entity.User;
import com.uao.taskmanager.TaskManager.repository.UserRepository;
import com.uao.taskmanager.TaskManager.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;

  public JwtAuthenticationRS signup(SignupRQ request) {
    var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
        .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER).build();
    userRepository.save(user);

    var jwt = jwtService.generateToken(user);
    return JwtAuthenticationRS.builder().token(jwt).build();

  }

  public JwtAuthenticationRS signin(SigninRQ request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    var jwt = jwtService.generateToken(user);
    return JwtAuthenticationRS.builder().token(jwt).build();
  }

  public User getCurrentuser() {
    Authentication auth = SecurityContextHolder
        .getContext()
        .getAuthentication();
    UserDetails userDetail = (UserDetails) auth.getPrincipal();

    return this.userRepository.findByEmail(userDetail.getUsername()).orElseThrow(() -> new UsernameNotFoundException("An error has ocurred getting user data"));

  }
}
