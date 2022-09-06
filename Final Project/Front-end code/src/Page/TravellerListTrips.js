import React, { Fragment, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import Button from "@mui/material/Button";
import { isLoggedIn } from "../utils/API";
import API from "../utils/API";
import Stack from "@mui/material/Stack";
import "../Css/TravellerListTrips.css";
import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";

export default function TravellerListTrips() {
  const [trips, setTrips] = useState(null);
  const [data, setData] = useState({
    tripId: null,
  });

  let state = useSelector((state) => state.login);
  const navigate = useNavigate();

  useEffect(async () => {
    isLoggedIn(state, navigate);
    const response = await API().get(`/trip/username/${state.id}`);
    setTrips(response.data);
  }, []);

  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
    padding: theme.spacing(1),
    textAlign: "center",
  }));

  return (
    <Fragment>
      <TravelerHomePageHeader />
      <h2
        style={{
          display: "flex",
          justifyContent: "center",
          color: "black",
        }}
      >
        Your Trips!
      </h2>
      <br></br>
      <div>
        {trips != null ? (
          trips.map((trips, index) => (
            <div key={index}>
              <div
                className={
                  trips.accepted != ""
                    ? "with-detail-accepted-btn"
                    : "with-detail-btn"
                }
              >
                <Stack spacing={2}>
                  <Item>Trip name : {trips.name}</Item>
                  <Item>City : {trips.city}</Item>
                  <Item>
                    Start From : {trips.startDate} {"  to  "} {trips.endDate}
                  </Item>
                  <Item>Country : {trips.country}</Item>
                  <Item>
                    {trips.accepted != "" ? (
                      <Button
                        disabled
                        sx={{
                          background: "green",
                          color: "aliceblue !important",
                          marginRight: "3px",
                        }}
                      >
                        Accepted
                      </Button>
                    ) : (
                      <Button
                        disabled
                        sx={{
                          background: "red",
                          color: "aliceblue !important",
                          marginRight: "3px",
                        }}
                      >
                        Pending
                      </Button>
                    )}
                    {
                      <Button
                        variant="contained"
                        onClick={() => {
                          navigate(PATH.Traveller.SINGLETRIPINFO, {
                            state: { id: trips.id },
                          });
                        }}
                      >
                        Details
                      </Button>
                    }
                  </Item>
                </Stack>
              </div>
              <br></br>
            </div>
          ))
        ) : (
          <h1>No trip is available !! </h1>
        )}
      </div>
    </Fragment>
  );
}
