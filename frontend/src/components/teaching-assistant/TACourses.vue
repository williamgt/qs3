<template>
  <div class="course-container">
    <h3>{{ active ? "Active" : "Inactive" }}</h3>
    <ul class="course-list">
      <li v-for="course in courses" :key="course.code" class="element">
        <div class="course-item">
          <h4>{{ course.title }} - {{ course.code }}</h4>
        </div>
        <div class="course-item">
          <div class="course-item-center">
            <button @click="activateOrDeactivate(course, active)">
              {{ active ? "Deactivate" : "Activate" }}
            </button>
          </div>
        </div>
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
button {
  align-items: center;
  justify-content: space-between;
  height: 52px;
  text-align: center;
  font-weight: 600;
  transition: all 0.2s linear;
  background-color: dodgerblue;
  padding: 20px;
  max-width: 100%;
}
.course-container {
  margin-left: 5%;
  margin-right: 5%;
}
.course-list {
  list-style-type: none;
  padding-left: 0;
}
.element {
  display: grid;
  list-style-position: inside;
  border: 1px solid black;
  grid-template-columns: 70% 30%;
}
.element * {
}
.course-item {
  text-align: left;
}
.course-item-center {
  text-align: center;
}
</style>
