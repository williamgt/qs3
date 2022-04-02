<template>
  <div class="grid-container">
    <div class="grid-item">ID</div>
    <div class="grid-item">Name</div>
    <div class="grid-item">Tables</div>
    <div class="grid-item">Floor</div>
  </div>
</template>

<script>
export default {
  name: "RoomHeader",
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 25% 25% 25% 25%;
  background-color: grey;
}

.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding: 20px;
  font-size: 30px;
  text-align: center;
}
</style>
