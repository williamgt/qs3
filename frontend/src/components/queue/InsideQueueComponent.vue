<template>
  <div class="button-container">
    <button @click="routeToRegister">Register in queue</button>
  </div>
  <queue-header></queue-header>
  <queue-element
    @click="routeToStudent"
    v-model="stud"
    v-for="(studInfo, index) in queueInfo"
    :key="studInfo.user.email"
    :stud-info="studInfo"
    :index="index"
  ></queue-element>
  <!--  <div v-for="(studInfo, index) in queueInfo" :key="studInfo.user.email">-->
  <!--    {{ index + 1 }} - {{ studInfo.user.firstname }}-->
  <!--    {{ studInfo.user.lastname }}, {{ hOrV(studInfo.helpOrValidate) }}:-->
  <!--    <div v-for="(task, index) in studInfo.tasks" :key="index">-->
  <!--      {{ task.description }}-->
  <!--    </div>-->
  <!--    <div v-if="studInfo.comment">{{ studInfo.comment }}</div>-->
  <!--  </div>-->
  {{ c_queue }}
</template>

<script>
import { useRoute } from "vue-router";
//import useStore from "vuex/dist/vuex.mjs";
import { ref } from "vue";
import { getQueueInfoFromHashId } from "@/services/queueServices";
import QueueHeader from "@/components/queue/QueueHeader";
import QueueElement from "@/components/queue/QueueElement";

export default {
  name: "InsideQueueComponent",
  components: { QueueElement, QueueHeader },
  data() {
    return {
      queueInfo: this.c_queue.studsInQueue,
      stud: undefined,
    };
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
    routeToRegister() {
      console.log("ROUTING");
    },
    async routeToStudent() {
      console.log("RouteTOStud");
      await this.$store.dispatch("setValidateStud", this.stud);
      await this.$router.push({
        path:
          "/courses/" +
          this.$route.params.id +
          "/" +
          this.stud.user.queueInfoId +
          "/validate",
      });
    },
  },
  async setup() {
    let c_queue;
    const route = useRoute();
    //const store = useStore();
    console.log(route.params); //Course hash

    await getQueueInfoFromHashId(route.params.id)
      .then((response) => {
        c_queue = ref(response.data);
        console.log(c_queue);
      })
      .catch((e) => {
        console.log(e);
      });
    return {
      c_queue,
    };
  },
};
</script>

<style scoped>
.button-container {
  text-align: center;
}
</style>
