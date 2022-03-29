<template>
  <div class="container" v-if="dropdown">
    <div class="item-container">
      <div class="item">{{ task.name }}</div>
      <div class="item">
        <input
          v-if="enabled"
          type="checkbox"
          :checked="task.done"
          @change="(event) => $emit('update:task.done', event.target.checked)"
        />
        <div v-if="!enabled" v-text="task.done ? 'Done' : 'Not done'"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TaskBar",
  components: {},
  props: {
    task: {
      type: Object,
      required: true,
    },
    enabled: {
      type: Boolean,
      default: true,
    },
    dropdown: {
      type: Boolean,
      default: true,
    },
  },
};
</script>

<style scoped>
.container {
}
.item-container {
  display: grid;
  grid-template-columns: auto auto;
  background-color: #2196f3;
  border: 1px solid rgba(0, 0, 0, 0.8);
}
.item {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  font-size: 30px;
  text-align: left;
}
.item div {
  text-align: left;
  align-self: center;
}
input[type="checkbox"] {
  -ms-transform: scale(3); /* IE */
  -moz-transform: scale(3); /* FF */
  -webkit-transform: scale(3); /* Safari and Chrome */
  -o-transform: scale(3); /* Opera */
  transform: scale(3);
  padding: 10px;
  float: right;
}
</style>
