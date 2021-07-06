package com.tohire.tohire.Controllers;

import com.tohire.tohire.Models.Task;
import com.tohire.tohire.Repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/task")
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public Iterable<Task> projects(){
        return taskRepository.findAll();
    }

}
