<template>
  <div class="title">
    <h2>Courses</h2>
  </div>
  <div class="courses">
    <StudentCourses :courses="activeCourses" :active="true" v-on="handlers" />
    <StudentCourses
      :courses="inactiveCourses"
      :active="false"
      v-on="handlers"
    />
  </div>
</template>

<script>
import StudentCourses from "../student/StudentCourses";

export default {
  components: {
    StudentCourses,
  },
  name: "StudentCoursesView",
  data() {
    return {
      activeCourses: [
        {
          title: "Fulllstack",
          code: "IDATT2105",
        },
      ],
      inactiveCourses: [
        {
          title: "kcatslluF",
          code: "5012TTADI",
        },
      ],
      handlers: {
        "course-clicked": this.courseClicked,
        "course-tasks-clicked": this.courseTasksClicked,
        "course-queue-clicked": this.courseQueueClicked,
      },
    };
  },
  methods: {
    courseClicked(payload) {
      console.log("Course clicked: " + payload.course.code);
    },
    courseTasksClicked(payload) {
      console.log("Course tasks clicked: " + payload.course.code);
    },
    courseQueueClicked(payload) {
      if (payload.active) {
        console.log(
          "Clicked on active course, you can queue up " + payload.course.code
        );
      }
      if (!payload.active) {
        console.log(
          "Clicked on inactive course, you can't queue up " +
            payload.course.code
        );
      }
    },
  },
};
</script>

<style scoped></style>
