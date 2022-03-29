<template>
  <div class="item-container">
    <div class="item" @click="updateDropdown">
      {{ header }}
    </div>
  </div>
</template>

<script>
export default {
  name: "TaskBarHeader",
  props: {
    header: {
      type: String,
      required: true,
    },
    modelValue: {
      type: Boolean,
      default: false,
    },
  },
  emits: ["update:modelValue"],
  setup(props, { emit }) {
    const updateDropdown = () => {
      if (props.modelValue === true) emit("update:modelValue", false);
      else emit("update:modelValue", true);
    };
    return { updateDropdown };
  },
};
</script>

<style scoped>
.item-container {
  display: grid;
  grid-template-columns: auto;
  background-color: dimgrey;
  border: 1px solid rgba(0, 0, 0, 0.8);
}
.item {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  font-size: 30px;
  font-weight: bold;
}
</style>
