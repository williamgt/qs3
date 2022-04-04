<template>
  <h2>Location</h2>
  <h3 v-if="home">Hjemme</h3>
  <div v-if="!home">
    <base-display label="Campus" :message="location.campus.name">
    </base-display>
    <base-display label="Building" :message="location.building.name">
    </base-display>
    <base-display label="Room" :message="location.room.roomName">
    </base-display>
    <base-display label="Table" :message="location.room.tables"> </base-display>
  </div>
</template>

<script>
import BaseDisplay from "@/components/input-components/BaseDisplay";
export default {
  name: "StudentLocation",
  components: { BaseDisplay },
  props: {
    location: {
      type: Object,
    },
    home: {
      type: Boolean,
      default: false,
    },
  },
};
</script>

<style scoped></style>
