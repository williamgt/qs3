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
import router from "@/router";

export default {
  name: "TACoursesView",
  components: {
    TACourses,
  },
  data() {
    return {
      activeCourses: [],
      inactiveCourses: [
        {
          title: "Fulllstack",
          code: "IDATT2105",
        },
        {
          title: "Fysikk",
          code: "IFYT1001",
        },
      ],
    };
  },
  methods: {
    moveToActive(payload) {
      if (!payload.active) {
        let hashId = payload.course.hashId;
        let statusCode;
        taActivateOrDeactivateQueue(hashId)
          .then((payload) => {
            statusCode = payload.status;
          })
          .catch((e) => {
            console.log(e);
          });
        if (statusCode != 200) {
          alert("Seomthing went wrong, got statuscode " + statusCode);
          return;
        }
        router.push("/");
        /*let index = this.inactiveCourses.indexOf(payload.course);
        if (index > -1) {
          this.inactiveCourses.splice(index, 1);
          this.activeCourses.push(payload.course);
          this.activeCourses.sort((a, b) => a.code.localeCompare(b.code));
          console.log("TA activating course queue..."); //TODO remove
        } else {
          alert("No such course in the inactive courses");
        }*/
        /*else {
        alert(
          "Something wrong happened during initialization of inactive course " +
            payload.course
        );*/
      }
    },
    moveToInactive(payload) {
      if (payload.active) {
        let hashId = payload.course.hashId;
        let statusCode;

        taActivateOrDeactivateQueue(hashId)
          .then((payload) => {
            statusCode = payload.status;
          })
          .catch((e) => {
            console.log(e);
          });
        if (statusCode != 200) {
          alert("Seomthing went wrong, got statuscode " + statusCode);
          return;
        }
        router.push("/");
        /*let index = this.activeCourses.indexOf(payload.course);
        if (index > -1) {
          this.activeCourses.splice(index, 1);
          this.inactiveCourses.push(payload.course);
          this.inactiveCourses.sort((a, b) => a.code.localeCompare(b.code));
          console.log("TA disabling course queue..."); //TODO remove
        } else {
          alert("No such course in the active courses");
        }
      } else {
        alert(
          "Something wrong happened during initialization of active course: " +
            payload.course
        );*/
      }
    },
  },
};
</script>

<style scoped></style>
