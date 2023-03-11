import React, { CSSProperties, useState } from "react";
import Book from "./Book";

const BookListStyle: CSSProperties = {
  outlineStyle: "solid",
  outlineWidth: "1px",
  width: "30%",
  display: "flex",
  flexDirection: "column",
  justifyContent: "space-around",
  alignItems: "center",
  alignSelf: "center",
  paddingTop: "20px",
  paddingBottom: "20px",
};

const BookList = (): React.ReactElement => {
  // hardcode the booklist for now, until the country selection is implemented
  const books = [
    {
      author: "J.K. Rowling",
      name: "Harry Potter",
      borrower: ["Charles McGee", "Charles Malon", "Kevin Charles"],
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

  const [toggleStates, setToggleState] = useState([false, false, false]);

  const handleBookClick = (idx: number): void => {
    const temp: boolean[] = [false, false, false];
    temp[idx] = !toggleStates[idx];
    setToggleState([...temp]);
  };

  return (
    <div id="container" style={BookListStyle}>
      {books.length !== 0 ? (
        books.map((book, index) => (
          <div
            id={`book-item-${index + 1}`}
            style={{
              display: "inline-flex",
              width: "80%",
              flexDirection: "column",
              alignItems: "center",
              flex: "1",
              marginBottom: "20px",
            }}
            onClick={() => {
              handleBookClick(index);
            }}
          >
            <Book
              idx={index + 1}
              bookName={book.name}
              authorName={book.author}
              borrowers={book.borrower}
              isToggled={toggleStates[index]}
            />
          </div>
        ))
      ) : (
        <div style={{ fontSize: "18px" }}>"No data found"</div>
      )}
    </div>
  );
};

export default BookList;
