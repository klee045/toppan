import React from "react";
import renderer from "react-test-renderer";
import CountrySelector from "../components/CountrySelector";
import { shallow, configure } from "enzyme";
import Adapter from "enzyme-adapter-react-16";

configure({ adapter: new Adapter() });

it("renders correctly", () => {
  const tree = renderer
    .create(<CountrySelector countrySelected={"SG"} handleClick={""} />)
    .toJSON();
  expect(tree).toMatchSnapshot();
});

it('has a div with id "action-btn"', () => {
  const tree = renderer.create(
    <CountrySelector countrySelected={"SG"} handleClick={""} />
  );
  expect(
    shallow(<CountrySelector countrySelected={"SG"} />)
      .find("div#action-btn")
      .exists()
  ).toBe(true);
});
