import React, { Fragment, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import TravellerListTrips from "./TravellerListTrips";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import Button from "@mui/material/Button";
import { isLoggedIn } from "../utils/API";
import { useLocation } from "react-router-dom";
import API from "../utils/API";
import Stack from "@mui/material/Stack";
import "../Css/HostAcceptedTrips.css";
import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";

export default function HostAcceptedTrips() {
  const [trips, setTrips] = useState(null);
  let state = useSelector((state) => state.login);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(async () => {
    isLoggedIn(state, navigate);
    const response = await API().get(`/trip/accepted/${state.id}`);
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
        Trips Accepted by you!
      </h2>
      <br></br>
      <div>
        {trips != null ? (
          trips.map((trips, index) => (
            <div key={trips.id}>
              <div className="list-of-accepted-trip">
                <Stack spacing={2}>
                  <Item>Trip name : {trips.name}</Item>
                  <Item>City : {trips.city}</Item>
                  <Item>
                    Start From : {trips.startDate} {"  to  "} {trips.endDate}
                  </Item>
                  <Item>Country : {trips.country}</Item>
                  <Item>
                    {
                      <Button
                        variant="contained"
                        onClick={() => {
                          navigate(PATH.Traveller.USERPROFILE, {
                            state: {
                              disable: true,
                              hostId: trips.user_id,
                            },
                          });
                        }}
                      >
                        Traveller's Details
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
