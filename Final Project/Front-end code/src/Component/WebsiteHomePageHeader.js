import React from "react";
import { GiWorld } from "react-icons/gi";
import "../Css/WebsitePage.css";
import "../Css/WebsitePageText.css";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import Button from "@mui/material/Button";

export default function WebsiteHomePageHeader() {
  let navigate = useNavigate();

  return (
    /* main page here and in css file means the navigation bar*/
    <div className="mainpage">
      <div className="container">
        <h1>
          <span>
            <GiWorld /> Budget{" "}
          </span>{" "}
          Surfing{" "}
        </h1>
        <div className="menu">
          <li>
            <Button
              variant="contained"
              onClick={() => {
                navigate(PATH.Traveller.SIGNUP);
              }}
            >
              SIGN UP
            </Button>
          </li>

          <li>
            <Button
              variant="contained"
              onClick={() => {
                navigate(PATH.Traveller.LOGIN);
              }}
            >
              LOG IN
            </Button>
          </li>
        </div>
      </div>
    </div>
  );
}
