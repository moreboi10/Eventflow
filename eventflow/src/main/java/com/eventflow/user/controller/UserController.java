package com.eventflow.user.controller;

import com.eventflow.user.dto.UserResponse;
import com.eventflow.user.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){

            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);

   }

   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping
   public ResponseEntity<List<UserResponse>> getAllUser(){

            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);

   }
}