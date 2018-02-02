package com.foodorderingapp.exception;

/**
 * Created by TOPSHI KREATS on 2/2/2018.
 */
public class UserConflictException extends RuntimeException{
    public UserConflictException(String message) {
        super(message);
    }
}