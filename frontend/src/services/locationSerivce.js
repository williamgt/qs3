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
  return axios.get("http://localhost:8085/locations/campus/" + id, config);
}

export function getBuilding(id) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get("http://localhost:8085/locations/building/" + id, config);
}
