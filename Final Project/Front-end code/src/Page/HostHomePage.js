import React, { useEffect, useState } from "react";
import { Country, City } from "country-state-city";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { useSelector } from "react-redux";
import API from "../utils/API";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import Stack from "@mui/material/Stack";
import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";

import Checkbox from "@mui/material/Checkbox";
import { isLoggedIn } from "../utils/API";

const label = { inputProps: { "aria-label": "Checkbox demo" } };
export default function HostHomePage() {
  const [data, setData] = useState({
    tripName: "",
    country: "IN",
    city: "Ahmedabad",
    startDate: "",
    endDate: "",
    categories: [],
    countries: [],
    accepted: "",
    searchingData: [],
    searchWith: "city",
  });

  const [userIds, setUserIds] = useState([]);
  const [rating, setRating] = useState([]);

  let navigate = useNavigate();
  let login = useSelector((state) => state.login);
  const [traveller, setTraveller] = useState([]);
  const [error, setError] = useState({
    startDate: false,
    endDate: false,
    tripName: false,
  });

  const showTraveller = async (e) => {
    e.preventDefault();
    if (data.searchWith == "city") {
      let res = await API().get(`/trip/city/${data.city}`);
      setData((preState) => {
        return {
          ...preState,
          searchingData: res.data,
        };
      });
    } else {
      let res = await API().get(`/trip/country/${data.country}`);
      setData((preState) => {
        return {
          ...preState,
          searchingData: res.data,
        };
      });
    }
  };

  const changeSearchOption = (option) => {
    setData((preState) => {
      return {
        ...preState,
        searchWith: option,
      };
    });
  };

  useEffect(async () => {
    isLoggedIn(login, navigate);
    const res = await API().get(`/profile/${login.id}`);
    const { firstName, lastName, mobileNumber } = res.data;

    if (!firstName || !lastName || !mobileNumber) {
      navigate(PATH.Traveller.USERPROFILE, {
        state: { disable: false, hostId: login.id },
      });
    }
  }, []);

  useEffect(async () => {
    await API()
      .get(`/building/${login.id}`)
      .then((response) => {
        if (!response.data) {
          navigate(PATH.Traveller.HOSTPROFILECREATION, {
            state: { disable: false, hostId: login.id },
          });
        }
      });
  }, []);

  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
    padding: theme.spacing(1),
    textAlign: "left",
  }));

  return (
    <div>
      <div>
        <TravelerHomePageHeader />
        <div className="mainFormSignup">
          <form>
            <h2
              style={{
                textAlign: "center",
                color: "#5e72e4",
                marginTop: "10px",
                width: "100%",
              }}
            >
              Find Travellers !!
            </h2>
            <br></br>
            <lable style={{ marginBottom: "5px", position: "relative" }}>
              Select country:
            </lable>
            <select
              name="country"
              value={data.country}
              id="country"
              className="formFieldSignUp"
              onChange={(e) => {
                setData((preState) => {
                  return {
                    ...preState,
                    country: e.target.value,
                  };
                });
              }}
            >
              {Country.getAllCountries().map((e, index) => {
                return (
                  <option key={index} label={e.name} value={e.isoCode}>
                    {e.name}
                  </option>
                );
              })}
            </select>

            <br></br>
            <lable style={{ marginBottom: "5px", position: "relative" }}>
              Select city:
            </lable>
            <select
              name="city"
              value={data.city}
              id="city"
              className="formFieldSignUp"
              onChange={(e) => {
                setData((preState) => {
                  return {
                    ...preState,
                    city: e.target.value,
                  };
                });
              }}
            >
              {City.getCitiesOfCountry(data.country).map((e, index) => {
                return (
                  <option key={index} label={e.name} value={e.name}>
                    {e.name}
                  </option>
                );
              })}
            </select>
            <br></br>

            <div style={{ display: "flex", justifyContent: "space-around" }}>
              <div>
                <Checkbox
                  checked={data.searchWith == "city" ? true : false}
                  onChange={() => {
                    changeSearchOption("city");
                  }}
                />
                <label>Search with city only.</label>
              </div>
              <div>
                <Checkbox
                  onChange={() => {
                    changeSearchOption("country");
                  }}
                  checked={data.searchWith == "country" ? true : false}
                />
                <label>Search with Country only.</label>
              </div>
            </div>

            <br></br>

            <Button variant="contained" onClick={showTraveller}>
              Search
            </Button>
          </form>
          <br></br>
          <span>See trips accepted by you?</span>
          <div style={{ marginTop: "5px" }}>
            <Button
              variant="contained"
              onClick={() => {
                navigate(PATH.Traveller.HOSTACCEPTEDTRIP);
              }}
              style={{ width: "100%", marginTop: "5px" }}
            >
              Trips By You
            </Button>
          </div>

          <br></br>
          <br></br>
          <Button
            sx={{ width: "100%", marginTop: "5px" }}
            variant="contained"
            onClick={() => {
              navigate(PATH.Traveller.FEEDBACK);
            }}
          >
            Add a FeedBack
          </Button>

          <Button
            sx={{ width: "100%", marginTop: "5px" }}
            variant="contained"
            onClick={() => {
              navigate(PATH.Traveller.HOSTPROFILECREATION, {
                state: { disable: false, hostId: login.id },
              });
            }}
          >
            Building Information
          </Button>

          <br></br>
          <br></br>
          <>
            <h2 style={{ textAlign: "center", margin: "5px 0" }}>
              {" "}
              Available Trips{" "}
            </h2>
            {data.searchingData.length != 0 ? (
              data.searchingData.map((trip) => {
                if (trip.accepted == "" && trip.user_id != login.id) {
                  return (
                    <div>
                      <Card sx={{ minWidth: 275 }}>
                        <CardContent>
                          <Stack spacing={2}>
                            <Item>
                              <Typography>Name:{trip.name}</Typography>
                            </Item>
                            <Item>
                              <Typography> City: {trip.city}</Typography>
                            </Item>
                            <Item>
                              <Typography> Country: {trip.country}</Typography>
                            </Item>
                            <Item>
                              <Typography>
                                Trip start date: {trip.startDate}
                              </Typography>
                            </Item>
                            <Item>
                              <Typography>
                                Trip end date: {trip.endDate}
                              </Typography>
                            </Item>
                          </Stack>
                        </CardContent>
                        <CardActions>
                          <Button
                            onClick={() => {
                              navigate(
                                PATH.Traveller.HOSTSEARCHSINGLETRIPINFO,
                                {
                                  state: { id: trip.id },
                                }
                              );
                            }}
                            variant="contained"
                            size="small"
                            sx={{ width: "30%", margin: "auto" }}
                          >
                            Detail
                          </Button>
                        </CardActions>
                      </Card>
                      <br></br>
                    </div>
                  );
                }
              })
            ) : (
              <h1 style={{ textAlign: "center" }}>No Trip is available !! </h1>
            )}
          </>
        </div>
      </div>
    </div>
  );
}
