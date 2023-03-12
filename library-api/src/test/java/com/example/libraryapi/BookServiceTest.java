package com.example.libraryapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.libraryapi.errorhandlers.BadRequestException;
import com.example.libraryapi.errorhandlers.NoResultException;
import com.example.libraryapi.models.Top3ReadBooksQueryResult;
import com.example.libraryapi.models.Top3ReadBooksResponse;
import com.example.libraryapi.repositories.BookRepository;
import com.example.libraryapi.services.BookService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void init() {
        bookService = new BookService(bookRepository);
    }

    @Test
    void shouldGetTop3BooksForSg() throws BadRequestException, NoResultException {
        List<Top3ReadBooksQueryResult> books = new ArrayList<>() {
            {
                add(new Top3ReadBooksQueryResult(Long.valueOf(702), 1, "N.K. Jemisin", "The Stone Sky", "Peggy Olsen",
                        3));
                add(new Top3ReadBooksQueryResult(Long.valueOf(702), 1, "N.K. Jemisin", "The Stone Sky", "Joan Harris",
                        2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(702), 1, "N.K. Jemisin", "The Stone Sky",
                        "Trudy Campbell", 1));
                add(new Top3ReadBooksQueryResult(Long.valueOf(702), 2, "William Peter Blatty", "The Exorcist",
                        "Peggy Olsen", 3));
                add(new Top3ReadBooksQueryResult(Long.valueOf(702), 2, "William Peter Blatty", "The Exorcist",
                        "Joan Harris", 2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(702), 2, "William Peter Blatty", "The Exorcist",
                        "Trudy Campbell", 2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(702), 3, "Brandon Sanderson", "The Final Empire",
                        "Joan Harris", 3));
                add(new Top3ReadBooksQueryResult(Long.valueOf(702), 3, "Brandon Sanderson", "The Final Empire",
                        "Peggy Olsen", 2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(702), 3, "Brandon Sanderson", "The Final Empire",
                        "Trudy Campbell", 1));
            }
        };
        // Uses mocked repo with the data intialized as seen above
        Mockito.when(bookRepository.getTop3BorrowedBooksInCountryAndTop3BorrowersWithinCountry(Long.valueOf(702)))
                .thenReturn(books);

        List<Top3ReadBooksResponse> actual = bookService.getTop3ReadBooks("SG");
        List<Top3ReadBooksResponse> expected = new ArrayList<>() {
            {
                add(new Top3ReadBooksResponse("N.K. Jemisin", "The Stone Sky", new ArrayList<>() {
                    {
                        add("Peggy Olsen");
                        add("Joan Harris");
                        add("Trudy Campbell");
                    }
                }));
                add(new Top3ReadBooksResponse("William Peter Blatty", "The Exorcist", new ArrayList<>() {
                    {
                        add("Peggy Olsen");
                        add("Joan Harris");
                        add("Trudy Campbell");
                    }
                }));
                add(new Top3ReadBooksResponse("Brandon Sanderson", "The Final Empire", new ArrayList<>() {
                    {
                        add("Joan Harris");
                        add("Peggy Olsen");
                        add("Trudy Campbell");
                    }
                }));
            }
        };

        assertEquals(actual, expected);
    }
}
