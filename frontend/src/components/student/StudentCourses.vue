<template>
  <div class="subtitle">
    <h3>{{ activeOrInactive }}</h3>
  </div>
  <div class="courses">
    <ul class="list">
      <li v-for="course in courses" :key="course.code" class="element">
        <button @click="courseClicked(course)">
          {{ course.title }} - {{ course.code }}
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "StudentCourses",
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
    activeOrInactive: {
      type: String,
      required: true,
    },
    courses: {
      type: Object,
      required: true,
    },
  },
  methods: {
    //Emits which course was clicked to the parent component
    courseClicked(course) {
      this.$emit("course-clicked", course);
    },
  },
};
</script>

<style scoped></style>
