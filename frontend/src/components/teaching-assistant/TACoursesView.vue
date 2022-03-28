<template>
  <div class="validate-list-container">
    <div class="active-container">
      <TACourses :courses="activeCourses" title="Active"></TACourses>
    </div>
    <div class="inactive-container">
      <TACourses
        :courses="inactiveCourses"
        title="Inactive"
        @changed-active-status="a"
      >
      </TACourses>
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
};
</script>

<style scoped></style>
