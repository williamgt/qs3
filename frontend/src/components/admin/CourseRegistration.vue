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
            @click="updateTasks"
          ></BaseSelect>
          <BaseSelect
            v-if="taskAmount > 0"
            label="How many sets of tasks?"
            v-model.number="taskSetAmount"
            :options="properSetAlternatives"
            :error="taskSetError"
            @click="validateSetAmount"
          ></BaseSelect>
          <div v-if="taskAmount > 0 && taskSetAmount > 1">
            <label>Which tasks are in each set?</label>
            <div v-for="(tasks, index) in taskSets" :key="index">
              <label>Set {{ index + 1 }}</label>
              <div v-for="(task, index) in tasks" :key="index">
                <BaseCheckbox
                  :label="task.task"
                  v-model="task.checked"
                ></BaseCheckbox>
              </div>
            </div>
          </div>

          <div v-if="taskAmount > 0 && taskSetAmount > 1">
            <label>How many tasks per set to be valid?</label>
            <div v-for="(set, index) in taskSets" :key="index">
              <BaseSelect
                :label="'Set ' + index"
                v-model.number="obligatoryPerSet[index]"
                :options="properSetAlternatives"
              ></BaseSelect>
            </div>
          </div>

          <div v-if="taskAmount > 0 && taskSetAmount === 1">
            <BaseSelect
              label="How many has to be done to be valid?"
              v-model.number="obligatoryPerSet[0]"
              :options="properSetAlternatives"
            ></BaseSelect>
          </div>
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
import BaseCheckbox from "@/input-components/BaseCheckbox";

export default {
  name: "CourseRegistration",
  components: {
    BaseTextArea,
    BaseInput,
    BaseSelect,
    BaseCheckbox,
  },
  data() {
    return {
      courseName: "",
      courseCode: "",
      teachersString: "",
      tasString: "",
      csvFile: "PLACEHOLDER",
      tasks: [],
      taskSets: [],
      taskSetsChosenTasks: [],
      obligatoryPerSet: [],
      taskAmount: 0,
      taskSetAmount: 0,
      taskSetError: "",
      taskAlternatives: [
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
        20,
      ],
      taskSetAlternatives: [],
    };
  },
  methods: {
    //https://serversideup.net/uploading-files-vuejs-axios/
    fileUploaded(event) {
      console.log(event.target.files[0]);
      this.csvFile = event.target.files[0];
    },
    updateTasks() {
      this.tasks = [];
      this.taskSets = [];
      this.obligatoryPerSet = [];
      this.taskSetAmount = 0;
      for (let i = 0; i < this.taskAmount; i++) {
        this.tasks[i] = "Task " + (i + 1);
      }
      this.taskSetAlternatives = this.taskAlternatives.slice(
        1,
        this.taskAmount + 1
      );
    },
    validateSetAmount() {
      if (this.taskSetAmount > this.taskAmount) {
        //alert("The amount of sets cannot exceed the amount of tasks."); //Does not work properly
        this.taskSetError =
          "The amount of sets cannot exceed the amount of tasks.";
        this.taskSets = [];
      } else {
        this.taskSetError = "";
        this.taskSets = [];
        this.obligatoryPerSet = [];
        for (let i = 0; i < this.taskSetAmount; i++) {
          this.taskSets[i] = [];
          for (let j = 0; j < this.taskAmount; j++) {
            this.taskSets[i][j] = {
              task: "Task " + (j + 1),
              checked: false,
              id: i + " " + j,
            };
          }
        }
      }
    },
    registerCourse() {
      //Check every field, split teacher and ta string into arrays etc
      console.log("Registering course...");
    },
  },
  computed: {
    properSetAlternatives() {
      return this.taskAlternatives.slice(1, this.taskAmount + 1);
    },
    computeTasks() {
      let tasks = [];
      for (let i = 0; i < this.taskAmount; i++) {
        tasks[i] = "Task " + (i + 1);
      }
      return tasks;
    },
  },
};
</script>

<style scoped></style>
