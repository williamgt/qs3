<template>
  <label v-if="label">{{ label }}</label>
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
import BaseErrorMessage from "@/input-components/BaseErrorMessage";
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
select {
  width: 100%;
  height: 40px;
  padding: 0 24px 0 10px;
  vertical-align: middle;
  background: #fff;
  background-size: 8px 10px;
  border: solid 1px rgba(0, 0, 0, 0.4);
  border-radius: 0;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  font-size: 15px;
}
select:focus {
  border-color: #39b982;
  outline: 0;
}
.error {
  color: red;
}
</style>
