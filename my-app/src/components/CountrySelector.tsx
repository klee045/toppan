import React, {
  CSSProperties,
  Dispatch,
  SetStateAction,
  useState,
} from "react";

const CountrySelectorStyle: CSSProperties = {
  alignSelf: "flex-start",
  backgroundColor: "bisque",
  borderColor: "orange",
  borderWidth: "2px",
  padding: "2px",
};

const CountrySelector = (): JSX.Element => {
  const [countrySelected, setCountrySelected]: [
    string,
    Dispatch<SetStateAction<string>>
  ] = useState("SG");

  const handleClick = (): void => {
    const countries: string[] = ["SG", "US", "MY"];
    const randomId: number = Math.floor(Math.random() * countries.length);
    setCountrySelected(countries[randomId]);
  };

  return (
    <button style={CountrySelectorStyle} onClick={handleClick}>
      Get Country: {countrySelected}
    </button>
  );
};

export default CountrySelector;
