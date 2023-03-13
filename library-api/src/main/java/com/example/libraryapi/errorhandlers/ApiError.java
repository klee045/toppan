package com.example.libraryapi.errorhandlers;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private String message;
    private int statusCode;

    public ApiError() {
        super();
    }

    public ApiError(HttpStatus status, String message, int statusCode) {
        super();
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
