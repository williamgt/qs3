<template>
  <h1 class="header">Register Building</h1>
  <base-input
    label="Building name"
    v-model="building.buildingName"
  ></base-input>
  <div class="submit-button">
    <br v-if="error === ''" />
    <br v-if="error === ''" />
    <p class="error">{{ this.error }}</p>
    <base-button type="submit" @click="cancel">Cancel</base-button>
    <base-button type="submit" @click="submit">Register</base-button>
  </div>
</template>

<script>
import BaseInput from "@/components/input-components/BaseInput";
import BaseButton from "@/components/input-components/BaseButton";
import { registerBuilding } from "@/services/locationSerivce";
export default {
  inject: ["GStore"],
  name: "RegisterBuilding",
  components: { BaseInput, BaseButton },
  data() {
    return {
      building: {
        buildingId: Number,
        buildingName: "",
      },
      error: "",
    };
  },
  methods: {
    validateInput() {
      if (this.building.buildingName.length === 0) {
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
        this.building.buildingId = this.$route.params.id;
        registerBuilding(this.building)
          .then(() => {
            this.GStore.flashMessage = "Building registered!";
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            this.$router.push("/locations/campus/" + this.building.buildingId);
          })
          .catch((err) => {
            console.log(err.response);
            this.GStore.flashMessage = "Problems registering Building";
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            this.$router.push("/locations/campus/" + this.building.buildingId);
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
