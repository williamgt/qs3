<template>
  <div class="course-container">
    <h3>{{ active ? "Active" : "Inactive" }}</h3>
    <ul class="course-list">
      <li v-for="course in c_courses" :key="course.hashId" class="element">
        <router-link
          :to="'/courses/' + course.hashId + '/queue'"
          :is="!active ? 'span' : 'router-link'"
        >
          <div class="course-item">
            <h4>{{ course.courseName }} - {{ course.courseCode }}</h4>
          </div>
        </router-link>
        <div class="course-item">
          <div class="course-item-center">
            <button @click="activateOrDeactivate(course, active)">
              {{ active ? "Deactivate" : "Activate" }}
            </button>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import {
  taGetActiveQueues,
  taGetInactiveQueues,
} from "@/services/queueServices";
import { ref } from "vue";
import { useStore } from "vuex";

export default {
  name: "TACourses",
  props: {
    active: {
      type: Boolean,
      required: true,
    },

    courses: {
      type: Array,
      required: true,
    },
  },
  methods: {
    activateOrDeactivate(course, active) {
      this.$emit("changed-active-status", {
        course: course,
        active: active,
      });
    },
  },
  async setup(props) {
    console.log(props.active);
    const store = useStore();
    let c_courses;
    if (props.active) {
      await taGetActiveQueues(store.state.personLoggedIn.id)
        .then((response) => {
          c_courses = ref(response.data);
          console.log(c_courses);
        })
        .catch((e) => {
          console.log(e);
        });
    } else if (!props.active) {
      await taGetInactiveQueues(store.state.personLoggedIn.id)
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
button {
  align-items: center;
  justify-content: space-between;
  height: 52px;
  text-align: center;
  font-weight: 600;
  transition: all 0.2s linear;
  background-color: dodgerblue;
  padding: 20px;
  max-width: 100%;
}
.course-container {
  margin-left: 5%;
  margin-right: 5%;
}
.course-list {
  list-style-type: none;
  padding-left: 0;
}
.element {
  display: grid;
  list-style-position: inside;
  border: 1px solid black;
  grid-template-columns: 70% 30%;
}
.element * {
}
.course-item {
  text-align: left;
}
.course-item-center {
  text-align: center;
}
</style>
