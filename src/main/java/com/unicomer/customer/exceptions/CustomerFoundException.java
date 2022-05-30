package com.unicomer.customer.exceptions;

public class CustomerFoundException extends RuntimeException{
    public CustomerFoundException(String message, Exception e){
        super(message, e);
    }

    public CustomerFoundException(String message){
        super(message);
    }
}
