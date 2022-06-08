package com.raymond.spring.exercise.springsecurityinaction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raymond.spring.exercise.springsecurityinaction.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
