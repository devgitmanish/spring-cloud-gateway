package com.security.jwt.cloud.service;

import com.security.jwt.cloud.entity.UserCredentials;
import com.security.jwt.cloud.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthencationService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredentials userCredentials){
        userCredentials.setPassword(encoder.encode(userCredentials.getPassword()));
        userCredentialsRepository.save(userCredentials);
        return "user added our records";
    }

    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
