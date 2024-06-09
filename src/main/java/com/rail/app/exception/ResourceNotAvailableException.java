package com.rail.app.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotAvailableException extends Exception{
    String errorMessage="";
    HttpStatus httpStatus=HttpStatus.NOT_FOUND;

    public ResourceNotAvailableException(String errorMessage){
        this.errorMessage=errorMessage;
    }

    public ResourceNotAvailableException(String errorMessage,HttpStatus httpStatus){
        this.errorMessage=errorMessage;
        this.httpStatus=httpStatus;
    }
}