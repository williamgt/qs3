<template>
  <div class="queue-page-container">
    <div class="title">
      <h2>Queue</h2>
    </div>
    <div class="queue-container">
      <ul>
        <li v-for="course in courses" :key="course.hashId"></li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "InsideQueue",
  props: {
    courses: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped></style>
