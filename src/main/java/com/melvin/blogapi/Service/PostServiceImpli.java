package com.melvin.blogapi.Service;

import com.melvin.blogapi.Entity.PostEntity;
import com.melvin.blogapi.Exceptions.PostNotFoundException;
import com.melvin.blogapi.Model.Post;
import com.melvin.blogapi.Repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpli implements PostService{

    @Autowired
    PostRepository postRepository;

    @Override
    public List<PostEntity> getAllPost() {
        return (List<PostEntity>) postRepository.findAll();
    }

    @Override
    public PostEntity getPost(Integer id) {
        Optional<PostEntity> post = postRepository.findById(id);
        return unWrap(post,id);
    }

    @Override
    public Post createPost(Post post) {
        PostEntity postEntity = new PostEntity();
        BeanUtils.copyProperties(post,postEntity);
        postRepository.save(postEntity);
        return post;
    }

    @Override
    public Post updatePost(Integer id,Post post) {
        PostEntity postEntity = unWrap(postRepository.findById(id),id);
        postEntity.setPost(post.getPost());
        postEntity.setTitle(post.getTitle());
        postEntity.setImageUrl(post.getImageUrl());
        postEntity.setAuthor(post.getAuthor());
        postEntity.setUpdatedAt(post.getCreatedAt());

        postRepository.save(postEntity);
        return post;
    }

    @Override
    public Post deletePost(Integer id) {
        Post post = new Post();
        PostEntity postEntity = unWrap(postRepository.findById(id),id);
        BeanUtils.copyProperties(postEntity,post);
        postRepository.delete(postEntity);
        return post;
    }

    public PostEntity unWrap(Optional<PostEntity> post, Integer id){
        if(post.isPresent()){
            return post.get();
       }else{
           throw new PostNotFoundException(id);
       }
    }

}
