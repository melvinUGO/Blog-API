package com.melvin.blogapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.Instant;


@Data
@Entity
@Table(name = "Posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String imageUrl;

    @NotBlank(message = "Post cannot be blank")
    private String post;

    @NotBlank(message = "Author cannot be blank")
    private String Author;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp()
    private Instant updatedAt;

}
