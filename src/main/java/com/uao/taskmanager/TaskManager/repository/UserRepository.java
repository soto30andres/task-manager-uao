package com.uao.taskmanager.TaskManager.repository;

import com.uao.taskmanager.TaskManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String username);
}
