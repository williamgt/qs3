<template>
  <div class="grid-container">
    <div class="grid-item">Email: {{ user.email }}</div>
    <div class="grid-item">ID: {{ user.id }}</div>
  </div>
</template>

<script>
export default {
  name: "UserElement",
  props: {
    user: {
      type: Object,
      required: true,
      email: {
        type: String,
        required: true,
      },
      id: {
        type: Number,
        required: true,
      },
    },
  },
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: auto auto;
  padding: 10px;
  width: 50%;
}
.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding: 20px;
  font-size: 24px;
  text-align: center;
  overflow: hidden;
  max-width: 100%;
}
</style>
