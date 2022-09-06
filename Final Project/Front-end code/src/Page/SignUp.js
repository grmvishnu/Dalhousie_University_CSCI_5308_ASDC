import React, { useState } from "react";
import "../Css/Signup.css";
import API from "../utils/API";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";
import Swal from "sweetalert2";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import Button from "@mui/material/Button";

export default function SignUp() {
  const [data, setData] = useState({
    email: "",
    pwd: "",
    confirmPwd: "",
    role: "",
  });

  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const [error, setError] = useState({
    different_pwd: false,
    isEmailNull: false,
  });

  //check pwd and confirm pwd
  const checkPwdValidation = (e) => {
    //set an error if pwd and confirmPwd is not matched
    if (data.pwd != data.confirmPwd) {
      setError((preState) => {
        return {
          ...preState,
          different_pwd: true,
        };
      });
    } else {
      setError((preState) => {
        return {
          ...preState,
          different_pwd: false,
        };
      });
    }
  };

  //check value validation
  const emailValidation = () => {
    //is Email null or not
    if (!data.email) {
      setError((preState) => {
        return {
          ...preState,
          isEmailNull: true,
        };
      });
    } else {
      setError((preState) => {
        return {
          ...preState,
          isEmailNull: false,
        };
      });
    }
  };

  //on submit of signup
  const onSignUp = async (e) => {
    try {
      setLoading(true);
      e.preventDefault();
      //check for the validation
      if (!error.different_pwd && !error.isEmailNull) {
        //call an api for the signup
        let res = await API().post("/signup", {
          username: data.email,
          role: [data.role],
          password: data.pwd,
        });

        setLoading(false);
        if (res.status == 200 || res.status == 201) {
          //redirect to Login
          Swal.fire(
            "Good job!",
            "Successfully Register, Please check your Email !!",
            "success"
          ).then(() => {
            navigate(PATH.Traveller.LOGIN);
          });
        } else {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Something went wrong! Please try again",
          }).then(() => {
            setLoading(false);
          });
        }
      } else {
        setLoading(false);
      }
    } catch (error) {
      setLoading(false);
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Something went wrong!",
      }).then(() => {
        navigate(PATH.Traveller.SIGNUP);
        setData((preState) => {
          return {
            ...preState,
            email: "",
            pwd: "",
            confirmPwd: "",
            role: "",
          };
        });
      });
    }
  };

  return (
    <div style={{ marginTop: "10%" }}>
      <TravelerHomePageHeader />
      <div className="mainFormSignup">
        <form>
          <input
            type="text"
            value={data.email}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  email: e.target.value,
                };
              });
            }}
            onBlur={emailValidation}
            placeholder="Email:"
            className="formFieldSignUp"
          />

          {error.isEmailNull && (
            <div style={{ color: "red", marginRight: "19%" }}>
              Email cannot be null!
            </div>
          )}

          <br></br>

          <select
            className="formFieldSignUp"
            aria-label="Select one role."
            onChange={(data) => {
              setData((preState) => {
                return {
                  ...preState,
                  role: data.target.value,
                };
              });
            }}
          >
            <option>Select a role</option>
            <option defaultValue value="ROLE_TRAVELLER">
              Traveller
            </option>
            <option value="ROLE_HOST">Host</option>
          </select>
          <br></br>
          <input
            type="password"
            placeholder="Password:"
            className="formFieldSignUp"
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  pwd: e.target.value,
                };
              });
            }}
          />
          <br></br>
          <input
            type="password"
            placeholder="Confirm Password:"
            className="formFieldSignUp"
            onBlur={checkPwdValidation}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  confirmPwd: e.target.value,
                };
              });

              checkPwdValidation();
            }}
          />
          {error.different_pwd && (
            <p style={{ color: "red" }}>Password should be matched!</p>
          )}
          <br></br>
          <div style={{ display: "flex", justifyContent: "center" }}>
            {!loading ? (
              <Button variant="contained" onClick={onSignUp}>
                SignUp
              </Button>
            ) : (
              <Button variant="contained" disabled>
                Loading..
              </Button>
            )}
          </div>
          <br></br>
          <div>
            <label>Have an account ?</label>
            <div>
              <Button
                type="submit"
                variant="contained"
                sx={{ width: "100%", marginTop: "5px" }}
                onClick={() => {
                  navigate(PATH.Traveller.LOGIN);
                }}
              >
                Log In
              </Button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}
