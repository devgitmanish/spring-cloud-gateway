package com.cloud.book.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/getBooks")
    public ResponseEntity<?> getBooks(){
        return ResponseEntity.ok("all books collected");
    }
}
