<template>
  <base-display :message="course.id" label="CourseID:"></base-display>
  <base-button>Retire</base-button><base-button>Edit</base-button>
  <student-list header="Students" :students="course.students"></student-list>
</template>

<script>
import BaseDisplay from "@/components/input-components/BaseDisplay";
import BaseButton from "@/components/input-components/BaseButton";
import StudentList from "@/components/admin/course/StudentsList";
export default {
  name: "CourseAdmin",
  components: { StudentList, BaseButton, BaseDisplay },
  props: {
    course: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped></style>
