<template>
  <div class="container">
    <div class="item-container">
      <div class="item">{{ student.lastName }}</div>
      <div class="item">{{ student.firstName }}</div>
      <div class="item">{{ student.email }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "StudentBar",
  components: {},
  props: {
    student: {
      type: Object,
      required: true,
    },
    enabled: {
      type: Boolean,
      default: true,
    },
    dropdown: {
      type: Boolean,
      default: true,
    },
    modelValue: {
      type: Boolean,
    },
  },
};
</script>

<style scoped>
.container {
}
.item-container {
  display: grid;
  grid-template-columns: auto auto auto;
  background-color: #2196f3;
  border: 1px solid rgba(0, 0, 0, 0.8);
  overflow: scroll;
}
.item {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  font-size: 16px;
  text-align: left;
}
.item div {
  text-align: left;
  align-self: center;
}
</style>
