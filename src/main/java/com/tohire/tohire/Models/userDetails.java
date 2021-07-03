package com.tohire.tohire.Models;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class userDetails implements UserDetails {

    private String username;
    private String password;
    private ArrayList<GrantedAuthority> authorities;

    public userDetails(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority("role1"));
        this.authorities.add(new SimpleGrantedAuthority("role2"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
