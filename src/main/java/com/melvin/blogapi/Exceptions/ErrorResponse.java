package com.melvin.blogapi.Exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;



import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timeStamp;
    private List<String> message;

    public  ErrorResponse(List<String> message){
        this.timeStamp = LocalDateTime.now();
        this.message= message;
    }


}
