package com.rail.app.exception;

import com.rail.app.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotAvailableException.class)
    public ResponseEntity<Error> handleResourceNotAvailableException(ResourceNotAvailableException ex){
        Error error=new Error(ex.errorMessage,ex.getClass().getName());
        return new ResponseEntity<Error>(error, ex.httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(Exception ex){
        Error error=new Error(ex.getMessage(),ex.getClass().getName());
        return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
