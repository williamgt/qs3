<template>
  <div class="course-container">
    <h3>{{ active ? "Active" : "Inactive" }}</h3>
    <ul class="course-list">
      <li v-for="course in courses" :key="course.code" class="element">
        <h4>{{ course.title }} - {{ course.code }}</h4>
        <button @click="activateOrDeactivate(course, active)">
          {{ active ? "Deactivate" : "Activate" }}
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "TACourses",
  props: {
    active: {
      type: Boolean,
      required: true,
    },

    courses: {
      type: Array,
      required: true,
    },
  },
  methods: {
    activateOrDeactivate(course, active) {
      this.$emit("changed-active-status", {
        course: course,
        active: active,
      });
    },
  },
};
</script>

<style scoped>
.course-list {
  list-style-type: none;
}
.element {
  list-style-position: inside;
  border: 1px solid black;
}
.element * {
  display: inline-block;
}
</style>
