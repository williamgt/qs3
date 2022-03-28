<template>
  <div class="base-checkbox-item">
    <input
      @input="(event) => $emit('update:checked', event.target.checked)"
      type="checkbox"
      :checked="checked"
      :id="fieldId"
      class="mr-2 hidden"
    />
    <label
      :for="fieldId"
      class="flex flex-row items-center font-bold text-gray-800 cursor-pointer select-none"
    >
      <i
        class="fa mr-2"
        :class="{
          'fa-check-square text-blue-600': checked,
          'fa-square text-gray-500': !checked,
        }"
      ></i>
      {{ label }}
    </label>
  </div>
</template>

<script>
export default {
  props: {
    label: String,
    fieldId: {
      type: String,
      required: true,
    },
    checked: {
      type: Boolean,
    },
  },
};
</script>

<style>
.base-checkbox-item {
  padding: 10px;
  font-size: 24px;
}
</style>
