import React, { CSSProperties } from "react";
import Book from "./Book";
import BorrowerList from "./BorrowerList";

const BookListStyle: CSSProperties = {
  outlineStyle: "solid",
  height: "50%",
  width: "50%",
  display: "flex",
  flexDirection: "column",
  justifyContent: "space-around",
  alignItems: "center",
  alignSelf: "center",
};

const BookList = () => {
  // hardcode the booklist for now, until the country selection is implemented
  const books = [
    {
      author: "J.K. Rowling",
      name: "Harry Potter",
      borrower: ["Charles1", "Charles2", "Charles3"],
    },
    {
      author: "William Peter Blatty",
      name: "The Exorcist",
      borrower: ["Amy1", "Amy2", "Amy3"],
    },
    {
      author: "N.K. Jemisin",
      name: "The Stone Sky",
      borrower: ["Bob1", "Bob2", "Bob3"],
    },
  ];

  return (
    <div id="container" style={BookListStyle}>
      {books.map((book, index) => (
        <Book
          idx={index + 1}
          bookName={book.name}
          authorName={book.author}
          borrowers={book.borrower}
          isToggled={false}
        />
      ))}
    </div>
  );
};

export default BookList;
