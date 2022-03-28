<template>
  <label v-if="label">{{ label }}</label>
  <div class="example">
    <Multiselect v-bind="modelValue" :options="options"></Multiselect>
  </div>
</template>

<script>
import Multiselect from "@vueform/multiselect";
export default {
  components: {
    Multiselect,
  },
  name: "BaseMultiselect",
  props: {
    options: {
      type: [],
      required: true,
    },
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped></style>
