import React, {
  CSSProperties,
  Dispatch,
  SetStateAction,
  useState,
} from "react";

const CountrySelectorStyle: CSSProperties = {
  alignSelf: "flex-start",
  backgroundColor: "rgb(241, 230, 230)",
  borderColor: "rgb(255, 116, 43)",
  borderWidth: "1px",
  padding: "4px",
  width: "9%",
  height: "5%",
  marginTop: "25px",
  marginLeft: "25px",
  fontSize: "18px",
};

const CountrySelector = (): React.ReactElement => {
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
    <button id="action-btn" style={CountrySelectorStyle} onClick={handleClick}>
      Get country: {countrySelected}
    </button>
  );
};

export default CountrySelector;
