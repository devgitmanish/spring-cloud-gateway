package com.cloud.product.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok("all products collected");
    }
}
