package com.example.testsb.entity;

public class AuthResponse {
    private String token;
    private String message;

    public AuthResponse(String token, String message) {

        this.token = token;
        this.message = message;
    }

    // Getters and Setters
}
