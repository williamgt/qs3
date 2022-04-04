<template>
  <input
    type="radio"
    :checked="modelValue === value"
    :value="value"
    v-bind="$attrs"
    @change="$emit('update:modelValue', value)"
  />
  <label v-if="label">{{ label }}</label>
  <base-error-message v-if="error" :id="`${uuid}-error`">
    {{ error }}
  </base-error-message>
</template>

<script>
import UniqueID from "@/features/UniqueID";
import SetupFormComponent from "@/features/SetupFormComponent";
import BaseErrorMessage from "@/components/input-components/BaseErrorMessage";
export default {
  name: "BaseRadio",
  components: { BaseErrorMessage },
  props: {
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: [String, Number],
    },
    value: {
      type: [String, Number],
    },
    error: {
      type: String,
      default: "",
    },
  },
  setup(props, context) {
    const uuid = UniqueID().getID();
    const { updateValue } = SetupFormComponent(props, context);

    return {
      updateValue,
      uuid,
    };
  },
};
</script>

<style scoped>
input[type="radio"] {
  transform: scale(1.5);
  font-size: 20px;
}
</style>
