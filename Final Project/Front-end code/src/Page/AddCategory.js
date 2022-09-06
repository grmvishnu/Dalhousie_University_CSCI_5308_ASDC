import React, { useEffect, useState } from "react";
import API from "../utils/API";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import Swal from "sweetalert2";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { isLoggedIn } from "../utils/API";
import Button from "@mui/material/Button";

function AddCategory() {
  const navigate = useNavigate();
  let login = useSelector((state) => state.login);
  const [data, setData] = useState({
    categoryName: "",
    amount: 0,
    budgetId: 0,
    selectedBudgetId: 0,
    selectedBudgetName: "",
    trips: [],
    budget: [],
    selectedTripName: "",
    selectedTripId: 0,
  });

  const resetData = () => {
    setData((preState) => {
      return {
        ...preState,
        categoryName: "",
        amount: 0,
        budgetId: 0,
        selectedBudgetId: 0,
        selectedBudgetName: "",
        trips: [],
        budget: [],
        selectedTripName: "",
        selectedTripId: 0,
      };
    });
  };
  const [error, setError] = useState({
    errorInCategoryName: false,
  });

  const validation = () => {
    //console.log(userFirstName);
    let error = false;
    if (data.categoryName == undefined || data.categoryName.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInCategoryName: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInCategoryName: false,
        };
      });
    }
    return error;
  };
  //all trip
  useEffect(async () => {
    isLoggedIn(login, navigate);
    let res = await API().get(`/trip/username/${login.id}`);
    if (res.data.length != 0) {
      setData((preState) => {
        return {
          ...preState,
          trips: res.data,
          selectedTripId: res.data[0].id,
          selectedTripName: res.data[0].name,
        };
      });
    }
  }, []);

  //budget
  useEffect(async () => {
    const getBudgetInfo = async () => {
      let res = await API().get(`/budget/${data.selectedTripId}`);
      if (res.data.length != 0) {
        setData((preState) => {
          return {
            ...preState,
            budget: res.data,
            selectedBudgetId: res.data.id,
            selectedBudgetName: res.data.name,
          };
        });
      }
    };
    if (data.selectedTripId != 0) {
      getBudgetInfo();
    }
  }, [data.selectedTripId]);

  const addCategory = async (e) => {
    e.preventDefault();
    const error = validation();
    if (!error) {
    let res = await API().post("/category/", {
      name: data.categoryName,
      amount: data.maximumAmount ? data.maximumAmount : 10,
      budget_id: data.selectedBudgetId,
    });

    if (res.status == 200 || res.status == 201) {
      //redirect to Login
      Swal.fire("Good job!", "Successfully Add Category !!", "success").then(
        () => {
          navigate("/");
          resetData();
        }
      );
    }
  }
  };

  return (
    <div>
      <TravelerHomePageHeader />
      <form className="mainFormSignup">
        <h2 style={{ textAlign: "center", color: "#5e72e4" }}>
          Category Addition For the Expenses.
        </h2>
        <br></br>
        <label style={{ position: "relative", bottom: "5px" }}>
          Select the Trip:
        </label>
        <select
          name="selectedBudget"
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
        <label style={{ position: "relative", bottom: "5px" }}>
          Your Budget:
        </label>
        <input
          type="text"
          value={data.selectedBudgetName}
          disabled
          className="formFieldSignUp"
        />
        <br></br>

        <label style={{ position: "relative", bottom: "5px" }}>
          Enter Category name:
        </label>
        <input
          type="text"
          value={data.budgetName}
          onChange={(e) => {
            setData((preState) => {
              return {
                ...preState,
                categoryName: e.target.value,
              };
            });
          }}
          placeholder="Category Name:"
          className="formFieldSignUp"
        />
         {error.errorInCategoryName && (
          <div style={{ color: "red" }}>Please enter category name!</div>
        )}
        <br></br>
        <label style={{ position: "relative", bottom: "5px" }}>
          Enter Category Amount:
        </label>
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
            placeholder="Category Amount:"
            className="formFieldSignUp"
          />
        </span>
        
        <br></br>
        <Button
          variant="contained"
          sx={{ margin: "0 auto", display: "flex" }}
          onClick={addCategory}
        >
          Add Category
        </Button>
      </form>
    </div>
  );
}

export default AddCategory;
