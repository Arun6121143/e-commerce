package com.userservice.userservice.user;

public class UserWithUserIdNotFound extends Exception{
    public UserWithUserIdNotFound(String message) {
        super(message);
    }
}
