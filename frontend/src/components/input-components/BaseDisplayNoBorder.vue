<template>
  <div class="display-container">
    <span class="label-display">{{ label }}</span>
    <span class="message-display">{{ message }}</span>
  </div>
</template>

<script>
export default {
  name: "BaseDisplayNoBorder",
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
  font-size: 16px;
  text-align: left;
  border-bottom-color: black;
  border-bottom: solid;
  width: 80%;
  overflow: hidden;
}
.display-container {
  clear: both;
  font-size: 24px;
  display: grid;
  grid-template-columns: auto auto;
  padding: 10px;
  overflow: hidden;
}
.label-display {
  padding: 20px;
  font-size: 24px;
  text-align: left;
  font-weight: bold;
  border-bottom-color: black;
  border-bottom: solid;
  overflow: hidden;
}
</style>
