<template>
  <div>
    <component
      :is="vertical ? 'div' : 'span'"
      :class="{
        horizontal: !vertical,
      }"
    >
      <div class="display-container">
        <h3 class="label-display">{{ label }}</h3>
        <h3 class="message-display">{{ message }}</h3>
      </div>
    </component>
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
  float: right;
  text-align: left;
}
.display-container {
  clear: both;
}
.label-display {
  float: left;
}
</style>
