<template>
  <task-bar-header :header="header"></task-bar-header>
  <task-bar v-for="opt in tasks" :key="opt.id" :task="opt"></task-bar>
</template>

<script>
import TaskBar from "@/components/teaching-assistant/validate/TaskBar";
import TaskBarHeader from "@/components/teaching-assistant/validate/BarHeader";
export default {
  name: "StudentTasks",
  components: { TaskBarHeader, TaskBar },
  props: {
    header: {
      type: String,
      required: true,
    },
    tasks: {
      type: Array,
    },
  },
};
</script>

<style scoped></style>
