package com.userservice.userservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(InvalidUserEmailException.class)
    public ResponseEntity<String> userExceptionHandler(InvalidUserEmailException invalidUserEmailException){
       return new ResponseEntity<>(invalidUserEmailException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWithUserIdNotFound.class)
    public ResponseEntity<String> userWithIdNotFoundHandler(UserWithUserIdNotFound userWithUserIdNotFound){
        return new ResponseEntity<>(userWithUserIdNotFound.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}