import axios from "axios";
import React, { Dispatch, SetStateAction, useEffect, useState } from "react";
import BookList from "./BookList";
import CountrySelector from "./CountrySelector";

const Main = (): React.ReactElement => {
  const [books, setBooks]: [
    Array<{ author: string; name: string; borrower: string[] }>,
    Dispatch<
      SetStateAction<
        Array<{ author: string; name: string; borrower: string[] }>
      >
    >
  ] = useState(Array());
  const [countrySelected, setCountrySelected]: [
    string,
    Dispatch<SetStateAction<string>>
  ] = useState("");

  const handleCountrySelectedClick = async (): Promise<void> => {
    const countries: string[] = ["SG", "US", "MY"];
    const randomId: number = Math.floor(Math.random() * countries.length);
    const newCountry = countries[randomId];
    setCountrySelected(newCountry);
    console.log("newCountry =", newCountry);

    // Call /getTop3ReadBooks and populate books array
    const { data: books } = await axios.get(
      "http://localhost:8080/book/getTop3ReadBooks",
      {
        params: {
          country_code: newCountry,
        },
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      }
    );

    setBooks([...books]);
  };

  useEffect(() => {});

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        height: "100%",
      }}
    >
      <CountrySelector
        countrySelected={countrySelected}
        handleClick={handleCountrySelectedClick}
      />
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          height: "100%",
        }}
      >
        <BookList books={books} />
      </div>
    </div>
  );
};

export default Main;
