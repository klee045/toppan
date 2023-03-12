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

    @Test
    void shouldGetTop3BooksForMy() throws BadRequestException, NoResultException {
        List<Top3ReadBooksQueryResult> books = new ArrayList<>() {
            {
                add(new Top3ReadBooksQueryResult(Long.valueOf(458), 1, "N.K. Jemisin", "The Stone Sky", "Jim Halpert",
                        3));
                add(new Top3ReadBooksQueryResult(Long.valueOf(458), 1, "N.K. Jemisin", "The Stone Sky", "E.B. Farnum",
                        2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(458), 1, "N.K. Jemisin", "The Stone Sky", "Don Draper",
                        1));
                add(new Top3ReadBooksQueryResult(Long.valueOf(458), 2, "William Peter Blatty", "The Exorcist",
                        "Jim Halpert", 3));
                add(new Top3ReadBooksQueryResult(Long.valueOf(458), 2, "William Peter Blatty", "The Exorcist",
                        "E.B. Farnum", 2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(458), 2, "William Peter Blatty", "The Exorcist",
                        "Don Draper", 2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(458), 3, "Brandon Sanderson", "The Final Empire",
                        "Jim Halpert", 3));
                add(new Top3ReadBooksQueryResult(Long.valueOf(458), 3, "Brandon Sanderson", "The Final Empire",
                        "E.B. Farnum", 2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(458), 3, "Brandon Sanderson", "The Final Empire",
                        "Don Draper", 1));
            }
        };
        // Uses mocked repo with the data intialized as seen above
        Mockito.when(bookRepository.getTop3BorrowedBooksInCountryAndTop3BorrowersWithinCountry(Long.valueOf(458)))
                .thenReturn(books);

        List<Top3ReadBooksResponse> actual = bookService.getTop3ReadBooks("MY");
        List<Top3ReadBooksResponse> expected = new ArrayList<>() {
            {
                add(new Top3ReadBooksResponse("N.K. Jemisin", "The Stone Sky", new ArrayList<>() {
                    {
                        add("Jim Halpert");
                        add("E.B. Farnum");
                        add("Don Draper");
                    }
                }));
                add(new Top3ReadBooksResponse("William Peter Blatty", "The Exorcist", new ArrayList<>() {
                    {
                        add("Jim Halpert");
                        add("E.B. Farnum");
                        add("Don Draper");
                    }
                }));
                add(new Top3ReadBooksResponse("Brandon Sanderson", "The Final Empire", new ArrayList<>() {
                    {
                        add("Jim Halpert");
                        add("E.B. Farnum");
                        add("Don Draper");
                    }
                }));
            }
        };

        assertEquals(actual, expected);
    }

    @Test
    void shouldGetTop3BooksForUs() throws BadRequestException, NoResultException {
        List<Top3ReadBooksQueryResult> books = new ArrayList<>() {
            {
                add(new Top3ReadBooksQueryResult(Long.valueOf(840), 1, "N.K. Jemisin", "The Stone Sky",
                        "Darryl Philbin", 3));
                add(new Top3ReadBooksQueryResult(Long.valueOf(840), 1, "N.K. Jemisin", "The Stone Sky", "Betty Draper",
                        2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(840), 1, "N.K. Jemisin", "The Stone Sky", "Michael Scott",
                        1));
                add(new Top3ReadBooksQueryResult(Long.valueOf(840), 2, "William Peter Blatty", "The Exorcist",
                        "Darryl Philbin", 3));
                add(new Top3ReadBooksQueryResult(Long.valueOf(840), 2, "William Peter Blatty", "The Exorcist",
                        "Michael Scott", 2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(840), 2, "William Peter Blatty", "The Exorcist",
                        "Betty Draper", 2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(840), 3, "Brandon Sanderson", "The Final Empire",
                        "Darryl Philbin", 3));
                add(new Top3ReadBooksQueryResult(Long.valueOf(840), 3, "Brandon Sanderson", "The Final Empire",
                        "Betty Draper", 2));
                add(new Top3ReadBooksQueryResult(Long.valueOf(840), 3, "Brandon Sanderson", "The Final Empire",
                        "Michael Scott", 1));
            }
        };
        // Uses mocked repo with the data intialized as seen above
        Mockito.when(bookRepository.getTop3BorrowedBooksInCountryAndTop3BorrowersWithinCountry(Long.valueOf(458)))
                .thenReturn(books);

        List<Top3ReadBooksResponse> actual = bookService.getTop3ReadBooks("MY");
        List<Top3ReadBooksResponse> expected = new ArrayList<>() {
            {
                add(new Top3ReadBooksResponse("N.K. Jemisin", "The Stone Sky", new ArrayList<>() {
                    {
                        add("Darryl Philbin");
                        add("Betty Draper");
                        add("Michael Scott");
                    }
                }));
                add(new Top3ReadBooksResponse("William Peter Blatty", "The Exorcist", new ArrayList<>() {
                    {
                        add("Darryl Philbin");
                        add("Michael Scott");
                        add("Betty Draper");
                    }
                }));
                add(new Top3ReadBooksResponse("Brandon Sanderson", "The Final Empire", new ArrayList<>() {
                    {
                        add("Darryl Philbin");
                        add("Betty Draper");
                        add("Michael Scott");
                    }
                }));
            }
        };

        assertEquals(actual, expected);
    }
}
