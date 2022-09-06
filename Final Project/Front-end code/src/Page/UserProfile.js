import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import "../Css/UserProfile.css";
import PATH from "../utils/Path";
import API from "../utils/API";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import TextField from "@mui/material/TextField";
import MenuItem from "@mui/material/MenuItem";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { isLoggedIn } from "../utils/API";
import Button from "@mui/material/Button";

export default function UserProfile() {
  let login = useSelector((state) => state.login);
  let disptach = useDispatch();
  const [data, setData] = useState({
    userFirstName: "",
    userLastName: "",
    userStreet: "",
    userTripCity: "",
    userPhnNumber: "",
    userPostalCode: "",
    userGender: "",
  });

  const [error, setError] = useState({
    errorInFirstName: false,
    errorInLastName: false,
    errorInStreet: false,
    errorInTripCity: false,
    errorInPhnNumber: false,
    errorInPostalCode: false,
    errorInGender: false,
  });

  const navigate = useNavigate();
  const location = useLocation();

  const validation = () => {
    //console.log(userFirstName);
    let error = false;
    if (data.userFirstName == undefined || data.userFirstName.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInFirstName: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInFirstName: false,
        };
      });
    }

    if (data.userLastName == undefined || data.userLastName.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInLastName: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInLastName: false,
        };
      });
    }

    if (data.userTripCity == undefined || data.userTripCity.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInTripCity: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInTripCity: false,
        };
      });
    }

    if (data.userStreet == undefined || data.userStreet.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInStreet: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInStreet: false,
        };
      });
    }
    if (data.userPhnNumber == undefined || data.userPhnNumber.length != 10) {
      setError((preState) => {
        return {
          ...preState,
          errorInPhnNumber: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInPhnNumber: false,
        };
      });
    }
    if (data.userPostalCode == undefined || data.userPostalCode.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInPostalCode: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInPostalCode: false,
        };
      });
    }
    
    return error;
  };

  useEffect(async () => {
    isLoggedIn(login, navigate);
    await API()
      .get(
        `/profile/${location.state.hostId ? location.state.hostId : login.id}`
      )
      .then((response) => {
        setData((preState) => {
          return {
            ...preState,
            userFirstName: response.data.firstName,
            userLastName: response.data.lastName,
            userGender: response.data.gender,
            userTripCity: response.data.city,
            userPhnNumber: response.data.mobileNumber,
            userPostalCode: response.data.postalCode,
            userStreet: response.data.street,
          };
        });
      });
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    const error = validation();
    if (!error) {
    let res = await API().put("/profile", {
      firstName: data.userFirstName,
      lastName: data.userLastName,
      mobileNumber: data.userPhnNumber,
      street: data.userStreet,
      gender: data.userGender,
      postalCode: data.userPostalCode,
      city: data.userTripCity,
      id: login.id,
    });
    if (res.status == 200 || res.status == 201) {
      Swal.fire("Thank you!", "Saved!!", "success").then(() => {
        navigate(PATH.Traveller.TRAVELERHOMEPAGE);
      });
    }
  }
  };

  return (
    <div>
      <TravelerHomePageHeader />
      <div className="userProfileForm">
        <form>
          <TextField
            label="First name"
            type="text"
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  userFirstName: e.target.value,
                };
              });
            }}
            className="formField"
            value={data.userFirstName}
            variant="outlined"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
          />
          <br></br>
          <br></br>
          {error.errorInFirstName && (
            <div style={{ color: "red" }}>First name is mandatory!</div>
          )}
          <br></br>
          <TextField
            label="Last Name"
            type="text"
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  userLastName: e.target.value,
                };
              });
            }}
            className="formField"
            value={data.userLastName}
            variant="outlined"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
          />
          <br></br>
          <br></br>
          {error.errorInLastName && (
            <div style={{ color: "red" }}>Last name is mandatory!</div>
          )}
          <br></br>
          <TextField
            label="Street"
            type="text"
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  userStreet: e.target.value,
                };
              });
            }}
            className="formField"
            value={data.userStreet}
            variant="outlined"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
          />
          <br></br>
          <br></br>
          {error.errorInStreet && (
            <div style={{ color: "red" }}>Street is mandatory!</div>
          )}
          <br></br>
          <TextField
            label="Phone number"
            type="text"
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  userPhnNumber: e.target.value,
                };
              });
            }}
            className="formField"
            value={data.userPhnNumber}
            variant="outlined"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
          />
          <br></br>
          <br></br>
          {error.errorInPhnNumber && (
            <div style={{ color: "red" }}>
              Phone number is mandatory and should contain 10 digits !
            </div>
          )}
          <br></br>
          <TextField
            label="Postal Code"
            type="text"
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  userPostalCode: e.target.value,
                };
              });
            }}
            className="formField"
            value={data.userPostalCode}
            variant="outlined"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
          />
          <br></br>
          <br></br>
          {error.errorInPostalCode && (
            <div style={{ color: "red" }}>Postal Code is mandatory!</div>
          )}
          <br></br>
          <TextField
            label="City"
            type="text"
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  userTripCity: e.target.value,
                };
              });
            }}
            className="formField"
            value={data.userTripCity}
            variant="outlined"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
          />
          <br></br>
          <br></br>
          {error.errorInTripCity && (
            <div style={{ color: "red" }}>City is mandatory!</div>
          )}
          <br></br>
          <TextField
            label="Select gender"
            className="formFieldSignUp"
            select
            value={data.userGender}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  userGender: e.target.value,
                };
              });
            }}
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
          >
            <MenuItem key="Male" value="Male">
              Male
            </MenuItem>
            <MenuItem key="Female" value="Female">
              Female
            </MenuItem>
          </TextField>
          <br></br>
          <br></br>
          <br></br>
          {location.state && !location.state.disable && (
            <Button variant="contained" type="submit" onClick={onSubmit}>
              Save
            </Button>
          )}
        </form>
      </div>
    </div>
  );
}
