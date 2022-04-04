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
      this.$router.push({ path: `/courses/${payload.course.hashId}` });
    },
    courseTasksClicked(payload) {
      this.$router.push({ path: `/courses/${payload.course.hashId}` });
    },
    async courseQueueClicked(payload) {
      if (payload.active) {
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
          await this.$router.push({
            path: `/courses/${payload.course.hashId}/queue`,
          });
        } else {
          await this.$router.push({
            path: `/courses/${payload.course.hashId}/register`, //TODO CHANGE BACK
          });
        }
      }
      // eslint-disable-next-line no-empty
      if (!payload.active) {
      }
    },
  },
};
</script>

<style scoped></style>
