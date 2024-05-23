package com.example.racingevent.security.controller;

import com.example.racingevent.security.domain.model.User;
import com.example.racingevent.security.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    protected HttpHeaders headers;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    @GetMapping
    public ResponseEntity<List<User>> get() {
        List<User> users = userService.read();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @PutMapping("/get-admin")
    public void getAdmin() {
        userService.getAdmin();
    }

    public UserService getService() {
        return userService;
    }
}
