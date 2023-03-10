import React, { CSSProperties } from "react";

const BorrowerStyle: CSSProperties = {};

const Borrower = ({ name }: { name: string }): JSX.Element => {
  return <div style={BorrowerStyle}>{name}</div>;
};

export default Borrower;
