<template>
  <div class="subtitle">
    <h3>{{ active ? "Active" : "Inactive" }}</h3>
  </div>
  <div class="courses">
    <ul class="list">
      <li v-for="course in c_courses" :key="course.courseCode" class="element">
        <div class="course-info-container">
          <h4 @click="courseClicked(course, active)">
            {{ course.courseName }} - {{ course.courseCode }}
          </h4>
          <button @click="courseTasksClicked(course, active)">Tasks</button>
          <button @click="courseQueueClicked(course, active)">Queue</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { ref } from "vue";
import { useStore } from "vuex";
import { getActiveCourses, getInactiveCourses } from "@/services/courseService";

export default {
  name: "StudentCourses",
  emits: ["course-clicked", "course-tasks-clicked", "course-queue-clicked"],
  data() {
    return {
      courses: [],
    };
  },
  props: {
    active: {
      type: Boolean,
      required: true,
    },
  },
  methods: {
    //Emits which course was clicked to the parent component
    courseClicked(course, active) {
      this.$emit("course-clicked", { course: course, active: active });
    },
    courseTasksClicked(course, active) {
      this.$emit("course-tasks-clicked", {
        course: course,
        active: active,
      });
    },
    courseQueueClicked(course, active) {
      this.$emit("course-queue-clicked", { course: course, active: active });
    },
  },
  async setup(props) {
    let c_courses;
    const store = useStore();
    if (props.active) {
      await getActiveCourses(store.getters.getPersonLoggedIn.id)
        .then((response) => {
          c_courses = ref(response.data);
          console.log(c_courses);
        })
        .catch((e) => {
          console.log(e);
        });
    } else {
      await getInactiveCourses(store.getters.getPersonLoggedIn.id)
        .then((response) => {
          c_courses = ref(response.data);
          console.log(c_courses);
        })
        .catch((e) => {
          console.log(e);
        });
    }
    return {
      c_courses,
    };
  },
};
</script>

<style scoped>
.list {
  list-style-type: none;
}
.element {
  list-style-position: inside;
  border: 1px solid black;
}
</style>
