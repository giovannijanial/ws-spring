package com.janial.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.janial.course.services.exceptions.DatabaseException;
import com.janial.course.services.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resouceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError er = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(er);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError er = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(er);
    }

}
