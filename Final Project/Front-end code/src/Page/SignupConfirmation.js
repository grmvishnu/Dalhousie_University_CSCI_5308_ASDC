import React, { useEffect, useState } from "react";
import { useParams, useSearchParams, useNavigate } from "react-router-dom";
import ReactLoading from "react-loading";
import Login from "./Login";
import PATH from "../utils/Path";
import "../Css/SignupConfirmation.css";
import API from "../utils/API";

function SignupConfirmation() {
  // const { token } = useParams();
  const [loading, setloading] = useState(true);

  const token = new URLSearchParams(window.location.search).get("token");

  const navigate = useNavigate();

  useEffect(async () => {
    const res = await API().get(`/registrationConfirm?token=${token}`);
    if (res.status == 200) {
      setloading(false);
      navigate(PATH.Traveller.LOGIN);
    }
  }, []);

  return (
    <div>
      {loading ? (
        <div>
          {" "}
          <p>Please wait while we're working on your request !!!</p>
          <br></br>
          <div className="loader">
            <ReactLoading
              type={"spin"}
              color={"black"}
              height={100}
              width={100}
            />
          </div>
        </div>
      ) : (
        <div>
          <Login />
        </div>
      )}
    </div>
  );
}

export default SignupConfirmation;
