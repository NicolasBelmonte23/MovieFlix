package com.movieflix.exception;

public class UsernameOrPasswordIdException extends RuntimeException {
    public UsernameOrPasswordIdException(String message){
        super(message);
    }
}
