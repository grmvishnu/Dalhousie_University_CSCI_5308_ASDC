import { LOGIN, LOGOUT } from "../utils/Type";

let defaultState = {
  isLoggedIn: false,
};

const Authorization = (state = defaultState, action) => {
  switch (action.type) {
    case LOGIN:
      let newLoginState = { ...state };
      if (action.data.status === 200) {
        newLoginState.isLoggedIn = true;
        newLoginState.accessToken = action.payload.accessToken;
        newLoginState.id = action.payload.id;
      }
      return newLoginState;
    case LOGOUT:
      return { ...defaultState };
    default:
      return state;
  }
};

export default Authorization;
