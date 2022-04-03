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
            <BaseInput
              label="Name"
              v-model="courseName"
              :error="courseNameError"
            ></BaseInput>
          </div>
          <div class="course-code">
            <BaseInput
              label="Code"
              v-model="courseCode"
              :error="courseCodeError"
            ></BaseInput>
          </div>
          <div class="course-year">
            <BaseInput
              label="Year"
              v-model.number="year"
              :error="yearError"
            ></BaseInput>
          </div>
          <div class="course-term">
            <BaseInput
              label="Term"
              v-model.number="term"
              :error="termError"
            ></BaseInput>
          </div>
        </fieldset>

        <fieldset>
          <legend>Teachers and teaching assistants</legend>
          <div class="information">
            <h4>Format the names like this: 'firstname, lastname, email\n'.</h4>
          </div>
          <div class="teacher-input">
            <BaseTextArea
              label="Register teachers"
              v-model="teachersString"
              :error="teacherStringError"
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
            <p>Format of file should be 'firstname, lastname, email\n'</p>
            <label for="file"
              >Choose a .csv file of students to register.</label
            >
            <input
              type="file"
              id="file"
              accept="text/csv"
              @change="fileUploaded($event)"
            />
            <p v-if="csvFileError" class="error">{{ csvFileError }}</p>
          </div>
        </fieldset>

        <fieldset>
          <legend>Obligatory task</legend>
          <BaseSelect
            label="How many tasks?"
            v-model.number="taskAmount"
            :options="taskAlternatives"
            @click="updateTasks"
            :error="taskAmountError"
          ></BaseSelect>
          <BaseSelect
            v-if="taskAmount > 0"
            label="How many sets of tasks?"
            v-model.number="taskSetAmount"
            :options="properSetAlternatives"
            :error="taskSetAmountError"
            @click="updateSetAmount"
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
            <p v-if="taskSetsError" class="error">{{ taskSetsError }}</p>
          </div>

          <div v-if="taskAmount > 0 && taskSetAmount > 1">
            <label>How many tasks per set to be valid?</label>
            <div v-for="(set, index) in taskSets" :key="index">
              <BaseSelect
                :label="'Set ' + (index + 1)"
                v-model.number="obligatoryPerSet[index]"
                :options="properSetAlternatives"
              ></BaseSelect>
            </div>
          </div>
          <p v-if="obligatoryError" class="error">{{ obligatoryError }}</p>

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
import {
  registerCourseService,
  getStudentsFromFile,
} from "@/services/registerCourseService";

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
      courseNameError: "",
      courseCode: "",
      courseCodeError: "",
      year: undefined,
      yearError: "",
      term: undefined,
      termError: "",
      teachersString: "",
      teachers: [],
      teacherStringError: "",
      tasString: "",
      tas: [],
      taStringError: "",
      csvFile: undefined,
      csvFileError: "",
      tasks: [],
      taskSets: [],
      taskSetsError: "",
      taskSetsChosenTasks: [],
      obligatoryPerSet: [],
      obligatoryError: "",
      taskAmount: 0,
      taskAmountError: "",
      taskSetAmount: 0,
      taskSetAmountError: "",
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
      this.csvFileError = "";
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
    updateSetAmount() {
      if (this.taskSetAmount > this.taskAmount) {
        //alert("The amount of sets cannot exceed the amount of tasks."); //Does not work properly
        this.taskSetAmountError =
          "The amount of sets cannot exceed the amount of tasks.";
        this.taskSets = [];
      } else {
        this.taskSetAmountError = "";
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
    insertChosenTasksInArray() {
      this.taskSetsChosenTasks = []; //Cleaning up if something in the array first
      for (let i = 0; i < this.taskSetAmount; i++) {
        this.taskSetsChosenTasks[i] = [];
        for (let j = 0; j < this.taskAmount; j++) {
          if (this.taskSets[i][j].checked) {
            this.taskSetsChosenTasks[i].push(this.taskSets[i][j]); //Adding only checked tasks to this list
          }
        }
      }
    },
    validateCourseName() {
      if (this.courseName.trim() === "") {
        this.courseNameError = "Course name can't be empty.";
        return false;
      } else if (this.courseName.length > 60) {
        this.courseNameError =
          "Course name can't be longer than 60 characters.";
        return false;
      } else {
        this.courseNameError = "";
        return true;
      }
    },
    validateCourseCode() {
      if (this.courseCode.length > 10) {
        this.courseCodeError =
          "Course code can't be longer than 10 characters.";
        return false;
      } else if (this.courseCode.trim() === "") {
        this.courseCodeError = "Course code must be given";
        return false;
      } else {
        this.courseCodeError = "";
        return true;
      }
    },
    validateYear() {
      if (this.year < 1000 || this.year > 9999) {
        this.yearError = "Year must be 4 digits";
        return false;
      } else if (isNaN(this.year)) {
        this.yearError = "Year must be a number";
      } else {
        this.yearError = "";
        return true;
      }
    },
    validateTerm() {
      if (this.term !== 1 && this.term !== 2) {
        this.termError = "Term must be either 1 or 2";
        return false;
      } else if (isNaN(this.term)) {
        this.termError = "Term must be a number";
      } else {
        this.termError = "";
        return true;
      }
    },
    splitAndValidateTextArea(input) {
      let inputArray = input.trim().split("\n");
      for (let i = 0; i < inputArray.length; i++) {
        let commasInLine = (inputArray[i].match(/,/g) || []).length; //Need two commas in each line
        if (commasInLine !== 2) return "Formatting is wrong";
      }
      return inputArray;
    },
    validateTaskAmount() {
      if (this.taskAmount == 0) {
        this.taskAmountError = "A course must have 1 or more tasks.";
        return false;
      } else {
        this.taskAmountError = "";
        return true;
      }
    },
    validateTaskSetAmount() {
      if (this.taskSetAmount === 0) {
        this.taskSetAmountError = "A course must have 1 or more task sets.";
        return false;
      } else {
        this.taskSetAmountError = "";
        return true;
      }
    },
    validateTaskSets() {
      //No need to validate if only one set
      if (this.taskSets.length === 1) return true;
      //Validating that no tasks overlap
      let checkedTasks = new Array(this.taskAmount).fill(false);
      for (let i = 0; i < this.taskSetAmount; i++) {
        for (let j = 0; j < this.taskAmount; j++) {
          if (
            checkedTasks[j] === true &&
            this.taskSets[i][j].checked === true
          ) {
            this.taskSetsError = "Some of the tasks in each set overlap.";
            return false;
          } else if (this.taskSets[i][j].checked === true) {
            checkedTasks[j] = true;
          }
        }
      }
      //Validating that all tasks are checked
      if (!checkedTasks.every((e) => e === true)) {
        this.taskSetsError = "Not every task is checked.";
        return false;
      }
      this.taskSetsError = "";
      return true;
    },
    validateObligatoryPerSet() {
      let obligatoryTotal = this.obligatoryPerSet.reduce(function (a, b) {
        return a + b;
      }, 0);
      if (obligatoryTotal > this.taskAmount) {
        this.obligatoryError =
          "Total amount of obligatory tasks can't exceed amount of tasks.";
        return false;
      }
      if (obligatoryTotal < this.taskSetAmount) {
        this.obligatoryError = "Must be at least one obligatory task per set.";
        return false;
      }
      for (let i = 0; i < this.taskSetAmount; i++) {
        let checkedPerTaskSet = 0;
        for (let j = 0; j < this.taskAmount; j++) {
          if (this.taskSets[i][j].checked === true) checkedPerTaskSet++;
        }
        if (
          checkedPerTaskSet < this.obligatoryPerSet[i] &&
          this.taskSetAmount > 1
        ) {
          this.obligatoryError =
            "Can't have more obligatory tasks than checked tasks.";
          return false;
        }
      }

      this.obligatoryError = "";
      return true;
    },
    studentCheck(students) {
      if (typeof students === "undefined") {
        this.csvFileError =
          "Something went wrong when extracting student info from file.";
        return false;
      } else {
        this.csvFileError = "";
        return true;
      }
    },
    validateEverything() {
      //Handling course information
      if (
        this.validateCourseName() &&
        this.validateCourseCode() &&
        this.validateYear() &&
        this.validateTerm()
      ) {
        //Handling teachers
        let teacherSplitRes = this.splitAndValidateTextArea(
          this.teachersString
        );
        if (typeof teacherSplitRes === "string") {
          this.teacherStringError = teacherSplitRes;
          return false;
        } else if (teacherSplitRes.length === 0) {
          this.teacherStringError = "Teachers must be present.";
          return false;
        } else {
          this.teacherStringError = "";
        }
        //Putting teachers into corresponding array
        for (let i = 0; i < teacherSplitRes.length; i++) {
          let teacherAtI = teacherSplitRes[i].split(","); //Guaranteed two commas per, no need to check again
          this.teachers[i] = {
            firstname: teacherAtI[0],
            lastname: teacherAtI[1],
            email: teacherAtI[2],
          };
        }

        //Handling teaching assistants
        let taSplitRes = this.splitAndValidateTextArea(this.tasString);
        if (typeof taSplitRes === "string") {
          this.teacherStringError = taSplitRes;
          return false;
        } else if (taSplitRes.length === 0) {
          this.taStringError = "Teaching assistants must be present.";
          return false;
        } else {
          this.taStringError = "";
        }
        //Putting tas into corresponding array
        for (let i = 0; i < taSplitRes.length; i++) {
          let taAtI = taSplitRes[i].split(","); //Guaranteed two commas per, no need to check again
          this.tas[i] = {
            firstname: taAtI[0],
            lastname: taAtI[1],
            email: taAtI[2],
          };
        }

        //Handling file
        if (this.csvFile === undefined) {
          this.csvFileError = "A csv file must be uploaded";
          return false;
        } else {
          this.csvFileError = "";
        }

        //Handling obligatory tasks
        if (
          this.validateTaskAmount() &&
          this.validateTaskSetAmount() &&
          this.validateTaskSets() &&
          this.validateObligatoryPerSet()
        )
          return true;
      }
    },
    async registerCourse() {
      if (this.validateEverything()) {
        this.insertChosenTasksInArray();
        console.log(this.csvFile);
        let students;
        await getStudentsFromFile(this.csvFile)
          .then((response) => {
            students = response.data;
            console.log(students);
          })
          .catch((e) => {
            console.log(e.response);
          });
        console.log(students);
        if (!this.studentCheck(students)) return;

        let course = {
          year: this.year,
          term: this.term,
          courseCode: this.courseCode,
          courseName: this.courseName,
          teachers: this.teachers,
          tas: this.tas,
          students: students,
          obligatoryTaskAmount: this.taskAmount,
          setOfTasks: this.taskSetAmount,
          tasksInEachSet: this.taskSetsChosenTasks,
          obligatoryPerSet: this.obligatoryPerSet,
        };

        console.log(course);
        console.log("Registering course...");
        registerCourseService(course)
          .then((response) => {
            console.log(response);
          })
          .catch((e) => {
            console.log(e);
          });
        //Check every field, split teacher and ta string into arrays etc
      } else {
        console.log("NOT TRUE");
      }
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

<style scoped>
.error {
  color: red;
}
</style>
