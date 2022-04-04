<template>
  <input
    type="checkbox"
    :checked="modelValue"
    @change="$emit('update:modelValue', $event.target.checked)"
    class="field"
  />
  <label v-if="label">{{ label }}</label>

  <base-error-message v-if="error" :id="`${uuid}-error`">
    {{ error }}
  </base-error-message>
</template>

<script>
import BaseErrorMessage from "@/input-components/BaseErrorMessage";
import SetupFormComponent from "@/features/SetupFormComponent";
import UniqueID from "@/features/UniqueID";
export default {
  name: "BaseCheckbox",
  components: { BaseErrorMessage },
  props: {
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: Boolean,
      default: false,
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
[type="checkbox"],
[type="radio"] {
  box-sizing: border-box;
  padding: 0;
  margin-right: 0.5rem;
  transform: scale(1.5);
}
</style>
