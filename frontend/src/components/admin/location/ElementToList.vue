<template>
  <router-link :to="this.path + this.element.id" class="link">
    <div class="grid-container">
      <div class="grid-item">{{ element.id }}</div>
      <div class="grid-item">{{ element.name }}</div>
    </div>
  </router-link>
</template>

<script>
export default {
  name: "ElementToList",
  props: {
    element: {
      type: Object,
      required: true,
    },
    path: {
      type: String,
      required: true,
    },
  },
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 50% 50%;
}

.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding: 20px;
  font-size: 20px;
  text-align: center;
}
link {
  text-decoration: none;
  display: inline-block;
  border-bottom: none;
}
a:link,
a:visited,
a:hover,
a:active {
  text-decoration: none;
  color: black;
}
</style>
