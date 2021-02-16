package com.carlosbotelho.curseprojec.services.exceptions;

public class DataIntegrityViolation extends RuntimeException{

    public DataIntegrityViolation(String msg){
        super(msg);
    }

    public DataIntegrityViolation(String msg, Throwable cause){
        super(msg, cause);
    }

}
