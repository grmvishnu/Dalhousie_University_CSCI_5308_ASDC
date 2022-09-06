import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { login } from "../Action/Login";
import "../Css/Login.css";
import { Country, State, City } from "country-state-city";
import Home from "../Page/Home";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import Button from "@mui/material/Button";

export default function Login() {
  let disptach = useDispatch();
  const navigate = useNavigate();

  const navigateToHomePage = () => {
    navigate(PATH.Traveller.TRAVELERHOMEPAGE);
  };

  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState({
    isNull: false,
  });

  let state = useSelector((state) => state.login);

  const closeLoader = () => {
    setLoading(false);
  };

  const onSignIn = (e) => {
    setLoading(true);
    validation();
    e.preventDefault();
    if (!error.isNull) {
      disptach(login({ userName, password, navigateToHomePage, closeLoader }));
    }
  };

  const validation = () => {
    if (userName.length == 0 || password.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          isNull: true,
        };
      });
    } else {
      setError((preState) => {
        return {
          ...preState,
          isNull: false,
        };
      });
    }
  };

  return (
    <div>
      <TravelerHomePageHeader />
      <div className="mainForm">
        <form>
          <input
            type="text"
            onChange={(e) => setUserName(e.target.value)}
            placeholder="Username:"
            className="formField"
          />
          <br></br>
          <input
            type="password"
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password:"
            className="formField"
            onBlur={validation}
          />
          <br></br>
          {error.isNull && (
            <div style={{ color: "red" }}>
              All the field should be filled !!
            </div>
          )}

          <br></br>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              marginLeft: "7%",
            }}
          >
            {!loading ? (
              <Button type="submit" variant="contained" onClick={onSignIn}>
                Sign In
              </Button>
            ) : (
              <Button variant="contained" disabled>
                Loading..
              </Button>
            )}
          </div>

          <br></br>
          <label>Don't have an account ?</label>
          <div style={{ width: "80vh", marginTop: "5px" }}>
            <Button
              variant="contained"
              sx={{ width: "100%", marginTop: "5px" }}
              onClick={() => {
                navigate(PATH.Traveller.SIGNUP);
              }}
            >
              SignUp
            </Button>
          </div>
        </form>
      </div>
    </div>
  );
}
