package com.example.demo.Exception;

public class EditException extends RuntimeException{
    public EditException(){}
    public EditException(String message){
        super(message);
    }
    public EditException(String message , Throwable cause){
        super(message, cause);
    }
}
