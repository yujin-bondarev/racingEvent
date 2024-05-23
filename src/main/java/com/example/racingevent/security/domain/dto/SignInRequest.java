package com.example.racingevent.security.domain.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
