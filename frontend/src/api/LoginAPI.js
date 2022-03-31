import { getToken, login } from "@/services/authService";
import store from "../store";
import router from "@/router";
export default function doLogin(userLogin) {
  let token;
  getToken(userLogin.email, userLogin.password)
    .then((data) => {
      token = data.data;
      store.dispatch("setToken", token).then(
        login(userLogin).then((resolvedResult) => {
          if (resolvedResult.data) {
            console.log(resolvedResult.data);
            store.dispatch("setLogin", resolvedResult.data).then(() => {
              router.push({ name: "Home" });
            });
          }
        })
      );
    })
    .catch((error) => {
      console.log(error);
      if (error)
        if (error.response.status === 403) {
          alert("Wrong username or password");
        } else {
          console.log(error);
        }
    });
}
