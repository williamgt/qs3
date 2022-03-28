<template>
  <div class="info-container">
    <div class="title">
      <h2>Information page - {{ courseName }}</h2>
    </div>
    <div>
      <h3>Status: {{ status }}</h3>
    </div>
    <div class="task-info-container">
      <div class="generalities">
        <h4 class="generalities-element">Task</h4>
        |
        <h4 class="generalities-element">Result</h4>
        |
        <h4 class="generalities-element">Comment</h4>
      </div>
      <div class="task-container">
        <ul class="task-list">
          <li v-for="task in tasks" :key="task.taskNr" class="element">
            <p class="element-task-nr">Task {{ task.taskNr }}</p>
            |
            <p class="element-task-status" v-if="task.status === 'Validated'">
              &#x2714; {{ task.status }}
            </p>
            <p
              class="element-task-status"
              v-if="task.status === 'Not validated'"
            >
              &#215; {{ task.status }}
            </p>

            |
            <p class="element-task-comment">{{ task.comment }}</p>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "StudentCourseInfo",
  data() {
    return {
      courseName: "",
      status: "",
      tasks: [
        {
          taskNr: 1,
          status: "Validated",
          comment: "V good job actually",
        },
        {
          taskNr: 2,
          status: "Not validated",
          comment: "",
        },
      ],
    };
  },
  methods: {
    getTasks() {
      //Do an api call to get the logged on students current progress, maybe look at beforeEnter in router
      return 0;
    },
  },
};
</script>

<style scoped>
.generalities-element {
  display: inline-block;
}
.task-list {
  list-style-type: none;
}
.element {
  list-style-position: inside;
  border: 1px solid black;
}
/*.element p {
  display: inline-block;
}*/
/*Might wanna do something about this, does not scale well with long comments currently*/
.element-task-nr {
  display: inline-block;
}
.element-task-status {
  display: inline-block;
}
.element-task-comment {
  display: inline-block;
}
</style>
