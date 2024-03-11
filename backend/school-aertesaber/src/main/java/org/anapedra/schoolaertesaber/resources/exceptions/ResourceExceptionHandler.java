package org.anapedra.schoolaertesaber.resources.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.anapedra.schoolaertesaber.services.exceptions.DataBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler{

/*
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status= HttpStatus.NOT_FOUND;
        StandardError errer=new StandardError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errer);
    }



 */

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status= HttpStatus.BAD_REQUEST;
        StandardError errer=new StandardError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errer);
    }
    /*

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        String error = "Validation Exception";
        HttpStatus status= HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError errer=new ValidationError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());

        for(FieldError f:e.getBindingResult().getFieldErrors()){
            errer.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(errer);
    }
    @ExceptionHandler(ForbiddenException.class )
    public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e, HttpServletRequest request){
        OAuthCustomError  errer=new OAuthCustomError("Forbidden", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errer);
    }
    @ExceptionHandler(UnauthorizedException.class )
    public ResponseEntity<OAuthCustomError> Unauthorized(UnauthorizedException e, HttpServletRequest request){
        OAuthCustomError  errer=new OAuthCustomError("Unauthorized", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errer);
    }

     */
}
