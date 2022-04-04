<template>
  <div class="button-container" v-if="this.isStudent">
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
</template>

<script>
import { useRoute } from "vue-router";
//import useStore from "vuex/dist/vuex.mjs";
import { ref } from "vue";
import { getQueueInfoFromHashId } from "@/services/queueServices";
import QueueHeader from "@/components/queue/QueueHeader";
import QueueElement from "@/components/queue/QueueElement";
import { hasTAAccess } from "@/api/AuthAPI";
import {useStore} from "vuex";

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
    const store = useStore();
    let isStudent = !hasTAAccess(store.state.auth.role);
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
      isStudent,
    };
  },
};
</script>

<style scoped>
.button-container {
  text-align: center;
  margin-bottom: 10px;
}
</style>
