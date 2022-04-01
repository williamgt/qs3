<template>
  <div class="login">
    <div class="title">
      <h1 class="header">Login</h1>
    </div>

    <div class="error" v-if="this.error">
      <p>{{ this.error }}</p>
    </div>

    <div class="login-form">
      <form @submit.prevent="login">
        <fieldset>
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
          <!--          <p>Forgotten password?</p>-->
          <p>Not yet registered?</p>
          <p>Contact Orakeltjenesten</p>
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
        email: "lars@123.com",
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
button {
  display: inline-flex;
  align-items: center;
  justify-content: space-between;
  height: 52px;
  padding: 0 40px;
  background: transparent;
  border: none;
  border-radius: 6px;
  text-align: center;
  font-weight: 600;
  white-space: nowrap;
  transition: all 0.2s linear;
  background-color: dodgerblue;
}
.submit-button {
  align-self: center;
  text-align: center;
}
button:hover {
  -webkit-transform: scale(1.02);
  transform: scale(1.02);
  box-shadow: 0 7px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
button:active {
  -webkit-transform: scale(1);
  transform: scale(1);
  box-shadow: none;
}
button:focus {
  outline: 0;
}
button:disabled {
  -webkit-transform: scale(1);
  transform: scale(1);
  box-shadow: none;
}
</style>
