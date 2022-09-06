import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import "../Css/UserProfile.css";
import PATH from "../utils/Path";
import API from "../utils/API";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import TextField from "@mui/material/TextField";
import { useLocation } from "react-router-dom";
import MenuItem from "@mui/material/MenuItem";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { isLoggedIn } from "../utils/API";
import Button from "@mui/material/Button";

export default function HostProfileCreation() {
  let login = useSelector((state) => state.login);
  let disptach = useDispatch();
  const [data, setData] = useState({
    placeDescriprion: "",
    apartmentType: "",
    noOfGuest: 0,
    noOfBeds: 0,
    noOfBedrooms: 0,
    noOfBathrooms: 0,
    parkingAvailability: "",
    kitchenAvailability: "",
    hostAddress: "",
    wifiAvailability: "",
    washerAvailability: "",
    safetyEquipmentAvailability: "",
    address: "",
    id: "",
    buildingId: "",
  });

  const [error, setError] = useState({
    errorInPlaceDescriprion: false,
    errorInNoOfGuest: false,
    errorInNoOfBeds: false,
    errorInNoOfBedrooms: false,
    errorInNoOfBathrooms: false,
    errorInHostAddress: false,
  });

  const validation = () => {
    //console.log(userFirstName);
    let error = false;
    if (
      data.placeDescriprion == undefined ||
      data.placeDescriprion.length == 0
    ) {
      setError((preState) => {
        return {
          ...preState,
          errorInPlaceDescriprion: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInPlaceDescriprion: false,
        };
      });
    }

    if (data.noOfGuest == undefined || data.noOfGuest.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInNoOfGuest: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInNoOfGuest: false,
        };
      });
    }

    if (data.noOfBeds == undefined || data.noOfBeds.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInNoOfBeds: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInNoOfBeds: false,
        };
      });
    }

    if (data.noOfBedrooms == undefined || data.noOfBedrooms.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInNoOfBedrooms: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInNoOfBedrooms: false,
        };
      });
    }
    if (data.noOfBathrooms == undefined) {
      setError((preState) => {
        return {
          ...preState,
          errorInNoOfBathrooms: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInNoOfBathrooms: false,
        };
      });
    }
    if (data.hostAddress == undefined || data.hostAddress.length == 0) {
      setError((preState) => {
        return {
          ...preState,
          errorInHostAddress: true,
        };
      });
      error = true;
    } else {
      setError((preState) => {
        return {
          ...preState,
          errorInHostAddress: false,
        };
      });
    }
    return error;
  };

  const navigate = useNavigate();
  const location = useLocation();

  // house image, availability of place,

  const onSubmit = async (e) => {
    e.preventDefault();
    const error = validation();
    if (!error) {
      let res = await API().put(
        "/building",
        {},
        {
          params: {
            description: data.placeDescriprion,
            typeOfApartment: data.apartmentType,
            noOfGuest: data.noOfGuest,
            noOfBedrooms: data.noOfBedrooms,
            noOfBathrooms: data.noOfBathrooms,
            parking: data.parkingAvailability == 1 ? true : false,
            kitchen: data.kitchenAvailability == 1 ? true : false,
            wifi: data.wifiAvailability == 1 ? true : false,
            address: data.hostAddress,
            washer: data.washerAvailability == 1 ? true : false,
            safetyEquipment:
              data.safetyEquipmentAvailability == 1 ? true : false,
            userId: login.id,
            id: data.buildingId,
          },
        }
      );
      if (res.status == 200 || res.status == 201) {
        //redirect to Login
        Swal.fire(
          "Thank you!",
          "Successfully registered as host!!",
          "success"
        ).then(() => {
          navigate(PATH.Traveller.HOSTHOMEPAGE);
        });
      }
    }
  };

  useEffect(async () => {
    isLoggedIn(login, navigate);
    await API()
      .get(
        `/building/${location.state.hostId ? location.state.hostId : login.id}`
      )
      .then((response) => {
        setData((preState) => {
          return {
            ...preState,
            hostAddress: response.data.address,
            placeDescriprion: response.data.description,
            kitchenAvailability: response.data.kitchen ? 1 : 0,
            id: response.data.id,
            apartmentType: response.data.typeOfApartment
              ? response.data.typeOfApartment
              : "Apartment",
            noOfGuest: response.data.noOfGuest,
            noOfBeds: response.data.noOfBeds,
            noOfBedrooms: response.data.noOfBedrooms,
            noOfBathrooms: response.data.noOfBathrooms,
            parkingAvailability: response.data.parking ? 1 : 0,
            kitchenAvailability: response.data.kitchen ? 1 : 0,
            wifiAvailability: response.data.wifi ? 1 : 0,
            washerAvailability: response.data.washer ? 1 : 0,
            safetyEquipmentAvailability: response.data.safetyEquipment ? 1 : 0,
            buildingId: response.data.id,
          };
        });
      });
  }, []);

  return (
    <div>
      <TravelerHomePageHeader />
      <div className="userProfileForm">
        <form>
          <TextField
            label="Place Description"
            type="text"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  placeDescriprion: e.target.value,
                };
              });
            }}
            className="formField"
            value={data.placeDescriprion}
            label="Enter description of your place"
            variant="outlined"
          />
          <br></br>
          <br></br>
          {error.errorInPlaceDescriprion && (
            <div style={{ color: "red" }}>Please enter place description!</div>
          )}
          <br></br>
          <TextField
            label="Select the type of your place"
            className="formFieldSignUp"
            select
            value={data.apartmentType}
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  apartmentType: e.target.value,
                };
              });
            }}
          >
            <MenuItem key="Apartment" value="Apartment">
              Apartment
            </MenuItem>
            <MenuItem key="House" value="House">
              House
            </MenuItem>
            <MenuItem key="Secondary Unit" value="Secondary Unit">
              Secondary Unit
            </MenuItem>
            <MenuItem key="Shared Unit" value="Shared Unit">
              Shared Unit
            </MenuItem>
          </TextField>
          <br></br>
          <br></br>
          <br></br>
          <TextField
            label="Number of guests"
            type="number"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            InputLabelProps={{
              shrink: true,
            }}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  noOfGuest: e.target.value,
                };
              });
            }}
            value={data.noOfGuest}
            placeholder="Maximum number of guest"
            className="formField"
          />
          <br></br>
          <br></br>
          {error.errorInNoOfGuest && (
            <div style={{ color: "red" }}>Please select no of guests!</div>
          )}
          <br></br>
          <TextField
            label="Number of beds"
            type="number"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            InputLabelProps={{
              shrink: true,
            }}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  noOfBeds: e.target.value,
                };
              });
            }}
            value={data.noOfBeds}
            placeholder="Number of bed"
            className="formField"
          />
          <br></br>
          <br></br>
          {error.errorInNoOfBeds && (
            <div style={{ color: "red" }}>Please enter number of beds!</div>
          )}
          <br></br>
          <TextField
            label="Number of bedrooms"
            type="number"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            InputLabelProps={{
              shrink: true,
            }}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  noOfBedrooms: e.target.value,
                };
              });
            }}
            value={data.noOfBedrooms}
            placeholder="Number of bedrooms"
            className="formField"
          />
          <br></br>
          <br></br>
          {error.errorInNoOfBedrooms && (
            <div style={{ color: "red" }}>Please enter number of bedrooms!</div>
          )}
          <br></br>
          <TextField
            label="Number of bathrooms"
            type="number"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            InputLabelProps={{
              shrink: true,
            }}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  noOfBathrooms: e.target.value,
                };
              });
            }}
            value={data.noOfBathrooms}
            placeholder="Number of bathrooms"
            className="formField"
          />
          <br></br>
          <br></br>
          {error.errorInNoOfBathrooms && (
            <div style={{ color: "red" }}>
              Please enter number of bathrooms!
            </div>
          )}
          <br></br>
          <TextField
            label="Parking facility available?"
            className="formFieldSignUp"
            select
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            value={data.parkingAvailability}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  parkingAvailability: e.target.value,
                };
              });
            }}
          >
            <MenuItem key="Yes" value={1}>
              Yes
            </MenuItem>
            <MenuItem key="No" value={0}>
              No
            </MenuItem>
          </TextField>
          <br></br>
          <br></br>
          <br></br>
          <TextField
            label="Washer facility available?"
            className="formFieldSignUp"
            select
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            value={data.washerAvailability}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  washerAvailability: e.target.value,
                };
              });
            }}
          >
            <MenuItem key="Yes" value={1}>
              Yes
            </MenuItem>
            <MenuItem key="No" value={0}>
              No
            </MenuItem>
          </TextField>
          <br></br>
          <br></br>
          <br></br>
          <TextField
            label="Kitchen facility available?"
            className="formFieldSignUp"
            select
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            value={data.kitchenAvailability}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  kitchenAvailability: e.target.value,
                };
              });
            }}
          >
            <MenuItem key="Yes" value={1}>
              Yes
            </MenuItem>
            <MenuItem key="No" value={0}>
              No
            </MenuItem>
          </TextField>
          <br></br>
          <br></br>
          <br></br>
          <TextField
            label="Wifi facility available?"
            className="formFieldSignUp"
            select
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            value={data.wifiAvailability}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  wifiAvailability: e.target.value,
                };
              });
            }}
          >
            <MenuItem key="Yes" value={1}>
              Yes
            </MenuItem>
            <MenuItem key="No" value={0}>
              No
            </MenuItem>
          </TextField>
          <br></br>
          <br></br>
          <br></br>
          <TextField
            label="Address"
            type="text"
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  hostAddress: e.target.value,
                };
              });
            }}
            className="formField"
            value={data.hostAddress}
            label="Address"
            variant="outlined"
          />
          <br></br>
          <br></br>
          {error.errorInHostAddress && (
            <div style={{ color: "red" }}>Please enter your address!</div>
          )}
          <br></br>
          <TextField
            label="Safety Equipment available?"
            className="formFieldSignUp"
            select
            inputProps={{
              readOnly: location.state ? location.state.disable : true,
            }}
            value={data.safetyEquipmentAvailability}
            onChange={(e) => {
              setData((preState) => {
                return {
                  ...preState,
                  safetyEquipmentAvailability: e.target.value,
                };
              });
            }}
          >
            <MenuItem key="Yes" value={1}>
              Yes
            </MenuItem>
            <MenuItem key="No" value={0}>
              No
            </MenuItem>
          </TextField>
          <br></br>
          <br></br>
          {location.state && !location.state.disable && (
            <Button type="submit" variant="contained" onClick={onSubmit}>
              Become host
            </Button>
          )}
        </form>
      </div>
    </div>
  );
}
