package com.raymond.spring.exercise.springsecurityinaction.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raymond.spring.exercise.springsecurityinaction.entities.Product;
import com.raymond.spring.exercise.springsecurityinaction.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
