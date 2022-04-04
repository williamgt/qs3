<template>
  <div class="label-container">
    <label v-if="label">{{ label }}</label>
  </div>
  <select
    class="field"
    :value="modelValue"
    :disabled="disabled"
    v-bind="{
      ...$attrs,
      onChange: ($event) => {
        $emit('update:modelValue', $event.target.value);
      },
    }"
  >
    <option
      v-for="option in options"
      :value="option.id"
      :key="option.id"
      :selected="option === modelValue"
    >
      {{ option.name }}
    </option>
  </select>
  <BaseErrorMessage v-if="error" :id="`${label}-error`" class="error">
    {{ error }}
  </BaseErrorMessage>
</template>

<script>
import BaseErrorMessage from "@/components/input-components/BaseErrorMessage";
export default {
  name: "BaseSelect",
  components: { BaseErrorMessage },
  props: {
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: Object,
      default: undefined,
    },
    options: {
      type: Array,
      required: true,
    },
    disabled: {
      type: Boolean,
      default: false,
    },
    error: {
      type: String,
      default: "",
    },
  },
};
</script>

<style scoped>
@media (max-width: 960px) {
  select {
    width: 60%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }
}
@media (min-width: 960px) {
  select {
    width: 60%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 24px;
  }
}
label {
  font-size: 24px;
}
.label-container {
  text-align: center;
}
.error {
  color: red;
}
</style>
