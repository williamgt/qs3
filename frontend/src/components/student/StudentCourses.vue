<template>
  <div class="subtitle">
    <h3>{{ active ? "Active" : "Inactive" }}</h3>
  </div>
  <div class="courses">
    <ul class="list">
      <li v-for="course in courses" :key="course.code" class="element">
        <div class="course-info-container">
          <h4 @click="courseClicked(course, active)">
            {{ course.title }} - {{ course.code }}
          </h4>
          <button @click="courseTasksClicked(course, active)">Tasks</button>
          <button @click="courseQueueClicked(course, active)">Queue</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "StudentCourses",
  emits: ["course-clicked", "course-tasks-clicked", "course-queue-clicked"],
  /*data() {
    return {
      courses: [
        {
          title: "Fulllstack",
          code: "IDATT2105",
        },
      ],
    };
  },*/
  props: {
    active: {
      type: Boolean,
      required: true,
    },
    courses: {
      type: Object,
      required: true,
    },
  },
  methods: {
    //Emits which course was clicked to the parent component
    courseClicked(course, active) {
      this.$emit("course-clicked", { course: course, active: active });
    },
    courseTasksClicked(course, active) {
      this.$emit("course-tasks-clicked", {
        course: course,
        active: active,
      });
    },
    courseQueueClicked(course, active) {
      this.$emit("course-queue-clicked", { course: course, active: active });
    },
  },
};
</script>

<style scoped>
.list {
  list-style-type: none;
}
.element {
  list-style-position: inside;
  border: 1px solid black;
}
</style>
