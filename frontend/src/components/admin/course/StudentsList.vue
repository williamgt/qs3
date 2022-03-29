<template>
  <bar-header-students
    :header="header"
    v-model="dropdown"
  ></bar-header-students>
  <div class="student-bar-container">
    <student-bar
      :dropdown="dropdown"
      v-for="stud in students"
      :key="stud.lastName"
      :enabled="checkbox"
      v-model="stud.done"
      :student="stud"
    ></student-bar>
  </div>
</template>

<script>
import BarHeaderStudents from "@/components/admin/course/BarHeaderStudents";
import StudentBar from "@/components/admin/course/StudentBar";
export default {
  name: "StudentList",
  components: { StudentBar, BarHeaderStudents },
  props: {
    header: {
      type: String,
      required: true,
    },
    students: {
      type: Array,
    },
    checkbox: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      dropdown: true,
    };
  },
};
</script>

<style scoped>
.student-bar-container {
  height: 300px;
  overflow: scroll;
}
</style>
