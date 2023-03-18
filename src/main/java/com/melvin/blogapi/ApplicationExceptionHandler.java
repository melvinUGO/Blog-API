package com.melvin.blogapi;

import com.melvin.blogapi.Exceptions.ErrorResponse;
import com.melvin.blogapi.Exceptions.PostNotFoundException;
import com.melvin.blogapi.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PostNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException exception){
        ErrorResponse error = new ErrorResponse(Arrays.asList(exception.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
}
