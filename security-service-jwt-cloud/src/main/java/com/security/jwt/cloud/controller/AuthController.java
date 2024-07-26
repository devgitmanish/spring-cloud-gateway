package com.security.jwt.cloud.controller;

import com.security.jwt.cloud.dto.AuthRequest;
import com.security.jwt.cloud.entity.UserCredentials;
import com.security.jwt.cloud.service.AuthencationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthencationService authencationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody UserCredentials user){
        return ResponseEntity.ok(authencationService.saveUser(user));

    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                authRequest.getPassword()));
        if(authenticate.isAuthenticated()) {
            return ResponseEntity.ok(authencationService.generateToken(authRequest.getUsername()));
        }
        else {
            System.out.println("user invalid access");
            throw new RuntimeException("user invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        authencationService.validateToken(token);
        return "token validated successfully";
    }
}
