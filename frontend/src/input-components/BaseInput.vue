<template>
  <label v-if="label && !disableLabel">{{ label }}</label>
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
//import UniqueID from '../features/UniqueID'
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
    disableLabel: {
      type: Boolean,
      default: false,
    },
  },
  /*  setup () {
    const uuid = UniqueID().getID()
    return {
      uuid
    }
  }*/
};
</script>

<style scoped></style>
