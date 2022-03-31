import { getAllUsers } from "@/services/userService";

export default async function getUsers() {
  let u;
  getAllUsers()
    .then((response) => {
      console.log(response);
      console.log(response.data);
      u = response.data;
      return response.data;
    })
    .catch((error) => {
      console.log(error);
    });
  await console.log(u);
  return u;
}
