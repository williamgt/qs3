<template>
  <h1>
    <router-link to="/locations/campus">
      {{ this.room.campusName }} |
      {{ this.room.buildingName }}
    </router-link>
  </h1>
  <base-display label="ID" :message="this.room.id"></base-display>
  <base-edit-display
    label="Room name"
    v-model="this.name"
    :message="this.name"
    :holder="this.room.roomName"
  ></base-edit-display>
  <base-edit-display
    label="Floor"
    v-model="this.floors"
    :message="this.floors"
    :holder="this.room.floors"
    type="number"
  ></base-edit-display>
  <base-edit-display
    label="Tables"
    v-model="this.tables"
    :message="this.tables"
    :holder="this.room.tables"
    type="number"
  ></base-edit-display>
  <div class="submit-button">
    <br v-if="error === ''" />
    <br v-if="error === ''" />
    <p class="error">{{ this.error }}</p>
    <base-button type="submit" @click="cancel">Cancel</base-button>
    <base-button type="submit" @click="submit">Save</base-button>
  </div>
</template>

<script>
import { getRoom, updateRoom } from "@/services/locationSerivce";
import BaseButton from "@/input-components/BaseButton";
import BaseEditDisplay from "@/input-components/BaseEditDisplay";
import BaseDisplay from "@/input-components/BaseDisplay";

export default {
  inject: ["GStore"],
  name: "RoomEditView",
  components: { BaseDisplay, BaseEditDisplay, BaseButton },
  data() {
    return {
      error: "",
      name: "",
      floors: 0,
      tables: 0,
      room: Object,
    };
  },
  async created() {
    console.log(this.$route.params.id);
    await getRoom(this.$route.params.id)
      .then((response) => {
        this.room = response.data;
        this.floors = response.data.floors;
        this.tables = response.data.tables;
        this.room.roomName = response.data.roomName;
      })
      .catch((err) => {
        console.log(err.response);
        this.error = err;
      });
  },
  methods: {
    validateInput() {
      if (this.name.length === 0) {
        this.error = "Name can't be empty";
        return false;
      }
      if (this.room.roomName === this.name) {
        this.error = "Can't update to same name";
        return false;
      }
      if (this.tables < 0) {
        this.error = "Can't have less than 0 tables";
        return false;
      }
      this.room.roomName = this.name;
      this.room.tables = this.tables;
      this.room.floors = this.floors;
      return true;
    },
    cancel() {
      this.$router.push("/locations/room/" + this.room.id);
    },
    submit() {
      if (this.validateInput()) {
        updateRoom(this.room)
          .then(() => {
            this.GStore.flashMessage = "Room Edited!";
            this.$router.push("/locations/room/" + this.room.id);
          })
          .catch((err) => {
            console.log(err.response);
            this.error = err;
            this.GStore.flashMessage = "Problems Editing Room";
            this.$router.push("/locations/room/" + this.room.id);
          });
      }
    },
  },
};
</script>

<style scoped>
a:link,
a:visited,
a:hover,
a:active {
  text-decoration: none;
  color: black;
}
</style>
