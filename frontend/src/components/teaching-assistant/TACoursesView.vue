<template>
  <div class="validate-list-container">
    <Suspense>
      <template #default>
        <div class="active-container">
          <TACourses
            :courses="activeCourses"
            :active="true"
            @changed-active-status="moveToInactive"
          ></TACourses>
        </div>
      </template>
      <template #fallback><p>Loading active courses...</p></template>
    </Suspense>
    <Suspense>
      <template #default>
        <div class="inactive-container">
          <TACourses
            :courses="inactiveCourses"
            :active="false"
            @changed-active-status="moveToActive"
          ></TACourses>
        </div>
      </template>
      <template #fallback><p>Loading inactive courses...</p></template>
    </Suspense>
  </div>
</template>

<script>
import TACourses from "../teaching-assistant/TACourses";
import { taActivateOrDeactivateQueue } from "@/services/queueServices";

export default {
  name: "TACoursesView",
  components: {
    TACourses,
  },
  methods: {
    moveToActive(payload) {
      if (!payload.active) {
        let hashId = payload.course.hashId;
        let statusCode;
        taActivateOrDeactivateQueue(hashId)
          .then((payload) => {
            statusCode = payload.status;
            if (statusCode !== 200) {
              alert("Something went wrong, got status code " + statusCode);
              return;
            }
            this.$router.push("/");
          })
          .catch((e) => {
            console.log(e);
          });
      }
    },
    moveToInactive(payload) {
      if (payload.active) {
        let hashId = payload.course.hashId;
        let statusCode;

        taActivateOrDeactivateQueue(hashId)
          .then((payload) => {
            statusCode = payload.status;
            if (statusCode !== 200) {
              alert("Something went wrong, got status code " + statusCode);
              return;
            }
            this.$router.push("/");
          })
          .catch((e) => {
            console.log(e);
          });
      }
    },
  },
};
</script>

<style scoped></style>
