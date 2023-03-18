package com.melvin.blogapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank(message = "username cannot be blank")
    @NonNull
    @Column(nullable = false,unique = true)
    String username;

    @NotBlank(message = "password cannot be blank")
    @NonNull
    @Column(nullable = false)
    String password;



}
