package com.example.testsb.controller;


import com.example.testsb.entity.MyUser;
import com.example.testsb.entity.Task;
import com.example.testsb.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.testsb.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Controller
public class ContentController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/home")
    public String handleHome(){
        return "home";
    }
    @GetMapping("/user/home")
    public String handleUserHome(){
        return "home_admin";
    }
    @GetMapping("/admin/home")
    public String handleAdminHome(){
        return "home_user";
    }
    @GetMapping("/admin/users")
    public List<MyUser> getUsers(){
        return myUserRepository.findAll();}
//    @GetMapping("/tasks")
//    public List<Task> getTasks(){
//        return taskRepository.findAll();}
    }
