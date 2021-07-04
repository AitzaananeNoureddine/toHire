package com.tohire.tohire.Repositories;

import com.tohire.tohire.Models.Task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Integer>{
    
}
