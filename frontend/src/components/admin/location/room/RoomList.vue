<template>
  <h1>
    <router-link to="/locations/campus"> {{ campus.name }} </router-link> |
    <router-link :to="'/locations/campus/' + this.campus.id">{{
      campus.building.name
    }}</router-link>
  </h1>
  <div class="table-container">
    <room-header></room-header>
    <room-element
      v-for="room in campus.building.rooms"
      :key="room.id"
      :room="room"
    ></room-element>
  </div>
  <div class="button-container">
    <button @click="editBuilding">Edit Building</button>
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
    editBuilding() {
      this.$router.push(this.$route.params.id + "/edit");
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
a:link,
a:visited,
a:hover,
a:active {
  text-decoration: none;
  color: black;
}
</style>
