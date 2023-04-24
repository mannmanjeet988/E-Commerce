package com.example.Ecommerce.Exceptions;

public class EmailAlreadyPresentException extends Exception{

    public EmailAlreadyPresentException(String message){
        super(message);
    }
}
