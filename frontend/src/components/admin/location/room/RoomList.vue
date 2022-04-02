<template>
  <h1>{{ campus.name }} | {{ campus.building.name }}</h1>
  <div class="table-container">
    <room-header></room-header>
    <room-element
      v-for="room in campus.building.rooms"
      :key="room.id"
      :room="room"
    ></room-element>
  </div>
  <div class="button-container">
    <button>Edit Building</button>
    <button @click="registerRoom">Add Room</button>
  </div>
</template>

<script>
import RoomHeader from "@/components/admin/location/room/RoomHeader";
import RoomElement from "@/components/admin/location/room/RoomElement";
export default {
  name: "RoomList",
  components: { RoomElement, RoomHeader },
  props: {
    campus: Object,
  },
  methods: {
    registerRoom() {
      this.$router.push(this.$route.params.id + "/register");
    },
  },
};
</script>

<style scoped>
.header {
  font-size: 24px;
}
h1 {
  text-align: center;
}
.table-container {
  margin-right: 5%;
  margin-left: 5%;
}
.button-container {
  margin-top: 10px;
  text-align: center;
}
button {
  margin-left: 10px;
  margin-right: 10px;
}
</style>
