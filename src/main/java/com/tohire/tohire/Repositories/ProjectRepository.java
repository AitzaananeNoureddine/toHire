package com.tohire.tohire.Repositories;

import com.tohire.tohire.Models.Project;

import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Integer> {
    
}
