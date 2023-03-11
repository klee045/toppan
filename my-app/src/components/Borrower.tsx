import React, { CSSProperties } from "react";

const BorrowerStyle: CSSProperties = {
  marginTop: "20px",
  padding: "30px",
  borderRadius: "10px",
  boxShadow: "3px 6px 5px gray",
  backgroundColor: "rgb(255, 215, 215)",
  fontSize: "24px",
};

const Borrower = ({ name }: { name: string }): JSX.Element => {
  return (
    <div className="customer" style={BorrowerStyle}>
      {name}
    </div>
  );
};

export default Borrower;
