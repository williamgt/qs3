<template>
  <h1 class="header">Register campus</h1>
  <base-input label="Campus name" v-model="name"></base-input>

  <div class="submit-button">
    <br v-if="error === ''" />
    <br v-if="error === ''" />
    <p class="error">{{ this.error }}</p>
    <base-button type="submit" @click="cancel">Cancel</base-button>
    <base-button type="submit" @click="submit">Register</base-button>
  </div>
</template>

<script>
import BaseInput from "@/input-components/BaseInput";
import BaseButton from "@/input-components/BaseButton";
import { registerCampus } from "@/services/locationSerivce";
export default {
  inject: ["GStore"],
  name: "RegisterCampus",
  components: { BaseInput, BaseButton },
  data() {
    return {
      name: "",
      error: "",
    };
  },
  methods: {
    validateInput() {
      if (this.name.length === 0) {
        this.error = "Name can't be empty";
        return false;
      }
      return true;
    },
    cancel() {
      this.$router.push("/locations/campus");
    },
    submit() {
      if (this.validateInput()) {
        registerCampus(this.name)
          .then(() => {
            this.GStore.flashMessage = "Campus Registered!";
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            this.$router.push("/locations/campus");
          })
          .catch((err) => {
            console.log(err.response);
            this.GStore.flashMessage = "Problems registering campus";
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            this.$router.push("/locations/campus");
          });
      }
    },
  },
};
</script>

<style scoped>
.header {
  text-align: center;
}
.error {
  color: red;
}
</style>
