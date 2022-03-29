<template>
  <course-admin :course="course"></course-admin>
</template>

<script>
import CourseAdmin from "@/components/admin/course/CourseAdmin";
export default {
  name: "CourseAdminView",
  components: { CourseAdmin },
  data() {
    return {
      course: {
        id: "IDATT2001",
        students: [
          {
            email: "student@ntnu.stud.no",
            firstName: "Hans",
            lastName: "Larsen",
          },
          {
            email: "student@ntnu.stud.no",
            firstName: "af",
            lastName: "Lasfsa",
          },
          {
            email: "student@ntnu.stud.no",
            firstName: "Ola",
            lastName: "Norman",
          },
          {
            email: "student@ntnu.stud.no",
            firstName: "Ola",
            lastName: "Norman",
          },
          {
            email: "student@ntnu.stud.no",
            firstName: "Ola",
            lastName: "Norman",
          },
          {
            email: "student@ntnu.stud.no",
            firstName: "Ola",
            lastName: "Norman",
          },
          {
            email: "student@ntnu.stud.no",
            firstName: "Ola",
            lastName: "Norman",
          },
          {
            email: "student@ntnu.stud.no",
            firstName: "Ola",
            lastName: "Norman",
          },
        ],
      },
    };
  },
};
</script>

<style scoped></style>
