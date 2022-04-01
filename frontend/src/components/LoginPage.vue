<template>
  <div class="login">
    <div class="title">
      <h1>QS</h1>
    </div>

    <div class="error" v-if="this.error">
      <p>{{ this.error }}</p>
    </div>

    <div class="login-form">
      <form @submit.prevent="login">
        <fieldset>
          <legend>Login</legend>
          <BaseInput v-model="userLogin.email" label="Email" type="text" />
          <br />
          <BaseInput
            v-model="userLogin.password"
            label="Password"
            type="text"
          />
        </fieldset>

        <div class="submit-button">
          <button type="submit">Log in</button>
        </div>

        <div class="general-info">
          <p>Forgotten password? PLACEHOLDER</p>
          <p>Not yet registered? Contact Orakeltjenesten</p>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import BaseInput from "../input-components/BaseInput";
import doLogin from "../api/LoginAPI";
import { getRole } from "@/services/authService";
import hasAdminAccess, { hasTAAccess, hasTeacherAccess } from "@/api/AuthAPI";

export default {
  name: "LoginPage",
  components: {
    BaseInput,
  },
  data() {
    return {
      userLogin: {
        email: "olav@123.com",
        password: "123",
      },
      error: "",
    };
  },
  methods: {
    async login() {
      if (!this.validateEmail()) {
        this.error = "The email is not valid.";
      } else {
        this.error = "";
        doLogin(this.userLogin);
      }
    },
    validateEmail() {
      return this.userLogin.email.match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
    },
  },
  // eslint-disable-next-line no-unused-vars
  beforeRouteLeave(to, from) {
    getRole(this.$store.state.personLoggedIn)
      .then((response) => {
        this.$store.dispatch("setRole", response.data);
        console.log(this.$store.state.auth.role);
        //Sets navbar for logged in
        if (hasAdminAccess(response.data)) {
          this.$store.dispatch("setNavbar", this.$store.state.navbar.admin);
          console.log(this.$store.state.navbar.current);
        } else if (hasTeacherAccess(response.data)) {
          //TODO
        } else if (hasTAAccess(response.data)) {
          //TODO
        } else {
          this.$store.dispatch(
            "setNavbar",
            this.$store.state.navbar.student.navbarElements
          );
          console.log(this.$store.state.navbar.current);
        }
      })
      .catch((err) => {
        console.log(err.response);
      });
  },
};
</script>

<style scoped>
fieldset {
  border: 0;
}
</style>
