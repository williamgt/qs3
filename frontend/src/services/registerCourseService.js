import axios from "axios";
import store from "@/store";

export function registerCourseService(course) {
  const config = {
    headers: { Authorization: `Bearer ${store.state.auth.token}` },
  };
  return axios.post("http://localhost:8085/course/register", course, config);
}

export function getStudentsFromFile(file) {
  const config = {
    headers: {
      Authorization: `Bearer ${store.state.auth.token}`,
      "Content-Type": "multipart/form-data",
    },
  };
  var formData = new FormData();
  formData.append("file", file);
  return axios.post(
    "http://localhost:8085/users/upload-students-file",
    formData,
    config
  );
}
