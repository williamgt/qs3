<template>
  <router-link :to="'/locations/room/' + this.room.id" class="link">
    <div class="grid-container">
      <div class="grid-item">{{ room.id }}</div>
      <div class="grid-item">{{ room.roomName }}</div>
      <div class="grid-item">{{ room.tables }}</div>
      <div class="grid-item">{{ room.floors }}</div>
    </div>
  </router-link>
</template>

<script>
export default {
  name: "RoomElement",
  props: {
    room: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 25% 25% 25% 25%;
}

.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding: 20px;
  font-size: 20px;
  text-align: center;
}
link {
  text-decoration: none;
  display: inline-block;
  border-bottom: none;
}
a:link,
a:visited,
a:hover,
a:active {
  text-decoration: none;
  color: black;
}
</style>
