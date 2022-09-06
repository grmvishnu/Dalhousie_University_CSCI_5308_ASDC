import React, { useEffect, useState } from "react";
import "../Css/SingleTripInfo.css";
import API from "../utils/API";
import TravelerHomePageHeader from "../Component/TravelerHomePageHeader";
import { useLocation } from "react-router-dom";
import { useSelector } from "react-redux";
import Stack from "@mui/material/Stack";
import Card from "@mui/material/Card";
import { styled } from "@mui/material/styles";
import { useNavigate } from "react-router-dom";
import Paper from "@mui/material/Paper";
import Swal from "sweetalert2";
import PATH from "../utils/Path";
import "../Css/Signup.css";
import Button from "@mui/material/Button";
import Rating from "@mui/material/Rating";
import { isLoggedIn } from "../utils/API";

export default function HostSearchSingleTripInfo() {
  let login = useSelector((state) => state.login);
  const [responseData, setResponseData] = useState([]);
  const [rating, setRating] = useState(0);
  const [loading, setLoading] = useState(false);
  const temp = responseData;
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(async () => {
    isLoggedIn(login, navigate);
    await API()
      .get(`trip/${location.state.id}`)
      .then((response) => {
        setResponseData(response.data);
      });
  }, []);

  const acceptTraveller = async (e) => {
    try {
      e.preventDefault();
      setLoading(true);
      let res = await API().put(`trip/`, {
        id: temp.id,
        name: temp.name,
        startDate: temp.startDate,
        endDate: temp.endDate,
        country: temp.country,
        city: temp.city,
        user_id: temp.user_id,
        accepted: login.id,
      });

      setLoading(false);
      if (res.status == 200 || res.status == 201) {
        //redirect to Login
        Swal.fire("Request Accepted", "Notification sent", "success").then(
          () => {
            navigate(PATH.Traveller.HOSTHOMEPAGE);
          }
        );
      }
    } catch {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Something went wrong! Please try again",
      }).then(() => {
        navigate(PATH.Traveller.HOSTHOMEPAGE);
        setLoading(false);
      });
    }
  };

  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
    padding: theme.spacing(1),
    textAlign: "left",
  }));

  useEffect(async () => {
    if (responseData.length != 0) {
      //get all trips by userId
      const res = await API().get(`/trip/username/${responseData.user_id}`);
      let total_trip = 0;
      var rating = 0;

      for (let i = 0; i < res.data.length; i++) {
        const response = await API().get(`/feedback/${res.data[i].id}`);
        if (response.data && response.data.length != 0) {
          total_trip++;
          rating = rating + parseInt(response.data[0].content);
        }
      }

      setRating(rating == 0 ? rating : rating / total_trip);
    }
  }, [responseData]);

  return (
    <div>
      <TravelerHomePageHeader />
      <div>
        <Card sx={{ display: "flex", background: "aliceblue" }}>
          <img
            style={{ width: "50vw", height: "100vh" }}
            src="https://www.tourism.australia.com/content/corporate/en/news-and-media/news-stories/new-high-value-traveller-profiles-for-international-markets/jcr:content/mainParsys/imagewithcaption/LargeImageTile/largeImageSrc.adapt.740.medium.jpg"
          ></img>
          <div style={{ margin: "auto" }}>
            <h1 style={{ marginBottom: "5%" }}>
              {" "}
              Information about the traveller !!
            </h1>
            <div
              style={{
                left: "50%",
                transform: "translateX(-50%)",
                position: "relative",
              }}
            >
              <Stack spacing={2}>
                <Item>Trip name: {temp.name}</Item>
                <Item>
                  Start From: {temp.startDate} {"  to  "} {temp.endDate}
                </Item>
                <Item>City: {temp.city}</Item>
                <Item>Country: {temp.country}</Item>
                <Item>
                  <div>
                    User's rating :
                    <Rating
                      name="size-large"
                      defaultValue={0}
                      size="large"
                      value={rating}
                      readOnly
                      precision={0.5}
                    />
                  </div>
                </Item>
              </Stack>
            </div>
            <br></br>

            {temp.accepted == "" ? (
              <div
                style={{
                  display: "grid",
                  alignContent: "space-around",
                }}
              >
                {!loading ? (
                  <Button variant="contained" onClick={acceptTraveller}>
                    Accept
                  </Button>
                ) : (
                  <Button variant="contained" disabled>
                    Loading..
                  </Button>
                )}

                <br></br>
              </div>
            ) : (
              <p></p>
            )}
          </div>
        </Card>
      </div>
    </div>
  );
}
