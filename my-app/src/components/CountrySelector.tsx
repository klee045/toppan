import React, {
  CSSProperties,
  Dispatch,
  SetStateAction,
  useState,
} from "react";

const CountrySelectorStyle: CSSProperties = {};

const CountrySelector = (): JSX.Element => {
  const [countrySelected, setCountrySelected]: [
    string,
    Dispatch<SetStateAction<string>>
  ] = useState("SG");

  const handleClick = (): void => {
    const countries: string[] = ["SG", "US", "MY"];
    const randomId: number = Math.floor(
      Math.random() * (countries.length - 0 + 1) + 0
    );
    setCountrySelected(countries[randomId]);
  };

  return <button onClick={handleClick}>Get Country: {countrySelected}</button>;
};

export default CountrySelector;
