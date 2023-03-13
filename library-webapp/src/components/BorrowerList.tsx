import React, { CSSProperties } from "react";
import Borrower from "./Borrower";

const BorrowerListStyle: CSSProperties = {
  width: "inherit",
};

const BorrowerList = ({
  borrowers,
}: {
  borrowers: string[];
}): React.ReactElement => {
  return (
    <div id="borrowerList" style={BorrowerListStyle}>
      {borrowers.map((borrower, index) => (
        <div key={index + 1}>
          <Borrower name={borrower} />
        </div>
      ))}
    </div>
  );
};

export default BorrowerList;
