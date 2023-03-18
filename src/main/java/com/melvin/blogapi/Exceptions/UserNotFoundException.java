package com.melvin.blogapi.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id){
        super("The User with an id:"+id+" is not registered in the database");
    }
}
