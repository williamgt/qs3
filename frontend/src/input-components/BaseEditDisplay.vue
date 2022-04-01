<template>
  <div class="display-container">
    <span class="label-display">{{ label }}</span>
    <base-input
      @change="$emit('update:modelValue', $event.target.value)"
      :value="modelValue"
      :label="modelValue"
      :disable-label="true"
      :type="type"
    />
  </div>
</template>

<script>
import BaseInput from "@/input-components/BaseInput";
export default {
  name: "BaseEditDisplay",
  components: { BaseInput },
  props: {
    label: {
      type: String,
      required: true,
    },
    vertical: {
      type: Boolean,
      default: false,
    },
    modelValue: {
      type: String,
    },
    type: {
      type: String,
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
</style>
