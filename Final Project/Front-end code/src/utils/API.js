import axios from "axios";
import { Store } from "../index";
import { URL, APP_URL } from "./config.js";
import { useNavigate } from "react-router-dom";
import PATH from "../utils/Path";

export default (type) => {
  return axios.create({
    baseURL: URL,
    headers: {
      "Content-Type": "application/json",
      Authorization: Store.getState().login.accessToken
        ? "Bearer " + Store.getState().login.accessToken
        : "",
    },
  });
};

export const isLoggedIn = (data, navigate) => {
  if (!data.isLoggedIn) {
    navigate(PATH.Traveller.LOGIN);
  }
};
