package com.example.testsb.controller;

import com.example.testsb.entity.AuthRequest;
import com.example.testsb.entity.MyUser;
import com.example.testsb.entity.Task;
import com.example.testsb.repository.TaskRepository;
import com.example.testsb.service.JwtService;
import com.example.testsb.service.TaskService;
import com.example.testsb.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/auth")
public class UserController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserInfoService service;
    @Autowired
    private TaskService taskService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody MyUser userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
//    public ResponseEntity<List<Task>> getTasks(@RequestHeader("Authorization") String token) {
//        // Extract the user ID from the JWT token
//        String userId = jwtService.extractId(token.replace("Bearer ", ""));
//
//        // Fetch tasks for the specific user
//        List<Task> tasks = taskRepository.findByUserId(Long.parseLong(userId));
//
//        // Return the list of tasks as a response entity
//        return ResponseEntity.ok(tasks);
//    }


    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(authRequest.getName(), authRequest.getId(), authRequest.getEmail());
            } else {
                throw new UsernameNotFoundException("Invalid user request!");
            }
    }

    @GetMapping("/admin/tasks")
    public List<Task> getTasks(@RequestHeader("Authorization") String token) {

        System.out.println("hello");
//        return null;
        System.out.println(token);
        // Extract the user ID from the JWT token
        String userId = jwtService.extractId(token.replace("Bearer ", ""));
        System.out.println(userId);
        // Fetch tasks for the specific user
        List<Task> tasks = taskRepository.findByUserId(Long.parseLong(userId));

        // Return the list of tasks as a response entity
        return tasks;
    }

    @GetMapping("/tasks")
    public List<Task> getAnyTasks(@RequestHeader("Authorization") String token) {

        System.out.println("hello");
//        return null;
        System.out.println(token);
        // Extract the user ID from the JWT token
        String userId = jwtService.extractId(token.replace("Bearer ", ""));
        System.out.println(userId);
        // Fetch tasks for the specific user
        List<Task> tasks = taskRepository.findByUserId(Long.parseLong(userId));
        System.out.println(tasks.toString());
        // Return the list of tasks as a response entity
        return tasks;
    }
    @PostMapping("/add")
        public  Task addTask (@RequestHeader("Authorization") String token, @Valid @RequestBody String title){
        System.out.println("ADD");
        return taskService.createTask(title, token);
        }

        //delete
    @DeleteMapping("delete/{id}")
    public Task deleteTask (@PathVariable Long id){
        //jen když task vidi, tak ho muze deletnout-> to bude featura na frontendu
        Task task = taskRepository.findById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "asd"));
        taskRepository.delete(task);
        return task;
    }
    //done
    @PostMapping("done/{id}")
    public void setDoneTask (@PathVariable Long id){
                taskService.setTaskDone(id);

    }

}

