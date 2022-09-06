import React, { useEffect, useState } from "react";
import API from "../utils/API";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import Swal from "sweetalert2";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { isLoggedIn } from "../utils/API";
import Button from "@mui/material/Button";

function AddBudget() {
  const [data, setData] = useState({
    trips: [],
    selectedTripName: 0,
    selectedTripId: 0,
    budgetName: "",
    maximumAmount: 0,
  });

  const [error, setError] = useState({
    errorInBudgetName: false,
    errorInMaximumAmount: false,
  });

  const validation = () => {
    //console.log(userFirstName);
    let error = false;
    if (data.budgetName == undefined || data.budgetName.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInBudgetName: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInBudgetName: false,
        };
      });
    }
    if (data.maximumAmount == undefined || data.maximumAmount.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInMaximumAmount: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInMaximumAmount: false,
        };
      });
    }
    return error;
  };

  //UserId
  let login = useSelector((state) => state.login);
  const navigate = useNavigate();

  //call an api for all trip
  useEffect(async () => {
    isLoggedIn(login, navigate);
    API()
      .get(`/trip/username/${login.id}`)
      .then((res) => {
        if (res.data.length != 0) {
          setData((preState) => {
            return {
              ...preState,
              trips: res.data,
              selectedTripId: res.data[res.data.length - 1].id,
              selectedTripName: res.data[res.data.length - 1].name,
            };
          });
        }
      })
      .catch((err) => {
        console.log("Error in addBudget ", err);
      });
  }, []);

  const addBudget = async (e) => {
    e.preventDefault();
    const error = validation();
    if (!error) {
      //api call
      let res = await API().post("/budgetAdd/", {
        name: data.budgetName,
        maxAmount: data.maximumAmount ? data.maximumAmount : 0,
        trip_id: data.selectedTripId,
      });

      if (res.status == 200 || res.status == 201) {
        //redirect to Login
        Swal.fire("Good job!", "Successfully Add Budget !!", "success").then(
          () => {
            navigate(PATH.Traveller.ADDCATEGORY);
          }
        );
      } else {
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: "Something went wrong! Please try again",
        });
      }
    }
  };

  return (
    <div>
      <TravelerHomePageHeader />
      <form className="mainFormSignup">
        <h2 style={{ textAlign: "center", color: "#5e72e4" }}>
          Budget Addition
        </h2>
        <br></br>
        <label></label>
        <select
          name="selectedTrip"
          className="formFieldSignUp"
          onChange={(e) => {
            setData((preState) => {
              return {
                ...preState,
                selectedTripId: e.target.value,
              };
            });
          }}
        >
          {data.trips.map((trip, index) => {
            return (
              <option key={index} value={trip.id}>
                {trip.name}
              </option>
            );
          })}
        </select>
        <br></br>
        <input
          type="text"
          value={data.budgetName}
          onChange={(e) => {
            setData((preState) => {
              return {
                ...preState,
                budgetName: e.target.value,
              };
            });
          }}
          placeholder="Budget Name:"
          className="formFieldSignUp"
        />
        {error.errorInBudgetName && (
          <div style={{ color: "red" }}>Please enter budget name!</div>
        )}
        <br></br>
        <span>
          <input
            type="number"
            value={data.maximumAmount}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  maximumAmount: e.target.value,
                };
              });
            }}
            placeholder="Maximum Amount of Budget:"
            className="formFieldSignUp"
          />
        </span>
        {error.errorInMaximumAmount && (
          <div style={{ color: "red" }}>Please enter maximum amount!</div>
        )}
        <br></br>

        <Button
          variant="contained"
          sx={{ margin: "0 auto", display: "flex" }}
          onClick={addBudget}
        >
          Add Budget
        </Button>
      </form>
    </div>
  );
}

export default AddBudget;
