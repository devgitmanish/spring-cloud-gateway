package com.security.jwt.cloud.service;

import com.security.jwt.cloud.config.CustomUserDetails;
import com.security.jwt.cloud.entity.UserCredentials;
import com.security.jwt.cloud.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> credentials=
                userCredentialsRepository.findByName(username);
        return credentials.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found with name "+username));
    }
}
