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

const BookList = ({
  books,
}: {
  books: {
    author: string;
    name: string;
    borrower: string[];
  }[];
}): React.ReactElement => {
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
