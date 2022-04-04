<template>
  <div class="base-checkbox-container">
    <check-box
      class="base-checkbox-item"
      v-for="option in options"
      :checked="value.includes(option.id)"
      @update:checked="check(option.id, $event)"
      :fieldId="option.name"
      :label="option.name"
      :key="option"
    />
  </div>
</template>

<script>
import Checkbox from "./checkbox2FML.vue";

export default {
  emits: ["update:value"],
  props: {
    value: {
      type: Array,
      required: true,
    },
    options: {
      type: Array,
      required: true,
      validator: (value) => {
        const hasNameKey = value.every((option) =>
          Object.keys(option).includes("name")
        );
        const hasIdKey = value.every((option) =>
          Object.keys(option).includes("id")
        );
        return hasNameKey && hasIdKey;
      },
    },
  },
  setup(props, context) {
    const check = (optionId, checked) => {
      let updatedValue = [...props.value];
      if (checked) {
        updatedValue.push(optionId);
      } else {
        updatedValue.splice(updatedValue.indexOf(optionId), 1);
      }
      context.emit("update:value", updatedValue);
    };

    return {
      check,
    };
  },
  components: {
    "check-box": Checkbox,
  },
};
</script>
<style>
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
