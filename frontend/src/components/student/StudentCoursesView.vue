<template>
  <div class="title">
    <h2>Courses</h2>
  </div>
  <div class="courses">
    <StudentCourses
      :courses="activeCourses"
      activeOrInactive="Active"
      @course-clicked="ActiveClicked"
    />
    <StudentCourses
      :courses="inactiveCourses"
      activeOrInactive="Inactive"
      @course-clicked="InactiveClicked"
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
    };
  },
  methods: {
    ActiveClicked(course) {
      console.log("Active course clicked: " + course.code);
    },
    InactiveClicked(course) {
      console.log("Inactive course clicked: " + course.code);
    },
  },
};
</script>

<style scoped></style>
