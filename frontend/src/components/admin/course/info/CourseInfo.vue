<template>
  <bar-header-students
    :header="course.id"
    v-for="course in courses"
    :key="course.id"
  ></bar-header-students>
</template>

<script>
import BarHeaderStudents from "@/components/admin/course/BarHeaderStudents";
export default {
  name: "CourseInfo",
  components: { BarHeaderStudents },
  props: {
    courses: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped></style>
