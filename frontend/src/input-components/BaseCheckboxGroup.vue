<template>
  <div class="base-checkbox-container">
    <component
      v-for="(opts, i) in options"
      :key="i"
      :is="vertical ? 'div' : 'span'"
      class="base-checkbox-item"
    >
      <BaseCheckbox
        :name="name"
        :label="opts.label"
        :modelValue="modelValue"
        :value="opts.value"
        @update:checked="check(opts.value, $event)"
      />
    </component>
  </div>
</template>

<script>
import BaseCheckbox from "@/input-components/BaseCheckbox";
export default {
  name: "BaseCheckboxGroup",
  setup(props, context) {
    const check = (optionId, checked) => {
      // copy the value Array to avoid mutating props
      let updatedValue = [...props.value];
      // remove name if checked, else add name
      if (checked) {
        updatedValue.push(optionId);
      } else {
        updatedValue.splice(updatedValue.indexOf(optionId), 1);
      }
      // emit the updated value
      context.emit("update:value", updatedValue);
    };
    return {
      check,
    };
  },
  components: { BaseCheckbox },
  props: {
    vertical: {
      type: Boolean,
      default: false,
    },
    modelValue: {
      type: [],
      required: true,
    },
    options: {
      type: Array,
      required: true,
    },
    name: {
      type: String,
      required: true,
    },
  },
  methods: {},
};
</script>

<style scoped>
.base-checkbox-container {
  display: inline-grid;
  grid-template-columns: auto auto auto;
  padding: 10px;
}
.base-checkbox-item {
  padding: 10px;
  font-size: 24px;
}
</style>
