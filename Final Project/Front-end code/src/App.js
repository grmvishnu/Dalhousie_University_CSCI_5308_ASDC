import {
  BrowserRouter as Router,
  Switch,
  Route,
  Routes,
  Link,
  useRouteMatch,
  useParams,
  Redirect,
  Navigate,
} from "react-router-dom";
import "./App.css";
import Login from "./Page/Login";
import SignUp from "./Page/SignUp";
import Home from "./Page/Home";
import UserProfile from "./Page/UserProfile";
import SignupConfirmation from "./Page/SignupConfirmation";
import AddTrip from "./Page/AddTrip";
import AddBudget from "./Page/AddBudget";
import AddCategory from "./Page/AddCategory";
import TravelerHomePage from "./Page/TravelerHomePage";
import WebsitePage from "./Page/WebsitePage";
import SingleTripInfo from "./Page/SingleTripInfo";
import AddExpense from "./Page/AddExpenses";
import HostProfileCreation from "./Page/HostProfileCreation";
import TravellerListTrips from "./Page/TravellerListTrips";
import HostAcceptedTrips from "./Page/HostAcceptedTrips";
import HostHomePage from "./Page/HostHomePage";
import HostSearchSingleTripInfo from "./Page/HostSearchSingleTripInfo";
import { useDispatch, useSelector } from "react-redux";
import PATH from "./utils/Path";
import Feedback from "./Page/FeedBack";

function App() {
  let dispatch = useDispatch();
  let login = useSelector((state) => state.login);
  return (
    <div style={{ marginTop: "4%" }}>
      <Router>
        <Routes>
          <Route
            path="/"
            element={login.isLoggedIn ? <TravelerHomePage /> : <WebsitePage />}
          ></Route>

          <Route
            path={PATH.Traveller.USERPROFILE}
            element={<UserProfile />}
          ></Route>

          <Route path={PATH.Traveller.LOGIN} element={<Login />}></Route>

          <Route path={PATH.Traveller.SIGNUP} element={<SignUp />}></Route>

          <Route
            exact
            path={PATH.Traveller.SIGNUPCONFIRMATION}
            element={<SignupConfirmation />}
          ></Route>

          <Route path={PATH.Traveller.ADDTRIP} element={<AddTrip />}></Route>

          <Route
            path={PATH.Traveller.ADDBUDGET}
            element={<AddBudget />}
          ></Route>

          <Route
            path={PATH.Traveller.ADDCATEGORY}
            element={<AddCategory />}
          ></Route>

          <Route
            path={PATH.Traveller.WEBSITEPAGE}
            element={<WebsitePage />}
          ></Route>

          <Route
            path={PATH.Traveller.TRAVELERHOMEPAGE}
            element={<TravelerHomePage />}
          ></Route>

          <Route
            path={PATH.Traveller.SINGLETRIPINFO}
            element={<SingleTripInfo />}
          ></Route>

          <Route
            path={PATH.Traveller.ADDEXPENSE}
            element={<AddExpense />}
          ></Route>

          <Route
            path={PATH.Traveller.ALLTRIP}
            element={<TravellerListTrips />}
          ></Route>

          <Route
            path={PATH.Traveller.HOSTACCEPTEDTRIP}
            element={<HostAcceptedTrips />}
          ></Route>

          <Route
            path={PATH.Traveller.HOSTHOMEPAGE}
            element={<HostHomePage />}
          ></Route>

          <Route
            path={PATH.Traveller.HOSTSEARCHSINGLETRIPINFO}
            element={<HostSearchSingleTripInfo />}
          ></Route>

          <Route
            path={PATH.Traveller.HOSTPROFILECREATION}
            element={<HostProfileCreation />}
          ></Route>

          <Route path={PATH.Traveller.FEEDBACK} element={<Feedback />}></Route>

          <Route path={"/*"} element={<Login />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
