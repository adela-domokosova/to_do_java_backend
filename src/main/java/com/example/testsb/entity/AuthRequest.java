package com.example.testsb.entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String id;
    private String email;
    private String name;
    private String password;

}