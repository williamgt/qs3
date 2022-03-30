import { getToken, login } from "@/services/authService";

export default function doLogin(userLogin) {
  let token;
  getToken(userLogin.email, userLogin.password).then((data) => {
    token = data.data;
    this.$store.dispatch("setToken", token);
    login(userLogin)
      .then((resolvedResult) => {
        if (resolvedResult.data) {
          console.log(resolvedResult.data);
          this.$store.dispatch("setLogin", resolvedResult.data);
          this.$router.push({ name: "Home" });
        }
        this.loginStatus = resolvedResult.data;
      })
      .catch((error) => {
        if (error)
          if (error.response.status === 403) {
            alert("Wrong username or password");
            this.loginStatus = false;
          } else {
            console.log(error);
          }
      });
  });
}
