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
import { checkIfInQueue } from "@/services/queueServices";

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
    async courseQueueClicked(payload) {
      if (payload.active) {
        console.log(
          "Clicked on active course, you can queue up " +
            payload.course.courseCode
        );
        let inQueue;
        await checkIfInQueue(
          payload.course.hashId,
          this.$store.state.personLoggedIn.id
        )
          .then((response) => {
            inQueue = response.data;
          })
          .catch((e) => {
            console.log(e);
          });

        if (inQueue) {
          console.log("Redirect directly to queue view.");
          await this.$router.push({
            path: `/courses/${payload.course.hashId}/queue`,
          });
        } else {
          console.log("Redirect to registering queue view.");
          await this.$router.push({
            path: `/courses/${payload.course.hashId}/register`, //TODO CHANGE BACK
          });
        }
      }
      if (!payload.active) {
        alert(
          "Clicked on inactive course, you can't queue up " +
            payload.course.courseCode
        );
      }
    },
  },
};
</script>

<style scoped></style>
