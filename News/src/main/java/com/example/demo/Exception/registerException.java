package com.example.demo.Exception;

public class registerException extends RuntimeException{
    public registerException(){}
    public registerException(String message){
        super(message);
    }
    public registerException(String message , Throwable cause){
        super(message, cause);
    }
}
