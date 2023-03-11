import axios from "axios";
import React, { useState } from "react";
import BookList from "./BookList";
import CountrySelector from "./CountrySelector";

const Main = (): React.ReactElement => {
  const [books, setBooks] = useState<
    { author: string; name: string; borrower: string[] }[]
  >([]);
  const [countrySelected, setCountrySelected] = useState<string>("");

  const handleCountrySelectedClick = async (): Promise<void> => {
    // Call /getRandomCountry for a countryCode
    const {
      data: { country },
    } = await axios.get("http://localhost:8080/country/getRandomCountry", {
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    });
    const newCountryCode: string = country.country_code;
    setCountrySelected(newCountryCode);

    // Call /getTop3ReadBooks and populate books array, set to empty array if exception
    axios
      .get("http://localhost:8080/book/getTop3ReadBooks", {
        params: {
          country_code: newCountryCode,
        },
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then((response) => {
        const { data: books } = response;
        setBooks([...books]);
      })
      .catch((error) => {
        setBooks([]);
      });
  };

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
