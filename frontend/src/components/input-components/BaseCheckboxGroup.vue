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
        :checked="modelValue.includes(opts.label)"
        :value="opts.value"
        @update:modelValue="check(opts.label, $event)"
      />
    </component>
  </div>
</template>

<script>
import BaseCheckbox from "@/input-components/BaseCheckbox";
export default {
  name: "BaseCheckboxGroup",
  emits: ["update:value"],
  components: { BaseCheckbox },
  props: {
    vertical: {
      type: Boolean,
      default: false,
    },
    modelValue: {
      type: Array,
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
  setup(props, context) {
    const check = (optionId, checked) => {
      console.log(this.modelValue);
      let updatedValue = [...props.modelValue];
      console.log(updatedValue);
      if (checked) {
        updatedValue.push(optionId);
      } else {
        updatedValue.splice(updatedValue.indexOf(optionId), 1);
      }
      console.log(updatedValue);
      context.emit("update:value", updatedValue);
    };

    return {
      check,
    };
  },
};
</script>

<style scoped>
.base-checkbox-container {
  display: inline-grid;
  grid-template-columns: auto auto;
  padding: 10px;
}
.base-checkbox-item {
  padding: 10px;
  font-size: 24px;
}
</style>
