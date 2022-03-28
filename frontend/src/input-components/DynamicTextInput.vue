<template>
  <div class="form-group" v-for="(input, index) in inputs" :key="index">
    <input type="text" class="form-control" v-model="input.name" />
    <span>
      <button
        class="bi bi-person-dash"
        @click="remove(index)"
        v-show="index || (!index && inputs.length > 1)"
      ></button>
      <i
        class="bi bi-person-plus"
        @click="add(index)"
        v-show="index === inputs.length - 1"
      ></i>
    </span>
  </div>
</template>

<script>
export default {
  name: "DynamicTextInput",
  data: function () {
    return {
      inputs: [
        {
          name: "",
        },
      ],
    };
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
