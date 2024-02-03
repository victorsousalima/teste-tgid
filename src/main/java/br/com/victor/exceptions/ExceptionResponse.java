package br.com.victor.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    
    private HttpStatus status;
    private String message;
    private String details;
    private Instant moment;

    public ExceptionResponse() {}

    public ExceptionResponse(HttpStatus status, String message, String details, Instant moment) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.moment = moment;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public Instant getMoment() {
        return moment;
    }
    
}
