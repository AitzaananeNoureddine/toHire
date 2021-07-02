package com.tohire.tohire.Repositories;

import java.util.Optional;

import com.tohire.tohire.Models.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
