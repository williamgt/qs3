<template>
  <base-input label="Email" v-model="user.email"></base-input>
  <base-input label="First name" v-model="user.firstname"></base-input>
  <base-input label="Last name" v-model="user.lastname"></base-input>
  <base-input
    label="Password"
    type="password"
    v-model="user.password"
  ></base-input>
  <base-input
    label="Retype password"
    type="password"
    v-model="rePassword"
  ></base-input>
  <div class="select-container">
    <base-select
      label="Role"
      :options="roles"
      v-model="user.role"
    ></base-select>
  </div>
  <div class="submit-button">
    <br v-if="error === ''" />
    <br v-if="error === ''" />
    <p class="error">{{ this.error }}</p>
    <base-button @click="cancel">Cancel</base-button>
    <base-button type="submit" @click="submit">Create</base-button>
  </div>
</template>

<script>
import BaseInput from "@/components/input-components/BaseInput";
import BaseButton from "@/components/input-components/BaseButton";
import BaseSelect from "@/components/input-components/BaseSelect";
import { registerUser } from "@/services/userService";
export default {
  inject: ["GStore"],
  name: "CreateUserView",
  components: { BaseSelect, BaseButton, BaseInput },
  data() {
    return {
      user: {
        firstname: "",
        lastname: "",
        password: "",
        email: "",
        role: "",
      },
      rePassword: "",
      error: "",
      roles: [
        { name: "ADMIN", id: "ADMIN" },
        { name: "TEACHER", id: "TEACHER" },
        { name: "TA", id: "TA" },
        { name: "STUDENT", id: "STUDENT" },
      ],
    };
  },
  methods: {
    validate() {
      return (
        this.validateEmail() &&
        this.validateName() &&
        this.validatePassword() &&
        this.validateRole()
      );
    },
    validateRole() {
      if (this.user.role === "") {
        this.error = "Please select a role";
        return false;
      }
      return true;
    },
    validateName() {
      if (this.user.firstname === "") {
        this.error = "Please enter a first name";
        return false;
      }
      if (this.user.lastname === "") {
        this.error = "Please enter a last name";
        return false;
      }
      return true;
    },
    validatePassword() {
      if (this.user.password !== this.rePassword) {
        this.error = "Password does not match";
        return false;
      }
      if (this.user.password === "") {
        this.error = "Password can't be blank";
        return false;
      }
      return true;
    },
    validateEmail() {
      if (
        !(
          this.user.email.match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          ) &&
          (this.user.email.includes(".no") || this.user.email.includes(".com"))
        )
      ) {
        this.error = "Wrong email format";
        return false;
      }
      return true;
    },
    async submit() {
      this.error = "";
      if (this.validate()) {
        await registerUser(this.user)
          .then(() => {
            this.GStore.flashMessage = "User registered";
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            this.$router.push("/users");
          })
          .catch((err) => {
            this.GStore.flashMessage = err.response.data;
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            console.log(err.response);
          });
      }
    },
    cancel() {
      this.$router.push("/users");
    },
  },
};
</script>

<style scoped>
.select-container {
  text-align: center;
}
.error {
  color: red;
}
</style>
