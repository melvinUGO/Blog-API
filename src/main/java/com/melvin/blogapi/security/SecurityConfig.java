package com.melvin.blogapi.security;

import com.melvin.blogapi.security.filter.AuthenticationFilter;
import com.melvin.blogapi.security.filter.ExceptionFilter;
import com.melvin.blogapi.security.filter.JwtAuthenticationFilter;
import com.melvin.blogapi.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    CustomAuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
        authenticationFilter.setFilterProcessesUrl("/api/v1/login");
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers("/api/v1/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ExceptionFilter(),authenticationFilter.getClass())
                .addFilter(authenticationFilter)
                .addFilterAfter(new JwtAuthenticationFilter(),authenticationFilter.getClass())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}
