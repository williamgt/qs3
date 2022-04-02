<template>
  <div class="display-container">
    <span class="label-display">{{ label }}</span>
    <span class="message-display">{{ message }}</span>
  </div>
</template>

<script>
export default {
  name: "BaseDisplay",
  props: {
    label: {
      type: String,
      required: true,
    },
    vertical: {
      type: Boolean,
      default: false,
    },
    message: {
      type: String,
      required: true,
    },
  },
};
</script>

<style scoped>
.message-display {
  padding: 20px;
  font-size: 20px;
  text-align: center;
  border-bottom-color: black;
  border-bottom: solid;
}
.display-container {
  font-size: 24px;
  font-weight: bold;
  display: grid;
  grid-template-columns: 50% 50%;
  padding: 10px;
}
.label-display {
  padding: 20px;
  font-size: 24px;
  text-align: left;
  border-bottom-color: black;
  border-bottom: solid;
}
</style>
