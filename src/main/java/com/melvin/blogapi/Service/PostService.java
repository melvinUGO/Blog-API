package com.melvin.blogapi.Service;

import com.melvin.blogapi.Entity.PostEntity;
import com.melvin.blogapi.Model.Post;

import java.util.List;

public interface PostService {

    List<PostEntity> getAllPost();

    PostEntity getPost(Integer id);

    Post createPost(Post post);

    Post updatePost(Integer id, Post post);

    Post deletePost(Integer id);
}
