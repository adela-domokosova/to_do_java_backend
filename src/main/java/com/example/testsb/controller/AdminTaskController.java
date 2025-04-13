//package com.example.testsb.controller;
//
//import com.example.testsb.entity.Task;
//import com.example.testsb.repository.TaskRepository;
//import com.example.testsb.service.JwtService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping(path = "/admin")
//public class AdminTaskController {
//
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @GetMapping
//    public List<Task> getTasks(){
//        return null;
//    }
//
////@GetMapping("/tasks")
////public ResponseEntity<List<Task>> getTasks(@RequestHeader("Authorization") String token) {
////    System.out.println(token);
////        // Extract the user ID from the JWT token
////    String userId = jwtService.extractId(token.replace("Bearer ", ""));
////    System.out.println(userId);
////    // Fetch tasks for the specific user
////    List<Task> tasks = taskRepository.findByUserId(Long.parseLong(userId));
////
////    // Return the list of tasks as a response entity
////    return ResponseEntity.ok(tasks);
////}
//
//    @PostMapping("/add")
//    public  Task addTask (@Valid @RequestBody Task taskItem){
//        System.out.println("HEREEEEE");
//    return taskRepository.save(taskItem);
//    }
//
//
//    //@Valid
//    @PutMapping("/update/{id}")
//    public Task putTask (@PathVariable Long id, @RequestBody Task taskDetails) {
//        Task taskItem = taskRepository.findById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", taskDetails, "asd"));
//        taskItem.setTitle(taskDetails.getTitle());
//        taskItem.setDone(taskDetails.isDone());
//        return taskRepository.save(taskItem);}
//
//    @DeleteMapping("delete/{id}")
//    public Task deleteTask (@PathVariable Long id){
//        Task task = taskRepository.findById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "asd"));
//        taskRepository.delete(task);
//        return task;
//    }
//}
