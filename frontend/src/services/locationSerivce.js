import axios from "axios";
import store from "../store";

export function getCampuses() {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get("http://localhost:8085/locations/campus", config);
}

export function getCampus(id) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.post("http://localhost:8085/getRole/" + id, config);
}
