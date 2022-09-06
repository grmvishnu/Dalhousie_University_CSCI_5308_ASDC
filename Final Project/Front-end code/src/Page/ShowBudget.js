import React, { useEffect, useState } from "react";
import { FiChevronsRight } from "react-icons/fi";
import { TextField } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import API from "../utils/API";
import { isLoggedIn } from "../utils/API";
import Button from "@mui/material/Button";

export default function ShowBudget(props) {
  const trip_id = props.tripId;
  const responseBudget = props.responseData;
  const [maxMoney, setMaxMoney] = useState("");
  const [clicked, setClicked] = useState(false);
  const temp = responseBudget;

  const navigate = useNavigate();
  let login = useSelector((state) => state.login);

  function getDetails() {
    setMaxMoney(responseBudget.maxAmount);
  }

  const UpdateBudget = async (e) => {
    e.preventDefault();
    let res = await API().put("/budgetUpdate/", {
      id: temp.id,
      name: temp.name,
      amountSpent: temp.amountSpent,
      maxAmount: maxMoney,
      trip_id: temp.trip_id,
    });

    if (res.status == 200) {
      Swal.fire("Budget updated!", "success").then(() => {
        getDetails();
      });
    }
  };

  useEffect(() => {
    isLoggedIn(login, navigate);
  });

  return (
    <div className="users">
      <div>
        <h4>
          {FiChevronsRight(undefined)} Maximum amount:{" "}
          {responseBudget.maxAmount}
        </h4>
        <br />
        <h4>
          {FiChevronsRight(undefined)} Amount Spent:{" "}
          {responseBudget.amountSpent}
        </h4>
        <br />
        <Button variant="contained" onClick={() => setClicked(true)}>
          Update maximum amount
        </Button>
      </div>

      {clicked && (
        <div>
          <TextField
            type="number"
            id="outlined-basic"
            required
            label="Enter amount"
            variant="outlined"
            value={maxMoney}
            onChange={(e) => {
              setMaxMoney(e.target.value);
            }}
          />
          <br />
          <br />
          <Button variant="contained" type="submit" onClick={UpdateBudget}>
            Save
          </Button>
        </div>
      )}
    </div>
  );
}
