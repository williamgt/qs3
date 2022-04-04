<template>
  <HomeAdminView id="admin" v-if="this.admin"></HomeAdminView>
  <t-a-courses-view
    id="teacher-ta"
    v-if="this.teacher || this.ta"
  ></t-a-courses-view>
  <student-courses-view id="student" v-if="this.student"></student-courses-view>
</template>

<script>
// @ is an alias to /src
import { hasAdminAccess, hasTAAccess, hasTeacherAccess } from "@/api/AuthAPI";
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
