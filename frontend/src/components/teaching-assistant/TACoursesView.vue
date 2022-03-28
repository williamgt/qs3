<template>
  <div class="validate-list-container">
    <div class="active-container">
      <TACourses
        :courses="activeCourses"
        :active="true"
        @changed-active-status="moveToInactive"
      ></TACourses>
    </div>
    <div class="inactive-container">
      <TACourses
        :courses="inactiveCourses"
        :active="false"
        @changed-active-status="moveToActive"
      ></TACourses>
    </div>
  </div>
</template>

<script>
import TACourses from "../teaching-assistant/TACourses";

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
      ],
    };
  },
  methods: {
    moveToActive(payload) {
      if (!payload.active) {
        let index = this.inactiveCourses.indexOf(payload.course);
        if (index > -1) {
          this.inactiveCourses.splice(index, 1);
          this.activeCourses.push(payload.course);
          console.log("TA activating course queue...");
        } else {
          alert("No such course in the inactive courses");
        }
      } else {
        alert(
          "Something wrong happened during initialization of inactive course " +
            payload.course
        );
      }
    },
    moveToInactive(payload) {
      if (payload.active) {
        let index = this.activeCourses.indexOf(payload.course);
        if (index > -1) {
          this.activeCourses.splice(index, 1);
          this.inactiveCourses.push(payload.course);
          console.log("TA disabling course queue...");
        } else {
          alert("No such course in the active courses");
        }
      } else {
        alert(
          "Something wrong happened during initialization of active course: " +
            payload.course
        );
      }
    },
  },
};
</script>

<style scoped></style>
