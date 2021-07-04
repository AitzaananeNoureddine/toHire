package com.tohire.tohire.Security;

import com.tohire.tohire.Filters.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .formLogin().disable()
            .cors().and().csrf().disable()
            .authorizeRequests()
                    // .antMatchers("/r1").hasRole("r1")
                    // .antMatchers("/r2").hasAnyRole("ADMIN", "USER")
                    // .antMatchers("/").permitAll()
                    .antMatchers("/api/auth/signin").permitAll()
                    .antMatchers("/api/auth/signup").permitAll()
                    .anyRequest().authenticated()
                    // .anyRequest().permitAll()
                    .and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // return NoOpPasswordEncoder.getInstance(); /// only during dev
        return new BCryptPasswordEncoder();
    }

}
