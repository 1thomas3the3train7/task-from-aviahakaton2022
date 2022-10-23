package com.example.tasksher.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerCustom {
    private ResponseEntity<Object> constructException(final String message,final HttpStatus httpStatus){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("Timestamp", LocalDateTime.now());
        body.put("Message",message);
        return new ResponseEntity<>(body,httpStatus);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {IncorrectJwtTokenException.class})
    public ResponseEntity<Object> handleIncorrectJwtTokenException(final Exception e){
        return constructException(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotValidRequestException.class})
    public ResponseEntity<Object> handleNotValidRequestException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
