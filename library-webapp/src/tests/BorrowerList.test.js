import React from "react";
import renderer from "react-test-renderer";
import BorrowerList from "../components/BorrowerList";
import { shallow, configure } from "enzyme";
import Adapter from "enzyme-adapter-react-16";

configure({ adapter: new Adapter() });

it("renders correctly", () => {
  const tree = renderer.create(<BorrowerList borrowers={[]} />).toJSON();
  expect(tree).toMatchSnapshot();
});

it('has a div with id "borrowerList"', () => {
  const tree = renderer.create(<BorrowerList borrowers={[]} />);
  expect(
    shallow(<BorrowerList borrowers={[]} />)
      .find("div#borrowerList")
      .exists()
  ).toBe(true);
});
