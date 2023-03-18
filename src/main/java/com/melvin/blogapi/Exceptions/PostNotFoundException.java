package com.melvin.blogapi.Exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(Integer id){
        super("The post with id:" +id+ " does not exist in our records");
    }
}
