import axios from "axios";

export function registerCourseService(course) {
  axios.post("http://localhost:8085/course/register", course);
}

export function getStudentsFromFile(file) {
  var formData = new FormData();
  formData.append("file", file);
  return axios.post(
    "http://localhost:8085/users/upload-students-file",
    formData,
    {
      headers: { "Content-Type": "multipart/form-data" },
    }
  );
}
