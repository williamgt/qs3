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
      student: {
        type: Object,
      },
      location: {
        type: Object,
      },
      tasks: {
        type: Array,
      },
      info: {
        type: Object,
      },
    };
  },
  created() {
    this.student = this.$store.state.validateStud.user;
    this.location = this.$store.state.validateStud.location;
    this.tasks = this.$store.state.validateStud.tasks;
    this.info.comment = this.$store.state.validateStud.comment;
    this.info.helpOrValidate = this.$store.state.validateStud.helpOrValidate;
    this.info.date = this.$store.state.validateStud.date;
  },
};
</script>

<style scoped></style>
