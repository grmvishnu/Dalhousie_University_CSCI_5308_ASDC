import React from "react";
import { LOGOUT } from "../utils/Type";
import { useDispatch, useSelector } from "react-redux";
import Button from "@mui/material/Button";

function Home() {
  let dispatch = useDispatch();

  const onLogout = () => {
    dispatch({ type: LOGOUT });
  };

  return (
    <div>
      <p>Home</p>
      <Button variant="contained" onClick={onLogout}>
        Log out
      </Button>
    </div>
  );
}

export default Home;
