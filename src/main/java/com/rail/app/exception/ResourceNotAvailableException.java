package com.rail.app.exception;

public class ResourceNotAvailableException extends Exception{
    String errorMessage="";
    public ResourceNotAvailableException(String errorMessage){
        this.errorMessage=errorMessage;
    }
}