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
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@Configuration
@AllArgsConstructor
public class SecurityConfig {

    CustomAuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);

        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers("/api/v1/user/register").permitAll()
                .requestMatchers(HttpMethod.POST,"/authenticate").permitAll()
                .requestMatchers(HttpMethod.PATCH).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(corsFilter(), CsrfFilter.class)
                .addFilterBefore(new ExceptionFilter(),authenticationFilter.getClass())
                .addFilter(authenticationFilter)
                .addFilterAfter(new JwtAuthenticationFilter(),authenticationFilter.getClass())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("https://localhost:5173"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        source.registerCorsConfiguration("/**",configuration);
        return new CorsFilter(source);
    }


}
