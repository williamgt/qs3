<template>
  <validate-student
    :student="student"
    :location="location"
    :tasks="tasks"
    :info="info"
  ></validate-student>
</template>

<script>
import ValidateStudent from "@/components/teaching-assistant/validate/ValidateStudent";
export default {
  name: "ValidateStudentView",
  components: { ValidateStudent },
  data() {
    return {
      location: {
        campus: "Gløs",
        building: "Realfagsbygget",
        room: "A4",
        table: 1,
        home: false,
      },
      student: {
        firstName: "Olav",
        lastName: "Larsen",
      },
      tasks: [
        { name: "Task 1", id: 1, done: false },
        { name: "Task 2", id: 2, done: true },
      ],
      info: {
        group: ["William Tresselt"],
        message: "This is the message",
        validate: true,
      },
    };
  },
};
</script>

<style scoped></style>
