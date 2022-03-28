<template>
  <form @submit.prevent="onSubmit">
    <h2>Lokasjon</h2>
    <base-checkbox label="Jobber hjemme" v-model="home"></base-checkbox>
    <div class="base-select-container">
      <div class="base-select-item">
        <base-select
          :disabled="this.home"
          label="Campus"
          :options="this.location.campus"
          v-model="campus"
          :error="campusError"
        ></base-select>
      </div>
      <div class="base-select-item">
        <base-select
          :disabled="this.home"
          label="Building"
          :options="this.location.building"
          v-model="building"
          :error="buildingError"
        ></base-select>
      </div>
      <div class="base-select-item">
        <base-select
          :disabled="this.home"
          label="Room"
          :options="this.location.rooms"
          v-model="room"
          :error="roomError"
        ></base-select>
      </div>
      <div class="base-select-item">
        <base-select
          :disabled="this.home"
          label="Table"
          :options="this.location.table"
          v-model="table"
          :error="tableError"
        ></base-select>
      </div>
    </div>
    <div>
      <h1>Tasks</h1>
      <base-checkbox-group
        :model-value="this.task"
        :options="tasks.toChoose"
        :vertical="true"
        name="Tasks"
      >
      </base-checkbox-group>
    </div>
    <div>
      <base-radio-group
        :model-value="vali"
        v-model="vali"
        :options="validation.toChoose"
        name="Type"
      ></base-radio-group>
    </div>
    <div class="base-select">
      <Multiselect
        v-model="group"
        mode="tags"
        :close-on-select="false"
        :searchable="true"
        :create-option="true"
        :options="groups.toChoose"
      />
    </div>
    <div>
      <base-text-area
        label="Message"
        v-model="message"
        :error="messageError"
        @change="handleChange"
      >
      </base-text-area>
    </div>
    <BaseButton type="submit" class="button button-accept" @click="test">
      Queue
    </BaseButton>
    <BaseButton type="cancel" class="button button-decline">
      Cancel
    </BaseButton>
  </form>
</template>

<script>
import BaseSelect from "@/input-components/BaseSelect";
import BaseCheckbox from "@/input-components/BaseCheckbox";
import { useField, useForm } from "vee-validate";
import BaseButton from "@/input-components/BaseButton";
import BaseCheckboxGroup from "@/input-components/BaseCheckboxGroup";
import BaseRadioGroup from "@/input-components/BaseRadioGroup";
import BaseTextArea from "@/input-components/BaseTextArea";
import Multiselect from "@vueform/multiselect";
export default {
  name: "QueueForm",
  components: {
    Multiselect,
    BaseTextArea,
    BaseRadioGroup,
    BaseCheckboxGroup,
    BaseButton,
    BaseCheckbox,
    BaseSelect,
  },
  data() {
    return {
      location: {
        campus: ["Gløshaugen", "Dragvoll", "Kalvskinnet"],
        building: ["Realfagsbygget"],
        rooms: ["A4-112", "Thildekontoret", "S7"],
        table: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      },
      tasks: {
        toChoose: [
          { label: "Task 1", value: false },
          { label: "Task 2", value: false },
          { label: "Task 3", value: false },
          { label: "Task 4", value: false },
          { label: "Task 5", value: false },
          { label: "Task 6", value: false },
          { label: "Task 7", value: false },
          { label: "Task 8", value: false },
        ],
        chosen: {
          type: [],
          default: [],
        },
      },
      validation: {
        toChoose: [
          { label: "Validation", value: true },
          { label: "Help", value: false },
        ],
      },
      groups: {
        toChoose: [
          { label: "Ola Norman", value: "Olav Norman" },
          { label: "Hans Hansen", value: "Hans Hansen" },
          { label: "Lars Larsen", value: "Lars Larsen" },
        ],
      },
    };
  },
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
        return task.value;
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
    const { value: task, errorMessage: taskError } = useField("task");
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
      task,
      taskError,
      group,
      groupError,
      handleChange,
    };
  },
  methods: {
    test() {
      console.log(this.message);
      console.log(this.home);
      console.log(this.campus);
      console.log(this.table);
      console.log(this.room);
      console.log(this.vali);
      console.log(this.group);
      console.log(this.task);
      console.log(this.tasks.toChoose);
    },
  },
};
</script>
<style scoped src="@vueform/multiselect/themes/default.css">
.base-select-container {
  display: inline-grid;
  grid-template-columns: auto auto;
  padding: 10px;
}
.base-select {
  background: #ffffff;
  margin: 20px;
  border-color: #e7e7e7;
  padding: 40px;
}
.base-select-container select {
  pointer-events: none;
}
.base-select-item {
  padding: 10px;
}
label {
  color: rgba(0, 0, 0, 0.5);
  font-weight: 700;
}
</style>
