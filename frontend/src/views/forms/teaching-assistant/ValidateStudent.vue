<template>
  <form @submit.prevent="onSubmit"></form>
  <!--  <student-location-->
  <!--    :home="false"-->
  <!--    campus="Gløs"-->
  <!--    building="Real"-->
  <!--    room="A4"-->
  <!--    table="1"-->
  <!--  ></student-location>-->

  <!--  <base-button>Confirm</base-button>-->
  <!--  <base-button>Cancel</base-button>-->
  <student-tasks></student-tasks>
</template>

<script>
import { useField, useForm } from "vee-validate";
import BaseDisplay from "@/input-components/BaseDisplay";
import StudentLocation from "@/components/teaching-assistant/validate/StudentLocation";
import BaseButton from "@/input-components/BaseButton";
import StudentTasks from "@/components/teaching-assistant/validate/StudentTasks";

export default {
  name: "ValidateStudent",
  // eslint-disable-next-line vue/no-unused-components
  components: { StudentTasks, BaseButton, StudentLocation, BaseDisplay },
  setup() {
    function onSubmit() {
      //console.log(home.value);
    }

    const validations = {
      home: (value) => {
        if (
          value === undefined &&
          (table.value === undefined || table.value === null)
        )
          return "It is required to tell your location";
        return true;
      },
      vali: () => {
        return vali.value;
      },
      room: () => {
        return true;
      },
      table: () => {
        return true;
      },
      task: () => {
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

<style scoped></style>
