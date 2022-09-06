import React from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import API from "../utils/API";

toast.configure();
export default function Notification(props) {
  const user_id = props.userId;
  const trip_id = props.tripId;
  const notify = async (e) => {
    e.preventDefault();
    let res = await API().post(`/notification/budget/`, {
      tripId: trip_id,
      userId: user_id,
    });

    if (res.data.length !== 0) {
      toast.warn(res.data, {
        position: "top-right",
        autoClose: false,
      });
    }
  };

  return (
    <div>
      {notify()}
      <ToastContainer />
    </div>
  );
}
