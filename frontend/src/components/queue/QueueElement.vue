<template>
  <div
    class="grid-container"
    @click="$emit('update:modelValue', this.studInfo)"
  >
    <div class="grid-item">{{ studInfo.user.lastname }}</div>
    <div class="grid-item">
      {{ studInfo.location.home ? "Home" : studInfo.location.room.roomName }}
    </div>
    <div class="grid-item">{{ hOrV(studInfo.helpOrValidate) }}</div>
    <div class="grid-item">
      <span v-for="(task, index) in studInfo.tasks" :key="index">
        {{ task.description }} -
      </span>
    </div>
  </div>
</template>

<script>
import { hasTAAccess } from "@/api/AuthAPI";

export default {
  name: "QueueElement",
  props: {
    studInfo: {
      type: Object,
      required: true,
    },
    index: Number,
    modelValue: {
      type: Object,
    },
  },
  methods: {
    hOrV(arg) {
      if (arg === "HELP") {
        return "Help";
      } else if (arg === "VALID") {
        return "Validate";
      } else {
        return "INVALID";
      }
    },
  },
  computed: {
    canValidate() {
      return hasTAAccess(this.$store.state.auth.role);
    },
    currentHash() {
      return this.$route.params.id;
    },
  },
  // eslint-disable-next-line no-unused-vars
  beforeRouteLeave(to, from) {
    alert("h");
    this.$store.dispatch("setValidateStud", this.studInfo);
  },
};
</script>

<style scoped>
.header {
  font-size: 32px;
  text-align: center;
}
.grid-container {
  display: grid;
  grid-template-columns: 25% 25% 25% 25%;
  width: 100%;
}
.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding-bottom: 25px;
  padding-top: 25px;
  font-size: 20px;
  text-align: center;
  overflow: hidden;
  max-width: 100%;
  width: 100%;
}
link {
  text-decoration: none;
  display: inline-block;
  border-bottom: none;
  color: black;
}
a:link,
a:visited,
a:hover,
a:active {
  text-decoration: none;
}
</style>
