import React, { useEffect, useState } from "react";
import { Country, State, City } from "country-state-city";
import API from "../utils/API";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import Swal from "sweetalert2";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { isLoggedIn } from "../utils/API";
import Button from "@mui/material/Button";

function AddTrip() {
  const [data, setData] = useState({
    tripName: "",
    country: "IN",
    city: "Ahmedabad",
    startDate: "",
    endDate: "",
    categories: [],
    countries: [],
    accepted: "",
  });

  const navigate = useNavigate();
  let login = useSelector((state) => state.login);

  const [error, setError] = useState({
    startDate: false,
    endDate: false,
    tripName: false,
    errorInTripName: false,
  });

  const validation = () => {
    let error = false;
    if (data.tripName == undefined || data.tripName.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInTripName: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInTripName: false,
        };
      });
    }
    return error;
  };

  useEffect(() => {
    isLoggedIn(login, navigate);
    setData((preState) => {
      return {
        ...preState,
        countries: [...data.countries, Country.getAllCountries()],
      };
    });
  }, []);

  //date validation
  const dateValidation = (date, element) => {
    //past date is selected
    if (new Date().getTime() > date) {
      //set error
      setError((preState) => {
        return {
          ...preState,
          [element]: true,
        };
      });
      return false;
    } else {
      //remove error
      setError((preState) => {
        return {
          ...preState,
          [element]: false,
        };
      });
      return true;
    }
  };

  //add trip
  const addTripBtn = async (e) => {
    e.preventDefault();
    const error = validation();
    if (!error) {
      //api call
      let res = await API().post("/trip/", {
        startDate: data.startDate,
        endDate: data.endDate,
        country: data.country,
        city: data.city,
        user_id: login.id,
        name: data.tripName,
        accepted: data.accepted,
      });

      if (res.status == 200 || res.status == 201) {
        //redirect to Login
        Swal.fire("Good job!", "Successfully Add Trip !!", "success").then(
          () => {
            navigate(PATH.Traveller.ADDBUDGET);
            setData({
              tripName: "",
              country: "IN",
              city: "Andkhoy",
              startDate: "",
              endDate: "",
              categories: [],
              countries: [],
              accepted: "",
            });
          }
        );
      }
    }
  };

  return (
    <div>
      <TravelerHomePageHeader />
      <div className="mainFormSignup">
        <form>
          <h2
            style={{
              textAlign: "center",
              color: "#5e72e4",
              marginTop: "10px",
              width: "100vh",
            }}
          >
            Trip Addition
          </h2>
          <br></br>
          <lable style={{ marginBottom: "5px", position: "relative" }}>
            Trip Name:
          </lable>
          <input
            type="text"
            value={data.tripName}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  tripName: e.target.value,
                };
              });
            }}
            placeholder="Trip Name:"
            className="formFieldSignUp"
          />
          {error.errorInTripName && (
            <div style={{ color: "red" }}>Please enter trip name!</div>
          )}
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
          <lable style={{ marginBottom: "5px", position: "relative" }}>
            Select start date:
          </lable>
          <input
            type="date"
            value={data.startDate}
            onChange={(e) => {
              if (
                dateValidation(new Date(e.target.value).getTime(), "startDate")
              ) {
                setData((preState) => {
                  return {
                    ...preState,
                    startDate: e.target.value,
                  };
                });
              }
            }}
            placeholder="Start Date:"
            className="formFieldSignUp"
          />
          {error.startDate && (
            <div style={{ color: "red" }}>Please select Valid Date!!</div>
          )}
          <br></br>
          <lable style={{ marginBottom: "5px", position: "relative" }}>
            Select End Date:
          </lable>
          <input
            type="date"
            value={data.endDate}
            onChange={(e) => {
              if (
                dateValidation(new Date(e.target.value).getTime(), "endDate")
              ) {
                setData((preState) => {
                  return {
                    ...preState,
                    endDate: e.target.value,
                  };
                });
              }
            }}
            placeholder="End Date:"
            className="formFieldSignUp"
          />
          {error.endDate && (
            <div style={{ color: "red" }}> Please select Valid Date!!</div>
          )}
          <br></br>
          <Button
            variant="contained"
            onClick={addTripBtn}
            sx={{ margin: "0 auto", display: "flex" }}
          >
            Add Trip
          </Button>
        </form>
      </div>
    </div>
  );
}

export default AddTrip;
