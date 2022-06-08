package com.raymond.spring.exercise.springsecurityinaction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.raymond.spring.exercise.springsecurityinaction.models.CustomUserDetails;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {

            CustomUserDetails user = (CustomUserDetails) jpaUserDetailsService.loadUserByUsername(username);

            switch (user.getUser().getAlgorithm()) {
                case BCRYPT:
                    return checkPassword(user, password, bCryptPasswordEncoder);
                case SCRYPT:
                    return checkPassword(user, password, sCryptPasswordEncoder);
            }

        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }

        throw new BadCredentialsException("Bad Credential");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomUserDetails user,
            String rawPassword,
            PasswordEncoder encoder) throws BadCredentialsException {

        if (encoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad Credential");
        }
    }

}
