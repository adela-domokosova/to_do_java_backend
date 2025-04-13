package com.example.testsb.service;

import com.example.testsb.entity.Task;
import com.example.testsb.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TaskService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Task createTask(String title, String token) {
        String userId = jwtService.extractId(token.replace("Bearer ", ""));
        System.out.println(userId);
        Task task = new Task();
        task.setTitle(title);
        task.setUserId(Long.valueOf(userId));
        System.out.println(task.toString());
        return taskRepository.save(task);
    }
    public void setTaskDone(Long id){
        Task task = taskRepository.findTaskById(id).orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "asd"));
        if(task.isDone()) {
            task.setDone(false);
        }else{
            task.setDone(true);}
        taskRepository.save(task);
    }
}
