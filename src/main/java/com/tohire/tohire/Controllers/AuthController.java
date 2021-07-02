package com.tohire.tohire.Controllers;


import com.tohire.tohire.Models.AuthRequest;
import com.tohire.tohire.Models.AuthResponse;
import com.tohire.tohire.Models.User;
import com.tohire.tohire.Repositories.UserRepository;
import com.tohire.tohire.Util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest){
        System.out.println(authRequest.getUsername()+" "+authRequest.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
            System.out.println("this line here1 !!!");
        } catch (BadCredentialsException e) {
            System.out.println("this line here2 !!!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        System.out.println("this line here !!!");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        System.out.println("userDetails: "+ userDetails);
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println("jwt: "+jwt);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody AuthRequest regRequest){
        System.out.println(regRequest.getUsername()+" "+regRequest.getPassword());
        try {
            userRepository.save(new User(regRequest.getUsername(),regRequest.getPassword()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return signIn(regRequest);
    }

}
