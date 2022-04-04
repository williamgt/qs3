<template>
  <div class="login">
    <div class="title">
      <h1 class="header">Login</h1>
    </div>

    <div class="login-form">
      <form @submit.prevent="login">
        <fieldset>
          <BaseInput v-model="userLogin.email" label="Email" type="text" />
          <br />
          <BaseInput
            v-model="userLogin.password"
            label="Password"
            type="password"
          />
        </fieldset>

        <div class="submit-button">
          <br v-if="error === ''" />
          <br v-if="error === ''" />
          <p class="error">{{ this.error }}</p>
          <base-button id="submit-button" type="submit">Log in</base-button>
        </div>

        <div class="general-info">
          <!--          <p>Forgotten password?</p>-->
          <p>Not yet registered?</p>
          <p>Contact Orakeltjenesten</p>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import BaseInput from "./input-components/BaseInput";
import doLogin from "../api/LoginAPI";
import BaseButton from "@/components/input-components/BaseButton";

export default {
  name: "LoginPage",
  components: {
    BaseButton,
    BaseInput,
  },
  data() {
    return {
      userLogin: {
        email: "",
        password: "",
      },
      error: "",
    };
  },
  methods: {
    async login() {
      if (!this.validateEmail()) {
        this.error = "The email is not valid.";
      } else {
        this.error = await doLogin(this.userLogin);
      }
    },
    validateEmail() {
      return this.userLogin.email.match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
    },
  },
  // eslint-disable-next-line no-unused-vars
};
</script>

<style scoped>
fieldset {
  border: 0;
}
.header {
  font-size: 32px;
  text-align: center;
}

@media (max-width: 960px) {
  .header {
    font-size: 32px;
    text-align: center;
  }
}
.general-info {
  font-size: 20px;
  text-align: center;
}
.error {
  color: red;
}
</style>
