import React from "react";
import renderer from "react-test-renderer";
import Book from "../components/Book";
import { shallow, configure } from "enzyme";
import Adapter from "enzyme-adapter-react-16";

configure({ adapter: new Adapter() });

it("renders correctly", () => {
  const tree = renderer
    .create(
      <Book
        idx={1}
        bookName={"The Stone Sky"}
        authorName={"N.K. Jemisin"}
        borrowers={[]}
        isToggled={false}
        handleBookClick={""}
      />
    )
    .toJSON();
  expect(tree).toMatchSnapshot();
});
