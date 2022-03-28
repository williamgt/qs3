<template>
  <div class="course-container">
    <h3>{{ titile }}</h3>
    <ul>
      <li v-for="course in courses" :key="course.code" class="element">
        <h4>{{ course.title }} - {{ course.code }}</h4>
        <button @click="activateOrDeactivate(course, active)">
          Activate/Deactivate
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "TACourses",
  data() {
    return {
      active: false,
    };
  },
  props: {
    title: {
      type: String,
      required: true,
    },
    courses: {
      type: Array,
      required: true,
    },
  },
  methods: {
    activateOrDeactivate(course, active) {
      if (this.active === false) {
        this.active = true;
      }
      if (this.active === true) {
        this.active = false;
      }
      this.$emit("changed-active-status", { course: course, active: active });
    },
  },
};
</script>

<style scoped></style>
