import { applyMiddleware, compose, createStore } from "redux";
import thunk from "redux-thunk";

export default function configureStore(persistedReducer) {
  const store = createStore(
    persistedReducer,
    compose(
      applyMiddleware(thunk),
      window.__REDUX_DEVTOOLS_EXTENSION__ &&
        window.__REDUX_DEVTOOLS_EXTENSION__()
    )
  );
}
