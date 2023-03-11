import React, { CSSProperties } from "react";
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

const BookList = ({
  books,
  toggleStates,
  handleBookClick,
}: {
  books: {
    author: string;
    name: string;
    borrower: string[];
  }[];
  toggleStates: boolean[];
  handleBookClick: (idx: number) => void;
}): React.ReactElement => {
  return (
    <div id="container" style={BookListStyle}>
      {books.length !== 0 ? (
        books.map((book, index) => (
          <div
            key={index + 1}
            id={`book-item-${index + 1}`}
            style={{
              display: "inline-flex",
              width: "80%",
              flexDirection: "column",
              alignItems: "center",
              flex: "1",
              marginBottom: "20px",
            }}
          >
            <Book
              idx={index + 1}
              bookName={book.name}
              authorName={book.author}
              borrowers={book.borrower}
              isToggled={toggleStates[index]}
              handleBookClick={handleBookClick}
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
