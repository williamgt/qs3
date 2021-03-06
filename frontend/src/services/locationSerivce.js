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

export function registerCampus(name) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.post("http://localhost:8085/locations/campus", name, config);
}

export function registerRoom(room) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.post("http://localhost:8085/locations/room", room, config);
}

export function registerBuilding(building) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.post(
    "http://localhost:8085/locations/building",
    building,
    config
  );
}

export function updateCampus(campus) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.put("http://localhost:8085/locations/campus", campus, config);
}

export function updateBuilding(building) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.put(
    "http://localhost:8085/locations/building",
    building,
    config
  );
}

export function getRoom(id) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get("http://localhost:8085/locations/room/" + id, config);
}

export function updateRoom(room) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.put("http://localhost:8085/locations/room", room, config);
}
