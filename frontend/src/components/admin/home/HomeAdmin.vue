<template>
  <h1>Your active courses</h1>
  <course-info :courses="courses"></course-info>
</template>

<script>
import CourseInfo from "@/components/admin/course/info/CourseInfo";
export default {
  name: "HomeAdmin",
  components: { CourseInfo },
  props: {
    courses: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped></style>
