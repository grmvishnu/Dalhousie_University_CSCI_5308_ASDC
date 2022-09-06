import React, { useEffect, useState } from "react";
import {
  MdConfirmationNumber,
  MdOutlineDriveFileRenameOutline,
} from "react-icons/md";
import { GiMoneyStack } from "react-icons/gi";
import API from "../utils/API";

export default function ShowExpenses(props) {
  const trip_id = props.tripId;
  const [responseCategory, setResponseCategory] = useState([]);

  useEffect(async () => {
    const response = await API().get(`/category/budget/${trip_id}`);
    setResponseCategory(response.data);
  }, []);

  return (
    <div>
      {responseCategory.map((category, index) => (
        <div key={index}>
          <h4>
            {" "}
            {MdConfirmationNumber(undefined)} Category ID : {category.id}
          </h4>
          <h4>
            {" "}
            {MdOutlineDriveFileRenameOutline(undefined)} Category Name :{" "}
            {category.name}
          </h4>
          <h4>
            {" "}
            {GiMoneyStack(undefined)} Category Amount : {category.amount}
          </h4>
        </div>
      ))}
    </div>
  );
}
