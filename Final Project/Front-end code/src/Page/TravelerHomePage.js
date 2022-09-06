import React, { useEffect, useState } from "react";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import "../Css/TravelerHomePage.css";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import bus from "../Images/bus.jpg";
import train from "../Images/train.jpg";
import Button from "@mui/material/Button";
import API from "../utils/API";
import { useDispatch, useSelector } from "react-redux";
import { isLoggedIn } from "../utils/API";

export default function TravelerHomePage() {
  let login = useSelector((state) => state.login);
  let navigate = useNavigate();

  useEffect(async () => {
    isLoggedIn(login, navigate);
    if (login.id) {
      const res = await API().get(`/profile/${login.id}`);
      const { firstName, lastName, mobileNumber } = res.data;
      if (!firstName || !lastName || !mobileNumber) {
        navigate(PATH.Traveller.USERPROFILE, {
          state: { disable: false, hostId: login.id },
        });
      }
    }
  }, []);

  return (
    <div>
      <TravelerHomePageHeader />
      <div style={{ display: "flex" }}>
        <img
          src={bus}
          style={{
            width: "100vw",
            height: "100vh",
            objectFit: "cover",
            position: "absolute",
            opacity: "0.90",
          }}
        />
        <div
          style={{
            display: "flex",
            flex: "1 1 auto",
            flexDirection: "column",
            height: "100vh",
            marginLeft: "39%",
            justifyContent: "space-around",
          }}
        >
          <Button
            variant="contained"
            sx={{ width: "60%", margin: "auto" }}
            onClick={() => {
              navigate(PATH.Traveller.ADDTRIP);
            }}
          >
            Add Trip
          </Button>
          <Button
            variant="contained"
            sx={{ width: "60%", margin: "auto" }}
            onClick={() => navigate(PATH.Traveller.ADDBUDGET)}
          >
            Add Budget
          </Button>
          <Button
            variant="contained"
            sx={{ width: "60%", margin: "auto" }}
            onClick={() => navigate(PATH.Traveller.ADDEXPENSE)}
          >
            Add Expense
          </Button>
          <Button
            variant="contained"
            sx={{ width: "60%", margin: "auto" }}
            onClick={() => navigate(PATH.Traveller.ADDCATEGORY)}
          >
            Add Category
          </Button>
        </div>
      </div>

      <div>
        <img
          src={train}
          style={{
            width: "100vw",
            height: "100vh",
            objectFit: "cover",
            position: "absolute",
            backgroundAttachment: "fixed",
            backgroundBlendMode: "darken",
            opacity: "0.90",
          }}
        />
        <Button
          variant="contained"
          sx={{
            position: "relative",
            marginTop: "50vh",
            left: "25%",
            width: "50%",
            background: "white",
            color: "black",
          }}
          onClick={() => navigate(PATH.Traveller.HOSTHOMEPAGE)}
        >
          Want to be a Host !
        </Button>
      </div>
    </div>
  );
}
