import React from "react";
import renderer from "react-test-renderer";
import Borrower from "../components/Borrower";
import { shallow, configure } from "enzyme";
import Adapter from "enzyme-adapter-react-16";

configure({ adapter: new Adapter() });

it("renders correctly", () => {
  const tree = renderer.create(<Borrower name={"Jon Hamm"} />).toJSON();
  expect(tree).toMatchSnapshot();
});

it('has class "customer"', () => {
  const tree = renderer.create(<Borrower name={"Jon Hamm"} />);
  expect(
    shallow(<Borrower name={"Jon Hamm"} />)
      .find("div.customer")
      .exists()
  ).toBe(true);
});
