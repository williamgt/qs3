<template>
  <h1>
    <router-link to="/locations/campus">
      {{ this.room.campusName }} |
      {{ this.room.buildingName }}
    </router-link>
  </h1>
  <base-display label="ID" :message="this.room.id"></base-display>
  <base-display label="Room name" :message="this.room.roomName"></base-display>
  <base-display
    label="Floor"
    :message="this.room.floors"
    type="number"
  ></base-display>
  <base-display
    label="Tables"
    :message="this.room.tables"
    type="number"
  ></base-display>
  <div class="submit-button">
    <base-button type="submit" @click="editRoom">Edit</base-button>
  </div>
</template>

<script>
import { getRoom } from "@/services/locationSerivce";
import BaseButton from "@/components/input-components/BaseButton";
import BaseDisplay from "@/components/input-components/BaseDisplay";
import router from "@/router";

export default {
  name: "RoomEditView",
  components: { BaseDisplay, BaseButton },
  data() {
    return {
      room: Object,
    };
  },
  async created() {
    await getRoom(this.$route.params.id)
      .then((response) => {
        console.log(response.data);
        this.room = response.data;
      })
      .catch((err) => {
        console.log(err.response);
      });
  },
  methods: {
    editRoom() {
      router.push(this.$route.path + "/edit");
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
