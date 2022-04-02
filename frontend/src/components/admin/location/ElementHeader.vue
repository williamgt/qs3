<template>
  <div class="grid-container">
    <div class="grid-item">{{ element.st }}</div>
    <div class="grid-item">{{ element.nd }}</div>
  </div>
</template>

<script>
export default {
  name: "ElementHeader",
  props: {
    element: Object,
  },
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 50% 50%;
  background-color: grey;
  padding: 10px;
}

.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding: 20px;
  font-size: 30px;
  text-align: center;
}
</style>
