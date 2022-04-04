import { getAllUsers } from "@/services/userService";

export default async function getUsers() {
  let u;
  getAllUsers()
    .then((response) => {
      u = response.data;
      return response.data;
    })
    .catch((error) => {
      console.log(error);
    });
  return u;
}
