<template>
  <h1 class="header">Register campus</h1>
  <base-input label="Campus name" v-model="name"></base-input>

  <div class="submit-button">
    <br v-if="error === ''" />
    <br v-if="error === ''" />
    <p class="error">{{ this.error }}</p>
    <base-button type="submit">Log in</base-button>
  </div>
</template>

<script>
import BaseInput from "@/input-components/BaseInput";
import BaseButton from "@/input-components/BaseButton";
import { addCampus } from "@/services/locationSerivce";
export default {
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
    submit() {
      if (this.validateInput()) {
        addCampus(this.name);
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
