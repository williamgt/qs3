import axios from "axios";

export function getActiveCourses(userId) {
  return axios.get("http://localhost:8085/course/active-courses/" + userId);
}

export function getInactiveCourses(userId) {
  return axios.get("http://localhost:8085/course/inactive-courses/" + userId);
}
