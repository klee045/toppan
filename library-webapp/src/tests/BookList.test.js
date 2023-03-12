import React from "react";
import renderer from "react-test-renderer";
import BookList from "../components/BookList";
import { shallow, configure } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import { screen } from "@testing-library/react";

configure({ adapter: new Adapter() });

it("renders correctly", () => {
  const tree = renderer
    .create(
      <BookList
        books={[]}
        toggleStates={[false, false, false]}
        handleBookClick={""}
      />
    )
    .toJSON();
  expect(tree).toMatchSnapshot();
});

it('has a div with id "container"', () => {
  const tree = renderer.create(
    <BookList
      books={[]}
      toggleStates={[false, false, false]}
      handleBookClick={""}
    />
  );
  expect(
    shallow(
      <BookList
        books={[]}
        toggleStates={[false, false, false]}
        handleBookClick={""}
      />
    )
      .find("div#container")
      .exists()
  ).toBe(true);
});
