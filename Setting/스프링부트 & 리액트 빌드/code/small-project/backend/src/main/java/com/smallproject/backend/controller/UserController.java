package com.smallproject.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @GetMapping("/list")
    public ResponseEntity<List<String>> getUsers(){
        List<String> usersList = new ArrayList<>();
        usersList.add("제임스");
        usersList.add("강아지");
        usersList.add("고양이");
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}