<template>
  <task-bar-header :header="header" v-model="dropdown"></task-bar-header>
  <task-bar
    :dropdown="dropdown"
    v-for="(opt, i) in tasks"
    :key="i"
    :task="opt"
    :enabled="checkbox"
    v-model="opt.done"
  ></task-bar>
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
    checkbox: {
      type: Boolean,
      default: true,
    },
    modelValue: {
      type: Array,
    },
  },
  data() {
    return {
      dropdown: true,
    };
  },
};
</script>

<style scoped></style>
