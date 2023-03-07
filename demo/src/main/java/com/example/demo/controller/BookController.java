package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.example.demo.errorhandler.ApiError;
import com.example.demo.errorhandler.BadRequestException;
import com.example.demo.repository.BookRepository;

import jakarta.persistence.NoResultException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/getTop3ReadBooks")
    public ResponseEntity<List<Map<String, Object>>> getTop3ReadBooks(
            @RequestParam(value = "country_code", required = false) String countryCode)
            throws BadRequestException, NoResultException {
        System.out.println("GET /getTop3ReadBooks");
        System.out.println("@@@@ countryCode = " + countryCode);
        Long countryId = null;

        // Get the corresponding countryId for the input countryCode if not null
        if (countryCode != null) {
            switch (countryCode.toUpperCase()) {
            case "SG":
                countryId = Long.valueOf(702);
                break;
            case "MY":
                countryId = Long.valueOf(458);
                break;
            case "US":
                countryId = Long.valueOf(840);
                break;
            default:
                System.out.println("BAD REQUEST");
                throw new BadRequestException("invalid parameter");
            }
        }
        System.out.println("@@@@@@@@ countryId " + countryId);
        // Call the query and add the result into the books array
        List<Map<String, Object>> books = new ArrayList<Map<String, Object>>(
                bookRepository.getTop3BorrowedBooksInCountryAndTop3BorrowersWithinCountry(countryId));
        System.out.println("@@@@@@@@ books " + books);

        if (books.isEmpty()) {
            throw new NoResultException("no results");
        }

        // Format the response

        return new ResponseEntity<>(books, HttpStatus.OK);
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
