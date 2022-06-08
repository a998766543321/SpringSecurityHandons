package com.raymond.spring.exercise.springsecurityinaction.services;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raymond.spring.exercise.springsecurityinaction.entities.User;
import com.raymond.spring.exercise.springsecurityinaction.models.CustomUserDetails;
import com.raymond.spring.exercise.springsecurityinaction.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authencation");

        User user = userRepository.findUserByUsername(username).orElseThrow(s);

        return new CustomUserDetails(user);
    }

}
