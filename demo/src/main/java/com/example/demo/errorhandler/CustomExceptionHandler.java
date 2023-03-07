package com.example.demo.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "invalid parameter", 400),
                HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<ApiError> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), 404), HttpStatus.NOT_FOUND);
    }
}
