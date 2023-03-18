package com.melvin.blogapi.security.manager;

import com.melvin.blogapi.Model.User;
import com.melvin.blogapi.Service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    UserServiceImpl userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = userService.findUser(authentication.getName());
        if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(),user.getPassword())){
            throw new BadCredentialsException("Password is not correct");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(),user.getPassword());
    }
}
