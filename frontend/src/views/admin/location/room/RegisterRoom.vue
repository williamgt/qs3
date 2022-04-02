<template>
  <h1 class="header">Register campus</h1>
  <base-input label="Room name" v-model="room.roomName"></base-input>
  <base-input
    label="Room floor"
    v-model="room.floors"
    type="number"
  ></base-input>
  <base-input
    label="Number of tables"
    v-model="room.tables"
    type="number"
  ></base-input>
  <div class="submit-button">
    <br v-if="error === ''" />
    <br v-if="error === ''" />
    <p class="error">{{ this.error }}</p>
    <base-button type="submit" @click="submit">Register</base-button>
  </div>
</template>

<script>
import BaseInput from "@/input-components/BaseInput";
import BaseButton from "@/input-components/BaseButton";
import { registerCampus, registerRoom } from "@/services/locationSerivce";
export default {
  inject: ["GStore"],
  name: "RegisterRoom",
  components: { BaseInput, BaseButton },
  data() {
    return {
      room: {
        buildingId: Number,
        roomName: "",
        floors: Number,
        tables: Number,
      },
      error: "",
    };
  },
  methods: {
    validateInput() {
      if (this.room.roomName.length === 0) {
        this.error = "Name can't be empty";
        return false;
      }
      if (this.room.tables < 1) {
        this.error = "Can't have less than 1 table";
        return false;
      }
      return true;
    },
    submit() {
      if (this.validateInput()) {
        registerRoom(this.name)
          .then(() => {
            this.GStore.flashMessage = "Room registered!";
            this.$router.push("/locations/campus");
          })
          .catch((err) => {
            console.log(err.response);
            this.GStore.flashMessage = "Problems registering Room";
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
