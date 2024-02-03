package br.com.victor.dto;

import org.springframework.validation.FieldError;

public record DataErrorsValidationDTO(String field, String message) {
    
    public DataErrorsValidationDTO(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }

}
