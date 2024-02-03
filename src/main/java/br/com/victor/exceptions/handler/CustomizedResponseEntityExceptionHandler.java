package br.com.victor.exceptions.handler;

import java.time.Instant;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.victor.exceptions.ExceptionResponse;
import br.com.victor.exceptions.ObjectAlreadyExistsException;
import br.com.victor.exceptions.ObjectNotFoundException;
import br.com.victor.exceptions.OfflineServiceException;
import br.com.victor.exceptions.ValueTransactionInvalidException;
import br.com.victor.dto.DataErrorsValidationDTO;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleInternalServerErrorExceptions(Exception ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), Instant.now());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleObjectNotFoundExceptions(ObjectNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), Instant.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleObjectAlreadyExistsExceptions(ObjectAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), Instant.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorsValidationDTO>> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();

        List<DataErrorsValidationDTO> fieldsInvalid = erros.stream().map(e -> new DataErrorsValidationDTO(e)).toList();

        return new ResponseEntity<>(fieldsInvalid, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValueTransactionInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleValueTransactionInvalidExceptions(ValueTransactionInvalidException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), Instant.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OfflineServiceException.class)
    public ResponseEntity<ExceptionResponse> handleOfflineServiceExceptions(OfflineServiceException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getMessage(), Instant.now());

        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
