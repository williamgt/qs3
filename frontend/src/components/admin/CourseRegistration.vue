<template>
  <div class="register-container">
    <div class="header">
      <h2>Register course</h2>
    </div>
    <div class="register-form">
      <form @submit.prevent="registerCourse">
        <fieldset>
          <legend>Course information</legend>
          <div class="course-name">
            <BaseInput label="Name" v-model="courseName"></BaseInput>
          </div>
          <div class="course-code">
            <BaseInput label="Code" v-model="courseCode"></BaseInput>
          </div>
        </fieldset>

        <fieldset>
          <legend>Teachers and teaching assistants</legend>
          <div class="information">
            <h4>Please separate the names in the following fields with ','</h4>
          </div>
          <div class="teacher-input">
            <BaseTextArea
              label="Register teachers"
              v-model="teachersString"
            ></BaseTextArea>
          </div>
          <div class="teacher-assistant-input">
            <BaseTextArea
              label="Register TAs"
              v-model="tasString"
            ></BaseTextArea>
          </div>
        </fieldset>

        <fieldset>
          <legend>File uploader</legend>
          <div class="file-uploader">
            <label for="file">Choose a .csv file of students to register</label>
            <input
              type="file"
              id="file"
              accept="text/csv"
              @change="fileUploaded($event)"
            />
          </div>
        </fieldset>

        <fieldset>
          <legend>Obligatory task</legend>
          <BaseSelect
            label="How many tasks?"
            v-model.number="taskAmount"
            :options="taskAlternatives"
          ></BaseSelect>
          <BaseSelect
            v-if="taskAmount > 0"
            label="How many sets of tasks?"
            v-model.number="taskSetAmount"
            :options="taskAlternatives"
            :error="taskSetError"
            @click="validateSetAmount"
          ></BaseSelect>
          <BaseCheckboxGroup></BaseCheckboxGroup>
        </fieldset>

        <button type="submit">Submit</button>
      </form>
    </div>
  </div>
</template>

<script>
import BaseTextArea from "@/input-components/BaseTextArea";
import BaseInput from "@/input-components/BaseInput";
import BaseSelect from "@/input-components/BaseSelect";
import BaseCheckboxGroup from "@/input-components/BaseCheckboxGroup";

export default {
  name: "CourseRegistration",
  components: {
    BaseTextArea,
    BaseInput,
    BaseSelect,
    BaseCheckboxGroup,
  },
  data() {
    return {
      courseName: "",
      courseCode: "",
      teachersString: "",
      tasString: "",
      csvFile: "PLACEHOLDER",
      taskAmount: 0,
      taskSetAmount: 0,
      taskSetError: "",
      taskAlternatives: [
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
        20,
      ],
      taskAlternativesName: ["Task 1", "Task 2", "Task 3", "Task 4", "Task 5", "Task 6", "Task 7","Task 8","Task 9","Task 10","Task 11","Task 12","Task 13","Task 14","Task 15","Task 16","Task 17","Task 18","Task 19","Task 20"],
    };
  },
  methods: {
    //https://serversideup.net/uploading-files-vuejs-axios/
    fileUploaded(event) {
      console.log(event.target.files[0]);
      this.csvFile = event.target.files[0];
    },
    validateSetAmount() {
      if (this.taskSetAmount > this.taskAmount) {
        //alert("The amount of sets cannot exceed the amount of tasks."); //Does not work properly
        this.taskSetError =
          "The amount of sets cannot exceed the amount of tasks.";
      } else {
        this.taskSetError = "";
      }
    },
    registerCourse() {
      //Check every field
      console.log("Registering course...");
    },
  },
  computed: {

  }
};
</script>

<style scoped></style>
