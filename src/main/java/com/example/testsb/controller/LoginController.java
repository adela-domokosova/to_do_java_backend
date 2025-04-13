package com.example.testsb.controller;

import com.example.testsb.entity.AuthResponse;
import com.example.testsb.entity.MyUser;
import com.example.testsb.repository.MyUserRepository;
import com.example.testsb.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
    @Autowired
    public MyUserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) throws IOException {
//        Optional<MyUser> user = repository.findByEmail(email);
        MyUser user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //špatné credencials nebo user neexistuje
        // Check if the password matches
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("Invalid credentials2");
            return ResponseEntity.ok("not OK");
        }

        String token = jwtService.generateToken(user.getUsername(), String.valueOf(user.getId()), user.getEmail());
        return ResponseEntity.ok().body(token);

    }



}
