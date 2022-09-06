import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import "../Css/AddExpenses.css";
import { useNavigate } from "react-router-dom";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import API from "../utils/API";
import Swal from "sweetalert2";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { isLoggedIn } from "../utils/API";
import PATH from "../utils/Path";
import Button from "@mui/material/Button";

export default function AddExpense() {
  let disptach = useDispatch();
  const [data, setData] = useState({
    expenseName: "",
    expenseDescription: "",
    amount: 0,
    budgetId: 0,
    selectedBudgetId: 0,
    selectedBudgetName: "",
    trips: [],
    budget: [],
    selectedTripName: "",
    selectedTripId: 0,
    selectedCategoryId: 0,
    selectedCategoryName: "",
    categories: [],
  });
  const navigate = useNavigate();
  let login = useSelector((state) => state.login);
  const [error, setError] = useState({
    errorInExpenseName: false,
  });

  const validation = () => {
    //console.log(userFirstName);
    let error = false;
    if (data.expenseName == undefined || data.expenseName.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInExpenseName: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInExpenseName: false,
        };
      });
    }
    return error;
  };


  const addExpenses = async (e) => {
    e.preventDefault();
    const error = validation();
    if (!error) {
    let res = await API().post("/expense/", {
      name: data.expenseName,
      description: data.expenseDescription,
      amount: data.amount,
      category_id: data.selectedCategoryId,
    });

    if (res.status == 200 || res.status == 201) {
      notify();
      //redirect to Login
      Swal.fire("Good job!", "Successfully Add Expense !!", "success").then(
        () => {
          navigate(PATH.Traveller.TRAVELERHOMEPAGE);
        }
      );
    }
  }
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

  //categories
  useEffect(async () => {
    const getCategoryInfo = async () => {
      let res = await API().get(`/category/budget/${data.selectedBudgetId}`);
      if (res.data.length != 0) {
        setData((preState) => {
          return {
            ...preState,
            categories: res.data,
            selectedCategoryId: res.data[0].id,
            selectedCategoryName: res.data[0].name,
          };
        });
      }
    };
    if (data.selectedBudgetId != 0) {
      getCategoryInfo();
    }
  }, [data.selectedBudgetId]);

  const notify = async () => {
    let res = await API().post(`/notification/budget/`, {
      tripId: data.selectedTripId,
      userId: login.id,
    });

    if (res.data.length !== 0) {
      toast.warn(res.data, {
        position: "top-right",
        autoClose: false,
      });
    }
  };

  useEffect(() => {
    notify();
  }, []);

  return (
    <div>
      <TravelerHomePageHeader />

      <ToastContainer />

      <form className="mainFormSignup">
        <h2 style={{ textAlign: "center", color: "#5e72e4" }}>
          Expense Addition
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
          Select Category:
        </label>
        <select
          name="selectCategory"
          className="formFieldSignUp"
          onChange={(e) => {
            setData((preState) => {
              return {
                ...preState,
                selectedCategoryId: e.target.value,
              };
            });
          }}
        >
          {data.categories.map((category, index) => {
            return (
              <option key={index} value={category.id}>
                {category.name}
              </option>
            );
          })}
        </select>

        <br></br>

        <label style={{ position: "relative", bottom: "5px" }}>
          Enter Expenses Name:
        </label>
        <input
          type="text"
          value={data.expenseName}
          onChange={(e) => {
            setData((preState) => {
              return {
                ...preState,
                expenseName: e.target.value,
              };
            });
          }}
          placeholder="Expenses Name"
          className="formFieldSignUp"
        />
        {error.errorInExpenseName && (
          <div style={{ color: "red" }}>Please enter expense name!</div>
        )}
        <br></br>
        <label style={{ position: "relative", bottom: "5px" }}>
          Enter Expenses Detail:
        </label>
        <input
          type="text"
          value={data.expenseDescription}
          onChange={(e) => {
            setData((preState) => {
              return {
                ...preState,
                expenseDescription: e.target.value,
              };
            });
          }}
          placeholder="Expenses Description"
          className="formFieldSignUp"
        />
        <br></br>
        <label style={{ position: "relative", bottom: "5px" }}>
          Enter Expenses Amount:
        </label>
        <span>
          <input
            type="number"
            value={data.amount}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  amount: e.target.value,
                };
              });
            }}
            placeholder="Expenses Amount:"
            className="formFieldSignUp"
          />
        </span>
        <br></br>
        <Button
          variant="contained"
          sx={{ margin: "0 auto", display: "flex" }}
          onClick={addExpenses}
        >
          Add Expense
        </Button>
      </form>
    </div>
  );
}
