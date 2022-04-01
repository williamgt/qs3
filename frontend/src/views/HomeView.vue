<template>
  <HomeAdminView v-if="this.admin"></HomeAdminView>
  <t-a-courses-view v-if="this.ta"></t-a-courses-view>
  <t-a-courses-view v-if="this.teacher"></t-a-courses-view>
  <student-courses-view v-if="this.student"></student-courses-view>
</template>

<script>
// @ is an alias to /src
import hasAdminAccess, { hasTAAccess, hasTeacherAccess } from "@/api/AuthAPI";
import HomeAdminView from "@/views/admin/HomeAdminView";
import StudentCoursesView from "@/components/student/StudentCoursesView";
import TACoursesView from "@/components/teaching-assistant/TACoursesView";
import { useStore } from "vuex";

export default {
  name: "HomeView",
  components: {
    TACoursesView,
    StudentCoursesView,
    HomeAdminView,
  },
  methods: {},
  setup() {
    const store = useStore();
    console.log(store.state.auth.role);
    const admin = hasAdminAccess(store.state.auth.role);
    const teacher =
      !hasAdminAccess(store.state.auth.role) &&
      hasTeacherAccess(store.state.auth.role);
    const ta =
      !hasAdminAccess(store.state.auth.role) &&
      !hasTeacherAccess(store.state.auth.role) &&
      hasTAAccess(store.state.auth.role);

    const student =
      !hasAdminAccess(store.state.auth.role) &&
      !hasTeacherAccess(store.state.auth.role) &&
      !hasTAAccess(store.state.auth.role);
    return {
      student,
      admin,
      ta,
      teacher,
    };
  },
};
</script>
