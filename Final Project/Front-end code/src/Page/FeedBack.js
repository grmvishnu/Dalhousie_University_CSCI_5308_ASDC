import React, { useEffect, useState } from "react";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import API from "../utils/API";
import { isLoggedIn } from "../utils/API";
import Swal from "sweetalert2";
import { FaStar } from "react-icons/fa";
import PATH from "../utils/Path";
import Rating from "@mui/material/Rating";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import Button from "@mui/material/Button";

export default function Feedback() {
  let dispatch = useDispatch();
  const stars = Array(5).fill(0);
  const navigate = useNavigate();
  const [currentRating, setCurrentRating] = useState(0);
  const [hoverValue, setHoverValue] = useState(null);
  const [isRatingGiven, setIsRatingGiven] = useState(false);
  const [snackbar, setSnackbar] = useState(true);
  const [data, setData] = useState({
    feedbackContent: "",
    trips: [],
    selectedTripName: "",
    selectedTripId: 0,
  });

  let login = useSelector((state) => state.login);

  const addFeedback = async (e) => {
    e.preventDefault();
    let res = await API().post("/fdb/", {
      content: currentRating.toString(),
      trip_id: data.selectedTripId,
    });

    if (res.status == 200 || res.status == 201) {
      Swal.fire("Good job!", "Feedback given successfully !!", "success").then(
        () => {
          navigate(PATH.Traveller.HOSTHOMEPAGE);
          setData({});
        }
      );
    }
  };

  //all trips
  useEffect(async () => {
    isLoggedIn(login, navigate);
    let res = await API().get(`/trip/accepted/${login.id}`);
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

  useEffect(async () => {
    if (data.selectedTripId != 0) {
      let res = await API().get(`/feedback/${data.selectedTripId}`);
      const rating = res.data.length != 0 ? res.data[0].content : 0;
      setSnackbar(true);

      if (rating != 0) {
        setIsRatingGiven(true);
      } else {
        setIsRatingGiven(false);
      }

      setCurrentRating(
        parseInt(res.data.length != 0 ? res.data[0].content : 0)
      );
    }
  }, [data.selectedTripId]);

  return (
    <div>
      <TravelerHomePageHeader />
      <div className="mainFormSignup">
        <form>
          <h2
            style={{
              textAlign: "center",
              color: "#1976d2",
              marginTop: "10px",
              width: "100vh",
            }}
          >
            Feedback Form!
          </h2>
          <br></br>
          <lable style={{ bottom: "5px", position: "relative" }}>
            Please select your trip:
          </lable>
          <select
            name="selectedtrip"
            required
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
            Select rating for your trip:
          </label>
          <div style={{ position: "relative", marginBottom: "5px" }}>
            <Rating
              name="size-large"
              defaultValue={0}
              size="large"
              value={currentRating}
              onChange={(e) => {
                setCurrentRating(parseInt(e.target.value));
              }}
            />
          </div>
          <br></br>
          <br></br>
          {!isRatingGiven ? (
            <Button
              variant="contained"
              sx={{ margin: "0 auto", display: "flex" }}
              onClick={addFeedback}
            >
              Submit
            </Button>
          ) : (
            <Snackbar
              open={snackbar}
              autoHideDuration={3000}
              onClose={() => {
                setSnackbar(false);
              }}
            >
              <Alert severity="error">You have already given a rating !!</Alert>
            </Snackbar>
          )}
        </form>
      </div>
    </div>
  );
}
