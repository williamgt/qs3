import axios from "axios";
import store from "@/store";

export function getActiveCourses(userId) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get(
    "http://localhost:8085/course/active-courses/" + userId,
    config
  );
}

export function getInactiveCourses(userId) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.get(
    "http://localhost:8085/course/inactive-courses/" + userId,
    config
  );
}
