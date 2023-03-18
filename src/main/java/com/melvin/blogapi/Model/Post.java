package com.melvin.blogapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private String title;
    private String imageUrl;
    private String post;
    private String Author;
    private Instant createdAt;
    private Instant updatedAt;
}
