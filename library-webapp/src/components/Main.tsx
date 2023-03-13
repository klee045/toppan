import axios from "axios";
import React, { useState } from "react";
import BookList from "./BookList";
import CountrySelector from "./CountrySelector";

const Main = (): React.ReactElement => {
  const [books, setBooks] = useState<
    { author: string; name: string; borrower: string[] }[]
  >([]);
  const [countrySelected, setCountrySelected] = useState<string>("");

  const [toggleStates, setToggleState] = useState<boolean[]>([
    false,
    false,
    false,
  ]);

  const handleBookClick = (idx: number): void => {
    const temp: boolean[] = [false, false, false];
    temp[idx] = !toggleStates[idx];
    setToggleState([...temp]);
  };

  const handleCountrySelectedClick = async (): Promise<void> => {
    // Call /getRandomCountry for a countryCode
    const {
      data: { country },
    } = await axios.get("http://localhost:8080/getRandomCountry", {
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    });
    const newCountryCode: string = country.country_code;
    setCountrySelected(newCountryCode);

    // Call /getTop3ReadBooks and populate books array, set to empty array if exception
    axios
      .get("http://localhost:8080/getTop3ReadBooks", {
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

    // Reset state of toggleStates so all books reset to unexpanded state
    setToggleState([false, false, false]);
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
        <BookList
          books={books}
          toggleStates={toggleStates}
          handleBookClick={handleBookClick}
        />
      </div>
    </div>
  );
};

export default Main;
