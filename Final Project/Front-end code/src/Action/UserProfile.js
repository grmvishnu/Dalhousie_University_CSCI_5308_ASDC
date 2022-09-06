import API, { getDataMiddleware } from "../utils/API";
import { CREATEPROFILE } from "../utils/Type";

export const createProfile = ({ userTripCity, userStartTripDate, userEndTripDate }) => {
  return async (dispatch) => {
    let res;
    try {
      res = getDataMiddleware(
        await API().post("/user/createProfile", JSON.stringify({ userTripCity, userStartTripDate, userEndTripDate }))
      );
    } catch (error) {
      console.log(error);
      //   error && error.response && setRes(error.response.data);
      //   dispatch({
      //     type: HIDE_LOADER,
      //   });
    }
  };
};
