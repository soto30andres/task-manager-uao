package com.uao.taskmanager.TaskManager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ping")
@CrossOrigin(origins = "http://localhost:5173")
public class PingController {

  @GetMapping
  public String ping() {

    return "ping";
  }
}
