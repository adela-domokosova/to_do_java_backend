//package com.example.testsb.controller;
//import com.example.testsb.entity.Task;
//import com.example.testsb.repository.TaskRepository;
//import com.example.testsb.service.JwtService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
//
//@RestController
//@RequestMapping(path = "/user")
//public class UserTaskController {
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private JwtService jwtService;
////    @Autowired
////    private Long myUser.id;
//
////    @GetMapping
////    public List<Task> getTasks(){
////        return taskRepository.findAll();
////    }
//
//    @GetMapping("/tasks")
//    public List<Task> getTasks(@RequestHeader("Authorization") String token) {
//        // Extract the user ID from the JWT token
//        String userId = jwtService.extractId(token.replace("Bearer ", ""));
//
//        // Fetch tasks for the specific user
//        List<Task> tasks = taskRepository.findByUserId(Long.parseLong(userId));
//
//        // Return the list of tasks as a response entity
//        return tasks;
//    }
//
//    @PostMapping("/add")
//    public  Task addTask (@Valid @RequestBody Task taskItem){ return taskRepository.save(taskItem);
//    }
//
//
//    //@Valid
//    @PutMapping("/update/{id}")
//    public Task putTask (@PathVariable Long id,  @RequestBody Task taskDetails) {
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
