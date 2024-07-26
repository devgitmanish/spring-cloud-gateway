package com.spring.cloud.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/productServiceFallback")
    public ResponseEntity<String> productServiceFallback(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("we are facing some problem. Please connect helpdesk");
    }
}
