<template>
  <base-display label="ID" :message="user.id"></base-display>
  <base-display label="First name" :message="user.firstname"></base-display>
  <base-display label="Last name" :message="user.lastname"></base-display>
  <base-display label="Email" :message="user.email"></base-display>
  <base-edit-display
    label="Password"
    v-model="user.password"
    type="password"
  ></base-edit-display>
  <base-edit-display
    label="Password again"
    v-model="rePassword"
    type="password"
  ></base-edit-display>
  <div class="button-container">
    <br v-if="error === ''" />
    <br v-if="error === ''" />
    <p class="error">{{ error }}</p>
    <button @click="submit">Save</button>
  </div>
</template>

<script>
import { updateUser } from "@/services/userService";
import BaseEditDisplay from "@/components/input-components/BaseEditDisplay";
import BaseDisplay from "@/components/input-components/BaseDisplay";

export default {
  name: "EditUserProt",
  components: { BaseDisplay, BaseEditDisplay },
  data() {
    return {
      user: undefined,
      error: "",
      rePassword: "",
    };
  },
  created() {
    this.user = this.$store.state.personLoggedIn;
    this.rePassword = this.user.password;
  },
  methods: {
    validateForm() {
      return this.validatePassword() && this.validateNames();
    },
    submit() {
      if (this.validateForm()) {
        updateUser(this.user)
          .then(() => {
            this.$router.push({ name: "User", params: this.user.id });
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    validatePassword() {
      if (this.user.password === undefined) {
        this.error = "Please insert a new password";
        return false;
      }
      if (this.user.rePassword === undefined) {
        this.error = "Please re enter a password";
        return false;
      }
      if (this.user.password.length < 7) {
        this.error = "Password must be longer than 7 chars";
        return false;
      }
      if (this.user.password !== this.rePassword) {
        this.error = "Passwords doesn't match";
        return false;
      }
      return true;
    },
    validateNames() {
      if (this.user.lastName > 20) {
        this.error = "LastName must be shorter than 20 chars";
        return false;
      }
      if (this.user.firstName > 30) {
        this.error = "LastName must be shorter than 30 chars";
        return false;
      }
    },
    cancel() {
      this.$router.push({ name: "User", params: this.$route.params.id });
    },
  },
};
</script>
<style scoped>
.button-container {
  text-align: center;
}
.error {
  color: red;
}
</style>
