<template>
  <div class="container">
    <div class="item-container">
      <div class="item">{{ task }}</div>
      <div class="item">
        <input type="checkbox" />
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
      type: String,
      required: true,
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
}
.item div {
  text-align: center;
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
