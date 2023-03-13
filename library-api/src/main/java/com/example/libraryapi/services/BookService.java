package com.example.libraryapi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.libraryapi.common.pojos.AuthorNameValue;
import com.example.libraryapi.common.pojos.BookNameValue;
import com.example.libraryapi.common.pojos.BorrowerArrayValue;
import com.example.libraryapi.common.pojos.DynamicTypeValue;
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
        Map<String, Map<String, DynamicTypeValue>> hashMap = new HashMap<>();
        List<Top3ReadBooksResponse> result = new ArrayList<>();

        for (Top3ReadBooksQueryResult book : books) {
            String bookId = String.valueOf(book.getBook_id());
            String borrower = book.getBorrower();

            // if hashMap already contains this book, extend top 3 borrowers array
            if (hashMap.containsKey(bookId)) {
                BorrowerArrayValue borrowerArrayValue = (BorrowerArrayValue) hashMap.get(bookId).get("borrower");
                borrowerArrayValue.addBorrower(borrower);
                continue;
            }

            // Create a temp hash map in the required json format as the value
            Map<String, DynamicTypeValue> valueHashMap = new HashMap<>();
            valueHashMap.put("author", new AuthorNameValue(book.getAuthor()));
            valueHashMap.put("name", new BookNameValue(book.getBook()));
            valueHashMap.put("borrower", new BorrowerArrayValue(new ArrayList<>()));
            ((BorrowerArrayValue) valueHashMap.get("borrower")).addBorrower(borrower);

            // Put the valueHashMap into the hashMap holder
            hashMap.put(bookId, valueHashMap);
        }

        // Put hashMap into result
        List<Map<String, DynamicTypeValue>> temp = new ArrayList<>(hashMap.values());
        for (Map<String, DynamicTypeValue> t : temp) {
            String author = ((AuthorNameValue) t.get("author")).getAuthorName();
            String name = ((BookNameValue) t.get("name")).getBookName();
            List<String> borrowers = ((BorrowerArrayValue) t.get("borrower")).getBorrowers();
            result.add(new Top3ReadBooksResponse(author, name, borrowers));
        }

        return result;
    }
}
