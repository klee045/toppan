import React, { CSSProperties } from "react";
import { RiArrowDropDownLine, RiArrowDropUpLine } from "react-icons/ri";

const BookStyle: CSSProperties = {
  padding: "30px",
  borderRadius: "10px",
  boxShadow: "3px 6px 5px gray",
  backgroundColor: "bisque",
  width: "70%",
};

const BookDescriptionStyle: CSSProperties = {
  display: "flex",
  flexDirection: "row",
  justifyContent: "space-between",
};

const IdxDivStyle: CSSProperties = {
  fontSize: "48px",
  marginLeft: "28px",
};

const BookNameDivStyle: CSSProperties = {
  fontSize: "48px",
};

const ArrowDivStyle: CSSProperties = {
  fontSize: "48px",
  marginRight: "28px",
  display: "flex",
  alignItems: "center",
};

const AuthorDivStyle: CSSProperties = {
  marginRight: "28px",
  textAlign: "end",
};

const Book = ({
  idx,
  bookName,
  authorName,
  borrowers,
  isToggled,
}: {
  idx: number;
  bookName: string;
  authorName: string;
  borrowers: string[];
  isToggled: boolean;
}): JSX.Element => {
  return (
    <div id={`book-item-${idx}`} style={BookStyle}>
      <div style={BookDescriptionStyle}>
        <div style={IdxDivStyle}>{idx}</div>
        <div style={BookNameDivStyle}>{bookName}</div>
        <div style={ArrowDivStyle}>
          {isToggled ? <RiArrowDropUpLine /> : <RiArrowDropDownLine />}
        </div>
      </div>
      <div style={AuthorDivStyle}>by {authorName}</div>
    </div>
  );
};

export default Book;
