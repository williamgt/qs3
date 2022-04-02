<template>
  <student-progress-admin :student="student"></student-progress-admin>
</template>

<script>
import StudentProgressAdmin from "@/components/admin/student-progress/StudentProgressAdmin";
export default {
  name: "StudentProgressAdminView",
  components: { StudentProgressAdmin },
  data() {
    return {
      student: {
        email: "student@ntnu.stud.no",
        firstName: "Hans",
        lastName: "Larsen",
        courses: [
          {
            id: "IDATT2021",
            tasks: [
              { name: "Task 1", id: 1, done: false },
              { name: "Task 2", id: 2, done: true },
            ],
          },
          {
            id: "IDATT2022",
            tasks: [
              { name: "Task 1", id: 1, done: false },
              { name: "Task 2", id: 2, done: false },
            ],
          },
        ],
      },
    };
  },
};
</script>

<style scoped></style>
