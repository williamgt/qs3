<template>
  <router-link :to="'locations/campus/' + this.campus.id" class="link">
    <div class="grid-container">
      <div class="grid-item">{{ campus.id }}</div>
      <div class="grid-item">{{ campus.name }}</div>
    </div>
  </router-link>
</template>

<script>
export default {
  name: "CampusElement",
  props: {
    campus: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 50% 50%;
  background-color: #2196f3;
  padding: 10px;
}

.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding: 20px;
  font-size: 30px;
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
