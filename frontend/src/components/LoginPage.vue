<template>
  <div class="login">
    <div class="title">
      <h1>QS</h1>
    </div>

    <div class="login-input">
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

export default {
  name: "LoginPage",
  components: {
    BaseInput,
  },
  data() {
    return {
      userLogin: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      doLogin();
    },
  },
};
</script>

<style scoped>
fieldset {
  border: 0;
}
</style>
