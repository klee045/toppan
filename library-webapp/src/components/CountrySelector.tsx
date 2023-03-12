import React, { CSSProperties } from "react";

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

const CountrySelector = ({
  countrySelected,
  handleClick,
}: {
  countrySelected: string;
  handleClick: React.MouseEventHandler<HTMLButtonElement>;
}): React.ReactElement => {
  return (
    <button id="action-btn" style={CountrySelectorStyle} onClick={handleClick}>
      Get country: {countrySelected}
    </button>
  );
};

export default CountrySelector;
