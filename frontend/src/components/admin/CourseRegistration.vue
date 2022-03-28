<template>
  <div class="register-container">
    <div class="header">
      <h2>Register course</h2>
    </div>
    <div class="register-form">
      <form>
        <div class="teacher-input">
          <DynamicTextInput></DynamicTextInput>
        </div>
        <div class="teacher-assistant-input">
          <DynamicTextInput></DynamicTextInput>
        </div>
        <div class="file-uploader">
          <label for="file">Choose a .csv file of students to register</label>
          <input type="file" id="file" accept="text/csv" />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import DynamicTextInput from "@/input-components/DynamicTextInput";
export default {
  name: "CourseRegistration",
  components: {
    DynamicTextInput,
  },
};
</script>

<style scoped></style>
