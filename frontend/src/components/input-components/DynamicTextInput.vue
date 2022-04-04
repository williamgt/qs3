<template>
  <label v-if="label">{{ label }}</label>
  <div class="form-group" v-for="(input, index) in inputs" :key="index">
    <input type="text" class="form-control" />
    <span>
      <i @click="remove(index)" v-show="index || (!index && input.length > 1)"
        >-</i
      >
      <i @click="add(input)" v-show="index === input.length - 1">+</i>
    </span>
  </div>
</template>

<script>
export default {
  name: "DynamicTextInput",
  data() {
    return {
      inputs: [
        {
          name: "",
        },
      ],
    };
  },
  props: {
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: Array,
    },
  },
  methods: {
    add() {
      console.log("added");
      this.inputs.push({ name: "" });
    },
    remove(index) {
      this.inputs.splice(index, 1);
    },
  },
};
</script>

<style scoped></style>
