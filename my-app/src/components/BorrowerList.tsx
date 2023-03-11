import React, { CSSProperties } from "react";
import Borrower from "./Borrower";

const BorrowerListStyle: CSSProperties = {
  width: "inherit",
};

const BorrowerList = ({ borrowers }: { borrowers: string[] }): JSX.Element => {
  return (
    <div id="borrowerList" style={BorrowerListStyle}>
      {borrowers.map((borrower) => (
        <Borrower name={borrower} />
      ))}
    </div>
  );
};

export default BorrowerList;
