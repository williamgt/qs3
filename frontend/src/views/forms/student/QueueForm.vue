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
      <mult-check :options="tasks.toChoose" v-model:value="task"></mult-check>
      <!--      <base-checkbox-group-->
      <!--        :model-value="heroes"-->
      <!--        v-model:model-value="heroes"-->
      <!--        :options="tasks.toChoose"-->
      <!--        :vertical="true"-->
      <!--        name="Tasks"-->
      <!--      >-->
      <!--      </base-checkbox-group>-->
    </div>
    <div class="radio-group">
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
import { ref } from "vue";
import MultCheck from "@/input-components/mult-check";
export default {
  name: "QueueForm",
  components: {
    MultCheck,
    Multiselect,
    BaseTextArea,
    BaseRadioGroup,
    // eslint-disable-next-line vue/no-unused-components
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
          { name: "Task 1", id: 1 },
          { name: "Task 2", id: 2 },
          { name: "Task 3", id: 3 },
          { name: "Task 4", id: 4 },
          { name: "Task 5", id: 5 },
          { name: "Task 6", id: 6 },
          { name: "Task 7", id: 7 },
          { name: "Task 8", id: 8 },
        ],
        chosen: ref([]),
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
        return true;
      },
      group: () => {
        return group.value;
      },
    };

    useForm({
      validationSchema: validations,
    });
    const task = ref([]);
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
      task,
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
.radio-group span {
  align-self: auto;
}
button,
[type="button"],
[type="reset"],
[type="submit"] {
  -webkit-appearance: none;
}
button::-moz-focus-inner,
[type="button"]::-moz-focus-inner,
[type="reset"]::-moz-focus-inner,
[type="submit"]::-moz-focus-inner {
  border-style: none;
  padding: 0;
}
button:-moz-focusring,
[type="button"]:-moz-focusring,
[type="reset"]:-moz-focusring,
[type="submit"]:-moz-focusring {
  outline: 2px solid #39b982;
}
.button {
  display: inline-flex;
  align-items: center;
  justify-content: space-between;
  height: 52px;
  padding: 0 40px;
  background: transparent;
  border: none;
  border-radius: 6px;
  text-align: center;
  font-weight: 600;
  white-space: nowrap;
  transition: all 0.2s linear;
}
.button:hover {
  -webkit-transform: scale(1.02);
  transform: scale(1.02);
  box-shadow: 0 7px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
.button:active {
  -webkit-transform: scale(1);
  transform: scale(1);
  box-shadow: none;
}
.button:focus {
  outline: 0;
}
.button:disabled {
  -webkit-transform: scale(1);
  transform: scale(1);
  box-shadow: none;
}
.button + .button {
  margin-left: 1em;
}
button.-fill-gradient {
  background: linear-gradient(to right, #16c0b0, #84cf6a);
  color: #ffffff;
}
.button.-fill-gray {
  background: rgba(0, 0, 0, 0.5);
  color: #ffffff;
}
.button.-size-small {
  height: 32px;
}
button.-icon-right {
  text-align: left;
  padding: 0 20px;
}
.button.-icon-right > .icon {
  margin-left: 10px;
}
button.-icon-left {
  text-align: right;
  padding: 0 20px;
}
button.-icon-left > .icon {
  margin-right: 10px;
}
button.-icon-center {
  padding: 0 20px;
}
.button-accept {
  background-color: limegreen;
}
.button-decline {
  background-color: orangered;
}
</style>
