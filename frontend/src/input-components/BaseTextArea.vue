<template>
  <div>
    <label v-if="label">{{ label }}</label>
    <textarea
      v-bind="$attrs"
      :value="modelValue"
      :placeholder="label"
      @input="$emit('update:modelValue', $event.target.value)"
      class="field"
    />
    <base-error-message v-if="error" :id="`${uuid}-error`" class="errorMessage">
      {{ error }}
    </base-error-message>
  </div>
</template>

<script>
import SetupFormComponent from "@/features/SetupFormComponent";
import UniqueID from "@/features/UniqueID";
import BaseErrorMessage from "@/input-components/BaseErrorMessage";
export default {
  name: "BaseTextArea",
  components: { BaseErrorMessage },
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
    rows: {
      type: String,
      default: "7",
    },
    cols: {
      type: String,
      default: "40",
    },
  },
  setup(props, context) {
    const { updateValue } = SetupFormComponent(props, context);
    const uuid = UniqueID().getID();

    return {
      updateValue,
      uuid,
    };
  },
};
</script>

<style scoped>
textarea {
  border: solid 1px #000;
  min-height: 200px;
  margin-left: 10px;
  margin-right: 10px;
  width: 80%;
}
.errorMessage {
  color: red;
}
</style>
