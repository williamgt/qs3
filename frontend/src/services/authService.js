import axios from "axios";
import store from "../store";

export function getToken(username, password) {
  const params = new URLSearchParams();
  params.append("username", username);
  params.append("password", password);
  return axios.post("http://localhost:8085/token", params);
}

export function login(loginRequest) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.post("http://localhost:8085/login", loginRequest, config);
}
