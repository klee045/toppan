import React from "react";
import BookList from "./BookList";
import CountrySelector from "./CountrySelector";

const Main = (): React.ReactElement => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        height: "100%",
      }}
    >
      <CountrySelector />
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          height: "100%",
        }}
      >
        <BookList />
      </div>
    </div>
  );
};

export default Main;
