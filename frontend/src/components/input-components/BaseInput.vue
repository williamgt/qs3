<template>
  <div class="label-container">
    <label class="label" v-if="label && !disableLabel">{{ label }}</label>
  </div>
  <div class="input-container">
    <input
      v-bind="$attrs"
      :type="type"
      :value="modelValue"
      :placeholder="holder"
      @input="$emit('update:modelValue', $event.target.value)"
      class="field"
    />
  </div>
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
    holder: {
      type: String,
      default: "",
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

<style scoped>
@media (max-width: 960px) {
  input[type="text"],
  input[type="password"] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }
  .label {
    font-size: 24px;
  }
}
@media (min-width: 960px) {
  input[type="text"],
  input[type="password"] {
    width: 60%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 24px;
  }
  .input-container {
    text-align: center;
  }
  .label-container {
    text-align: center;
  }
}
.label {
  font-size: 24px;
}
</style>
<style scoped>
.errorMessage {
  color: red;
}
</style>
