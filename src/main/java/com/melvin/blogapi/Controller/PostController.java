package com.melvin.blogapi.Controller;

import com.melvin.blogapi.Entity.PostEntity;
import com.melvin.blogapi.Model.Post;
import com.melvin.blogapi.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin("http://127.0.0.1:5173")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<List<PostEntity>> getAllPosts(){
        List<PostEntity> posts = postService.getAllPost();

        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostEntity> getPost(@PathVariable Integer id){
        PostEntity post = postService.getPost(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post newPost = postService.createPost(post);
        return new ResponseEntity<>(newPost,HttpStatus.CREATED);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post post){
        Post updatedPost = postService.updatePost(id,post);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Integer id){
        Post post = postService.deletePost(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
}
