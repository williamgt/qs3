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
</style>
