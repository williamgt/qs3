<template>
  <div class="grid-container">
    <div class="grid-item">ID</div>
    <div class="grid-item">Email</div>
    <div class="grid-item">First name</div>
    <div class="grid-item">Last name</div>
  </div>
</template>

<script>
export default {
  name: "UserElementHeader",
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 25% 25% 25% 25%;
  background-color: grey;
  width: 100%;
}
.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding-bottom: 20px;
  padding-top: 20px;
  font-size: 24px;
  text-align: center;
  overflow: hidden;
  max-width: 100%;
  width: 100%;
}
</style>
