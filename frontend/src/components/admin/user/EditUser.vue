<template>
  <base-display label="ID" :message="user.id"></base-display>
  <base-edit-display
    label="First name"
    v-model="user.firstname"
    :holder="user.firstname"
  ></base-edit-display>
  <base-edit-display
    :holder="user.lastname"
    label="Last name"
    v-model="user.lastname"
  ></base-edit-display>
  <base-display label="Email" :message="user.email"></base-display>
  <base-edit-display
    label="Password"
    v-model="user.password"
    type="password"
  ></base-edit-display>
  <base-edit-display
    label="Password Again"
    v-model="rePassword"
    type="password"
  ></base-edit-display>
  <div class="button-container">
    <br v-if="error === ''" />
    <br v-if="error === ''" />
    <p class="error">{{ error }}</p>
    <button @click="cancel">Cancel</button>
    <button @click="submit">Confirm</button>
  </div>
</template>

<script>
import { getUser, updateUser } from "@/services/userService";
import BaseEditDisplay from "@/components/input-components/BaseEditDisplay";
import BaseDisplay from "@/components/input-components/BaseDisplay";

export default {
  name: "EditUser",
  components: { BaseDisplay, BaseEditDisplay },
  data() {
    return {
      user: undefined,
      error: "",
      rePassword: "",
    };
  },
  async created() {
    console.log(this.$route.params);
    this.user = getUser(this.$route.params.id).then((response) => {
      console.log(response.data);
      this.user = response.data;
      this.rePassword = response.data.password;
    });
  },
  methods: {
    validateForm() {
      return this.validatePassword() && this.validateNames();
    },
    submit() {
      if (this.validateForm()) {
        updateUser(this.user)
          .then((response) => {
            console.log(response.data);
            this.$router.push({ name: "User", params: this.user.id });
          })
          .catch((err) => {
            console.log(err);
          });
      }
      console.log("Form not valid");
    },
    validatePassword() {
      if (this.user.password.length < 7) {
        this.error = "Password must be longer than 7 chars";
        return false;
      }
      if (this.user.password.length > 25) {
        this.error = "Password must be shorter than 25 chars";
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
      return true;
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
