package br.com.victor.exceptions.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.victor.exceptions.ExceptionResponse;
import br.com.victor.exceptions.ObjectAlreadyExistsException;
import br.com.victor.exceptions.ObjectNotFoundException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleInternalServerErrorExceptions(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false), Instant.now());

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleObjectNotFoundExceptions(ObjectNotFoundException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false), Instant.now());

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleObjectAlreadyExistsExceptions(ObjectAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false), Instant.now());

        return new ResponseEntity<>(response, response.getStatus());
    }
}
