import { getRole, getToken, login } from "@/services/authService";
import store from "../store";
import { hasAdminAccess, hasTAAccess, hasTeacherAccess } from "@/api/AuthAPI";
import router from "@/router";
export default async function doLogin(userLogin) {
  let token;
  let errorM = "";
  await getToken(userLogin.email, userLogin.password)
    .then((data) => {
      token = data.data;
      store.dispatch("setToken", token).then(
        login(userLogin).then(async (resolvedResult) => {
          if (resolvedResult.data) {
            await store.dispatch("setLogin", resolvedResult.data).then(() => {
              setRole().then(() => {
                router.push("/");
              });
            });
          }
        })
      );
    })
    .catch((error) => {
      console.log(error.response.status === 401);
      if (error)
        if (error.response.status === 403 || error.response.status === 401) {
          errorM = "Wrong username or password";
        } else {
          errorM =
            "Unknown error. Try again later. Code: " + error.response.status;
        }
    });
  return errorM;
}

export async function setRole() {
  await getRole(store.state.personLoggedIn)
    .then(async (response) => {
      await store.dispatch("setRole", response.data);
      //Sets navbar for logged in
      if (hasAdminAccess(response.data)) {
        await store.dispatch("setNavbar", store.state.navbar.admin);
      } else if (hasTeacherAccess(response.data)) {
        await store.dispatch("setNavbar", store.state.navbar.teacher);
      } else if (hasTAAccess(response.data)) {
        await store.dispatch("setNavbar", store.state.navbar.ta);
      } else {
        await store.dispatch(
          "setNavbar",
          store.state.navbar.student.navbarElements
        );
      }
    })
    .catch(() => {});
}
