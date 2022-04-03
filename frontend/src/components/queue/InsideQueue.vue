<template>
  <div class="queue-page-container">
    <div class="title">
      <h2>Queue</h2>
    </div>
    <div class="queue-container">
      <Suspense>
        <template #default>
          <div class="queue-container">
            <InsideQueueComponent></InsideQueueComponent>
          </div>
        </template>
        <template #fallback> Loading... </template>
      </Suspense>
    </div>
  </div>
</template>

<script>
import { useRoute } from "vue-router";
import InsideQueueComponent from "@/components/queue/InsideQueueComponent";

export default {
  name: "InsideQueue",
  components: { InsideQueueComponent },
  props: {
    courses: {
      type: Array,
      required: true,
    },
  },
  setup() {
    const route = useRoute();
    console.log(route.params); //Course hash
  },
};
</script>

<style scoped>
.queue-container {
  margin-right: 5%;
  margin-left: 5%;
}
</style>
