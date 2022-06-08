package com.raymond.spring.exercise.springsecurityinaction.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raymond.spring.exercise.springsecurityinaction.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findUserByUsername(String username);
}
