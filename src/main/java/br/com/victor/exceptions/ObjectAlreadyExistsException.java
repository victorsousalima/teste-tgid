package br.com.victor.exceptions;

public class ObjectAlreadyExistsException extends RuntimeException{

    public ObjectAlreadyExistsException(String msg) {
        super(msg);
    }
    
}
