package com.example.notify.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String Message)
    {
        super(Message);
    }
}
