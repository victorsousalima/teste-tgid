package br.com.victor.exceptions;

import java.time.Instant;


public class ExceptionResponse {
    
    private Integer status;
    private String message;
    private Instant moment;

    public ExceptionResponse() {}

    public ExceptionResponse(Integer status, String message, Instant moment) {
        this.status = status;
        this.message = message;
        this.moment = moment;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Instant getMoment() {
        return moment;
    }
    
}
