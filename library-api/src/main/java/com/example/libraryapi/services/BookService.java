package com.example.libraryapi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libraryapi.errorhandlers.BadRequestException;
import com.example.libraryapi.errorhandlers.NoResultException;
import com.example.libraryapi.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Object> getTop3ReadBooks(String countryCode) throws BadRequestException, NoResultException {
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
        } else {
            throw new BadRequestException("invalid parameter");
        }
        // Call the query and add the result into the books array
        List<Map<String, Object>> books = new ArrayList<Map<String, Object>>(
                bookRepository.getTop3BorrowedBooksInCountryAndTop3BorrowersWithinCountry(countryId));

        if (books.isEmpty()) {
            throw new NoResultException("no results");
        }

        // Format the response
        List<Object> result = formatTop3Response(countryCode, books);

        return result;
    }

    // Helper function to format query result into required json format
    public List<Object> formatTop3Response(String countryCode, List<Map<String, Object>> books) {
        // use a hashmap to mimic a set through use of bookId, in the required format
        Map<String, Object> hashMap = new HashMap<>();
        List<Object> result = null;

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

        return result;
    }
}
