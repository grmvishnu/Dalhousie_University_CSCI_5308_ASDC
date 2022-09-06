import API from "../utils/API";
import { LOGIN } from "../utils/Type";
import Swal from "sweetalert2";

export const login = ({
  userName,
  password,
  navigateToHomePage,
  closeLoader,
}) => {
  return async (dispatch) => {
    try {
      let res = await API().post(
        "/signin",
        JSON.stringify({ username: userName, password })
      );

      dispatch({
        type: LOGIN,
        payload: res.data,
        data: {
          status: res.status,
        },
      });
      closeLoader();
      navigateToHomePage();
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Something went wrong! Please try again",
      });
      closeLoader();
    }
  };
};
