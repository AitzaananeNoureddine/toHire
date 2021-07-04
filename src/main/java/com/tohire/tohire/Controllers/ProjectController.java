package com.tohire.tohire.Controllers;


import com.tohire.tohire.Models.Project;
import com.tohire.tohire.Repositories.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public Iterable<Project> projects(){
        return projectRepository.findAll();
    }
    
}
