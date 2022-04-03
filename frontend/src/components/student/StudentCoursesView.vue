<template>
  <div class="title">
    <h2>Courses</h2>
  </div>
  <Suspense>
    <template #default>
      <div class="courses">
        <StudentCourses :active="true" v-on="handlers" />
        <StudentCourses :active="false" v-on="handlers" />
      </div>
    </template>
    <template #fallback>Loading...</template>
  </Suspense>
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
      handlers: {
        "course-clicked": this.courseClicked,
        "course-tasks-clicked": this.courseTasksClicked,
        "course-queue-clicked": this.courseQueueClicked,
      },
    };
  },
  methods: {
    courseClicked(payload) {
      console.log("Course clicked: " + payload.course.courseCode);
      this.$router.push({ path: `/courses/${payload.course.hashId}` });
    },
    courseTasksClicked(payload) {
      console.log("Course tasks clicked: " + payload.course.courseCode);
      this.$router.push({ path: `/courses/${payload.course.hashId}` });
    },
    courseQueueClicked(payload) {
      if (payload.active) {
        console.log(
          "Clicked on active course, you can queue up " +
            payload.course.courseCode
        );
        if (this.$store.state.inQueue) {
          console.log("Redirect directly to queue view.");
          this.$router.push({
            path: `/courses/${payload.course.hashId}/queue`,
          });
        } else {
          console.log("Redirect to registering queue view.");
          this.$router.push({
            path: `/courses/${payload.course.hashId}/queue`, //TODO CHANGE BACK
          });
        }
      }
      if (!payload.active) {
        console.log(
          "Clicked on inactive course, you can't queue up " +
            payload.course.courseCode
        );
      }
    },
  },
};
</script>

<style scoped></style>
