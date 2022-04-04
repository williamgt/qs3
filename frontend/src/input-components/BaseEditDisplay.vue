<template>
  <div class="display-container">
    <span class="label-display">{{ label }}</span>
    <div>
      <base-input
        :holder="holder"
        v-model="modelValueProp"
        @change="$emit('update:modelValue', $event.target.value)"
        :value="modelValueProp"
        :disable-label="true"
        :type="type"
      />
    </div>
  </div>
</template>

<script>
import BaseInput from "@/input-components/BaseInput";
export default {
  name: "BaseEditDisplay",
  components: { BaseInput },
  data() {
    return {
      modelValueProp: undefined,
    };
  },
  props: {
    label: {
      type: String,
      required: true,
    },
    vertical: {
      type: Boolean,
      default: false,
    },
    modelValue: {
      type: String,
    },
    type: {
      type: String,
    },
    holder: {
      type: String,
      default: "",
    },
  },
  created() {
    this.modelValueProp = this.modelValue;
  },
};
</script>

<style scoped>
.message-display {
  padding: 20px;
  font-size: 20px;
  text-align: left;
  border-bottom-color: black;
  border-bottom: solid;
  overflow: hidden;
}
.display-container {
  font-size: 24px;
  font-weight: bold;
  display: grid;
  grid-template-columns: 50% 50%;
  padding: 10px;
  overflow: hidden;
}
.label-display {
  padding: 20px;
  font-size: 24px;
  text-align: left;
  border-bottom-color: black;
  border-bottom: solid;
  overflow: hidden;
}
</style>
