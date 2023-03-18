package com.melvin.blogapi.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class User {

    Integer id;

    @NotBlank(message = "username cannot be blank")
    String username;

    @NotBlank(message = "password cannot be blank")
    String password;


}
