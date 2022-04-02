<template>
  <h1 class="header">Register Building</h1>
  <base-input label="Building name" v-model="building.name"></base-input>
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
import { registerBuilding } from "@/services/locationSerivce";
export default {
  inject: ["GStore"],
  name: "RegisterBuilding",
  components: { BaseInput, BaseButton },
  data() {
    return {
      building: {
        id: Number,
        name: "",
      },
      error: "",
    };
  },
  methods: {
    validateInput() {
      if (this.building.name.length === 0) {
        this.error = "Name can't be empty";
        return false;
      }
      return true;
    },
    cancel() {
      this.$router.push("/locations/campus/" + this.$route.params.id);
    },
    submit() {
      if (this.validateInput()) {
        this.building.id = this.$route.params.id;
        registerBuilding(this.building)
          .then(() => {
            this.GStore.flashMessage = "Building registered!";
            this.$router.push("/locations/campus/" + this.building.id);
          })
          .catch((err) => {
            console.log(err.response);
            this.GStore.flashMessage = "Problems registering Building";
            this.$router.push("/locations/campus/" + this.building.id);
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
