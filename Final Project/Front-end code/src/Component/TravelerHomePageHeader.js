import React from "react";
import { GiWorld } from "react-icons/gi";
import "../Css/TravelerHomePage.css";
import { useNavigate } from "react-router-dom";
import { FaUserCircle } from "react-icons/fa";
import PATH from "../utils/Path";
import Button from "@mui/material/Button";

import { LOGOUT } from "../utils/Type";
import { useDispatch, useSelector } from "react-redux";

export default function TravelerHomePageHeader() {
  let navigate = useNavigate();

  let dispatch = useDispatch();
  let state = useSelector((state) => state.login);

  const onLogout = () => {
    navigate(PATH.Traveller.LOGIN);
    dispatch({ type: LOGOUT });
  };

  return (
    <div className="mainpage">
      <div className="container">
        <div
          onClick={() => {
            navigate(PATH.Traveller.HOME);
          }}
        >
          <h1 style={{ zIndex: 50, cursor: "pointer" }}>
            <span>
              <GiWorld /> Budget{" "}
            </span>{" "}
            Surfing{" "}
          </h1>
        </div>

        {state.isLoggedIn ? (
          <div
            style={{
              display: "flex",
              justifyContent: "flex-end",
              alignItems: "center",
            }}
          >
            <li>
              <Button
                onClick={() => {
                  navigate(PATH.Traveller.ALLTRIP);
                }}
                variant="outlined"
              >
                Your Trips !
              </Button>
            </li>

            <li style={{ zIndex: 100, cursor: "pointer" }}>
              <FaUserCircle
                color={"#1976d2"}
                size={"35px"}
                className="icon"
                onClick={() => {
                  navigate(PATH.Traveller.USERPROFILE, {
                    state: { disable: false, hostId: state.id },
                  });
                }}
              ></FaUserCircle>
            </li>

            <li>
              <Button variant="contained" onClick={onLogout}>
                Log out
              </Button>
            </li>
          </div>
        ) : null}
      </div>
    </div>
  );
}
