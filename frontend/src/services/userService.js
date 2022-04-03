import axios from "axios";
import store from "../store";

export function getAllUsers() {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get("http://localhost:8085/users/all", config);
}

export function getUser(id) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get("http://localhost:8085/users/user/" + id + "/all", config);
}

export function updateUser(newUserData) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.put(
    "http://localhost:8085/users/user/" + newUserData.id,
    newUserData,
    config
  );
}

export function deleteUser(user) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.delete("http://localhost:8085/users/user/" + user.id, config);
}
