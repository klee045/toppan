package com.example.libraryapi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.libraryapi.errorhandlers.BadRequestException;
import com.example.libraryapi.errorhandlers.NoResultException;
import com.example.libraryapi.models.Top3ReadBooksQueryResult;
import com.example.libraryapi.models.Top3ReadBooksResponse;
import com.example.libraryapi.repositories.BookRepository;

@Service
public class BookService {
    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Top3ReadBooksResponse> getTop3ReadBooks(String countryCode)
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
        } else {
            throw new BadRequestException("invalid parameter");
        }

        // Call the query and add the result into the books array
        List<Top3ReadBooksQueryResult> books = new ArrayList<>(
                bookRepository.getTop3BorrowedBooksInCountryAndTop3BorrowersWithinCountry(countryId));

        if (books.isEmpty()) {
            throw new NoResultException("no results");
        }

        // Format the response
        List<Top3ReadBooksResponse> result = formatTop3Response(countryCode, books);

        return result;
    }

    // Helper function to format query result into required json format
    public List<Top3ReadBooksResponse> formatTop3Response(String countryCode, List<Top3ReadBooksQueryResult> books) {
        // use a hashmap to mimic a set through use of bookId, in the required format
        Map<String, Object> hashMap = new HashMap<>();
        List<Top3ReadBooksResponse> result = new ArrayList<>();

        for (Top3ReadBooksQueryResult book : books) {
            String bookId = String.valueOf(book.getBook_id());
            String borrower = book.getBorrower();

            // if hashMap already contains this book, extend top 3 borrowers array
            if (hashMap.containsKey(bookId)) {
                ((List) ((HashMap) hashMap.get(bookId)).get("borrower")).add(borrower);
                continue;
            }

            // Create a temp hash map in the required json format as the value
            Map<String, Object> valueHashMap = new HashMap<>();
            valueHashMap.put("author", book.getAuthor());
            valueHashMap.put("name", book.getBook());
            valueHashMap.put("borrower", new ArrayList<>());
            ((List) valueHashMap.get("borrower")).add(borrower);

            // Put the valueHashMap into the hashMap holder
            hashMap.put(bookId, valueHashMap);
        }

        // Put hashMap into result
        List<Object> temp = new ArrayList<>(hashMap.values());
        for (Object t : temp) {
            String author = (String) ((Map) t).get("author");
            String name = (String) ((Map) t).get("name");
            List<String> borrowers = (List<String>) ((Map) t).get("borrower");
            result.add(new Top3ReadBooksResponse(author, name, borrowers));
        }

        return result;
    }
}
