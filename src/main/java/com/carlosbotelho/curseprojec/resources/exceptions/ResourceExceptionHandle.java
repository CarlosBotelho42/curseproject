package com.carlosbotelho.curseprojec.resources.exceptions;

import com.carlosbotelho.curseprojec.services.exceptions.DataIntegrityViolation;
import com.carlosbotelho.curseprojec.services.exceptions.ObjectNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(ObjectNotfoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotfoundException e, HttpServletRequest request){

        //404
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DataIntegrityViolation.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolation e, HttpServletRequest request){

        //400
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){

        //400
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação!", System.currentTimeMillis());

        for(FieldError x : e.getBindingResult().getFieldErrors()){
            error.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

}
