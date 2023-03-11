package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/getTop3ReadBooks")
    public ResponseEntity<List<Object>> getTop3ReadBooks(
            @RequestParam(value = "country_code", required = false) String countryCode)
            throws BadRequestException, NoResultException {
        Long countryId = null;

        // Get the corresponding countryId for the input countryCode if not null
        if (countryCode != null) {
            if (countryCode.toUpperCase().equals("SG")) {
                countryId = Long.valueOf(702);
            } else if (countryCode.toUpperCase().equals("MY")) {
                countryId = Long.valueOf(458);
            } else if (countryCode.toUpperCase().equals("US")) {
                countryId = Long.valueOf(840);
            } else {
                throw new BadRequestException("invalid parameter");
            }
        }
        // Call the query and add the result into the books array
        List<Map<String, Object>> books = new ArrayList<Map<String, Object>>(
                bookRepository.getTop3BorrowedBooksInCountryAndTop3BorrowersWithinCountry(countryId));

        if (books.isEmpty()) {
            throw new NoResultException("no results");
        }

        // Format the response
        List<Object> result = formatTop3Response(countryCode, books);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Helper Formatter Function
    public List<Object> formatTop3Response(String countryCode, List<Map<String, Object>> books) {
        Map<String, Object> hashMap = new HashMap<>();
        List<Object> result = null;
        if (countryCode != null) {
            // use a hashmap to mimic a set, in the required format
            // and if book is already in hashmap, extend the "borrower" array
            for (Map<String, Object> book : books) {
                String bookId = String.valueOf(book.get("book_id"));
                String borrower = (String) book.get("borrower");

                // if hashMap already contains this book, extend top 3 borrowers array
                if (hashMap.containsKey(bookId)) {
                    ((List) ((HashMap) hashMap.get(bookId)).get("borrower")).add(borrower);
                    continue;
                }

                // Create a temp hash map in the required json format as the value
                Map<String, Object> valueHashMap = new HashMap<>();
                valueHashMap.put("author", book.get("author"));
                valueHashMap.put("name", book.get("book"));
                valueHashMap.put("borrower", new ArrayList<>());
                ((List) valueHashMap.get("borrower")).add(borrower);

                // Put the valueHashMap into the hashMap holder
                hashMap.put(bookId, valueHashMap);
            }

            // Put hashMap into result
            result = new ArrayList<>(hashMap.values());
        }
        // use a hashMap too but nest the one on top inside of countryId first { countryId: bookId: HashMap<>}
        else {
            // use a hashmap to mimic a set, in the required format
            // and if book is already in hashmap, extend the "borrower" array
            for (Map<String, Object> book : books) {
                String bookCountryId = Long.toString((Long) book.get("country_id"));
                String bookId = String.valueOf(book.get("book_id"));
                String borrower = (String) book.get("borrower");

                // if hashMap already has this country and contains this book, extend top 3 borrowers array
                if (hashMap.containsKey(bookCountryId)) {
                    // if book exists, add borrower to the borrowers array
                    if (((HashMap) hashMap.get(bookCountryId)).containsKey(bookId)) {
                        ((List) ((HashMap) ((HashMap) hashMap.get(bookCountryId)).get(bookId)).get("borrower"))
                                .add(borrower);
                    }
                    // if book does not exist, create in country
                    else {
                        // Create a temp hash map in the required json format as the value
                        Map<String, Object> valueHashMap = new HashMap<>();
                        valueHashMap.put("author", book.get("author"));
                        valueHashMap.put("name", book.get("book"));
                        valueHashMap.put("borrower", new ArrayList<>());
                        ((List) valueHashMap.get("borrower")).add(borrower);

                        // Put the valueHashMap into the hashMap holder under the correct country
                        ((HashMap) hashMap.get(bookCountryId)).put(bookId, valueHashMap);
                    }
                }
                // if country does not yet exist, create new
                else {
                    // Create a temp hash map in the required json format as the value
                    Map<String, Object> valueHashMap = new HashMap<>();
                    valueHashMap.put("author", book.get("author"));
                    valueHashMap.put("name", book.get("book"));
                    valueHashMap.put("borrower", new ArrayList<>());
                    ((List) valueHashMap.get("borrower")).add(borrower);

                    // Put the valueHashMap into the hashMap holder under the correct country
                    hashMap.put(bookCountryId, new HashMap<String, Object>());
                    ((HashMap) hashMap.get(bookCountryId)).put(bookId, valueHashMap);
                }
            }

            // Put hashMap into result
            result = new ArrayList<>();
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Collection booksInfo = ((Map) value).values();
                Map<String, Object> toAdd = new HashMap<>();
                String country = null;

                if (key.equals("702")) {
                    country = "SG";
                } else if (key.equals("458")) {
                    country = "MY";
                } else if (key.equals("840")) {
                    country = "US";
                }

                toAdd.put(country, booksInfo);
                result.add(toAdd);
            }
        }
        return result;
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
