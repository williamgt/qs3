<template>
  <h2>Location</h2>
  <h3 v-if="home">Hjemme</h3>
  <div v-if="!home">
    <base-display label="Campus" :message="campus"> </base-display>
    <base-display label="Building" :message="building"> </base-display>
    <base-display label="Room" :message="room"> </base-display>
    <base-display label="Table" :message="table"> </base-display>
  </div>
</template>

<script>
import BaseDisplay from "@/input-components/BaseDisplay";
export default {
  name: "StudentLocation",
  components: { BaseDisplay },
  props: {
    campus: {
      type: String,
      default: "",
    },
    building: {
      type: String,
      default: "",
    },
    room: {
      type: String,
      default: "",
    },
    table: {
      type: Number,
      default: 0,
    },
    home: {
      type: Boolean,
      default: false,
    },
  },
};
</script>

<style scoped></style>
