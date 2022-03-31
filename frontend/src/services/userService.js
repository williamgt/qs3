import axios from "axios";
import store from "../store";

export function getAllUsers() {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  console.log(store.state.auth.token);
  return axios.get("http://localhost:8085/users/all", config);
}
