package com.example.libraryapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryapi.errorhandlers.ApiError;
import com.example.libraryapi.errorhandlers.BadRequestException;
import com.example.libraryapi.errorhandlers.NoResultException;
import com.example.libraryapi.services.BookService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/getTop3ReadBooks")
    public ResponseEntity<List<Object>> getTop3ReadBooks(
            @RequestParam(value = "country_code", required = false) String countryCode)
            throws BadRequestException, NoResultException {
        List<Object> result = bookService.getTop3ReadBooks(countryCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
     * Exception Handlers
     * 1. Invalid Parameter when country code is invalid
     * 2. No Result when book array is empty
     * 3. Catch all other exceptions
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "invalid parameter", 400),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResultException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    protected ResponseEntity<ApiError> handleNoResultException(NoResultException e) {
        return new ResponseEntity<>(new ApiError(HttpStatus.OK, "no results", 200), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiError> handleException(Exception e) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, "resource not found", 404),
                HttpStatus.NOT_FOUND);
    }
}
