package com.tohire.tohire.Security;

import java.util.Optional;

import com.tohire.tohire.Models.User;
import com.tohire.tohire.Models.userDetails;
import com.tohire.tohire.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException(username);
        return new userDetails(user.get());
    }
    
}
