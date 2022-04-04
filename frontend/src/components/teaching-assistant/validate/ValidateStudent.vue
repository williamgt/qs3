<template>
  <task-bar-header :header="getHeader(student)"></task-bar-header>
  <form @submit.prevent="onSubmit"></form>
  <student-queue-info
    :message="info.comment"
    :validate="info.helpOrValidate"
  ></student-queue-info>
  <div class="student-location-container">
    <student-location
      :home="location.home"
      :location="location"
    ></student-location>
  </div>
  <div>
    <student-tasks header="Tasks" :tasks="tasks"></student-tasks>
  </div>
  <div>
    <base-button @click="test" class="btn-outline-success">Confirm</base-button>
    <base-button class="btn-close">Postpone</base-button>
  </div>
</template>

<script>
import { useField, useForm } from "vee-validate";
import BaseDisplay from "@/input-components/BaseDisplay";
import StudentLocation from "@/components/teaching-assistant/validate/StudentLocation";
import BaseButton from "@/input-components/BaseButton";
import StudentTasks from "@/components/teaching-assistant/validate/StudentTasks";
import TaskBarHeader from "@/components/teaching-assistant/validate/BarHeader";
import StudentQueueInfo from "@/components/teaching-assistant/validate/StudentQueueInfo";

export default {
  name: "ValidateStudent",
  // eslint-disable-next-line vue/no-unused-components
  components: {
    StudentQueueInfo,
    TaskBarHeader,
    // eslint-disable-next-line vue/no-unused-components
    StudentTasks,
    // eslint-disable-next-line vue/no-unused-components
    BaseButton,
    // eslint-disable-next-line vue/no-unused-components
    StudentLocation,
    // eslint-disable-next-line vue/no-unused-components
    BaseDisplay,
  },
  data() {
    return {
      location: {
        type: Object,
        required: true,
      },
      student: {
        type: Object,
        required: true,
      },
      tasks: {
        type: Array,
      },
      info: {
        type: Object,
        required: true,
      },
    };
  },
  methods: {
    getName(student) {
      return student.lastname + ", " + student.firstname;
    },
    getHeader(student) {
      return "Student: " + student.lastname + ", " + student.firstname;
    },
    test() {
      console.log(this.tasks[0]);
      console.log(this.tasks[1]);
    },
  },
  async created() {
    console.log(this.$store.state.validateStud);
    this.location = this.$store.state.validateStud.location;
    this.student = this.$store.state.validateStud.user;
    this.info.comment = this.$store.state.validateStud.comment;
    this.info.helpOrValidate = this.$store.state.validateStud.helpOrValidate;
    this.info.date = this.$store.state.validateStud.date;
    this.tasks = this.$store.state.validateStud.tasks;
  },
  setup(params) {
    function onSubmit() {
      //console.log(home.value);
    }
    console.log(params);
    const validations = {
      tasks: () => {
        return true;
      },
      group: () => {
        return group.value;
      },
    };

    useForm({
      validationSchema: validations,
    });
    const {
      value: message,
      errorMessage: messageError,
      handleChange,
    } = useField("message");
    const { value: home, errorMessage: homeError } = useField("home");
    const { value: campus, errorMessage: campusError } = useField("campus");
    const { value: building, errorMessage: buildingError } =
      useField("building");
    const { value: room, errorMessage: roomError } = useField("room");
    const { value: table, errorMessage: tableError } = useField("table");
    const { value: vali, errorMessage: valiError } = useField("validation");
    const { value: group, errorMessage: groupError } = useField("group");
    return {
      onSubmit,
      home,
      homeError,
      campus,
      campusError,
      building,
      buildingError,
      room,
      roomError,
      table,
      tableError,
      vali,
      valiError,
      message,
      messageError,
      group,
      groupError,
      handleChange,
    };
  },
};
</script>

<style scoped>
.student-location-container {
}
</style>
