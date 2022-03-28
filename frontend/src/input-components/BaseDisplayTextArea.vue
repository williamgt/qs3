<template>
  <div class="display-container">
    <span class="label-display">{{ label }}</span>
    <span class="message-display">
      <div>
        <textarea readonly v-text="message"></textarea>
      </div>
    </span>
  </div>
</template>

<script>
export default {
  name: "BaseDisplayTextArea",
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
  text-align: left;
  border-bottom-color: black;
  border-bottom: solid;
}
.display-container {
  clear: both;
  font-size: 24px;
  font-weight: bold;
  display: grid;
  grid-template-columns: auto auto;
  padding: 10px;
}
.label-display {
  padding: 20px;
  font-size: 24px;
  text-align: left;
  border-bottom-color: black;
  border-bottom: solid;
}
textarea {
  resize: vertical;
  font-size: 16px;
  width: 100%;
  height: 100px;
}
</style>
