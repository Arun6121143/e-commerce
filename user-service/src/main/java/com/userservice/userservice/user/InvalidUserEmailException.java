package com.userservice.userservice.user;

public class InvalidUserEmailException extends Exception{
    public InvalidUserEmailException(String message) {
        super(message);
    }
}
