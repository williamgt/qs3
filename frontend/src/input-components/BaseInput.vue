<template>
  <label v-if="label">{{ label }}</label>
  <input
    v-bind="$attrs"
    :type="type"
    :value="modelValue"
    :placeholder="label"
    @input="$emit('update:modelValue', $event.target.value)"
    class="field"
  />
  <p v-if="error" class="errorMessage">
    {{ error }}
  </p>
</template>

<script>
export default {
  name: "BaseInput",
  props: {
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: [String, Number],
      default: "",
    },
    error: {
      type: String,
      default: "",
    },
    type: {
      type: String,
      default: "text",
    },
  },
};
</script>

<style scoped></style>
