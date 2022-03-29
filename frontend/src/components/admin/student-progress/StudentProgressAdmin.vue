<template>
  <base-display label="First name" :message="student.firstName"></base-display>
  <base-display label="Last name" :message="student.lastName"></base-display>
  <base-display label="Email" :message="student.email"></base-display>
  <student-tasks
    v-for="course in student.courses"
    :header="course.id"
    :key="course.id"
    :checkbox="false"
    :tasks="course.tasks"
  ></student-tasks>
</template>

<script>
import TaskBarHeader from "@/components/teaching-assistant/validate/BarHeader";
import BaseDisplay from "@/input-components/BaseDisplay";
import StudentTasks from "@/components/teaching-assistant/validate/StudentTasks";
export default {
  name: "StudentProgressAdmin",
  // eslint-disable-next-line vue/no-unused-components
  components: { StudentTasks, BaseDisplay, TaskBarHeader },
  props: {
    student: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      dropdown: false,
    };
  },
};
</script>

<style scoped></style>
