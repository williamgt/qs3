import { createRouter, createWebHistory } from "vue-router";
import store from "@/store";
import HomeView from "../views/HomeView.vue";
import NotFound from "@/views/errors/NotFound";
import NetworkError from "@/views/errors/NetworkError";
import NProgress from "nprogress";
import NotAuthorized from "@/views/errors/NotAuthorized";
import LoginPage from "../components/LoginPage.vue";
import QueueForm from "@/views/forms/student/QueueForm";
import StudentCoursesView from "../components/student/StudentCoursesView";
import StudentCourseInfo from "../components/student/StudentCourseInfo";
import TACoursesView from "../components/teaching-assistant/TACoursesView";
import homeAdminView from "@/views/admin/HomeAdminView";
import CourseRegistration from "../components/admin/CourseRegistration";
import AllUsersView from "@/views/admin/user/AllUsersView";
import AllUsersDetailsView from "@/views/admin/user/AllUsersDetailsView";
import EditUserAdminView from "@/views/admin/user/EditUserAdminView";
import UserInfoView from "@/views/admin/user/UserInfoView";
import SettingsView from "@/views/user/SettingsView";
import AddCampus from "@/views/admin/location/campus/RegisterCampus";
import RegisterRoom from "@/views/admin/location/room/RegisterRoom";
import RegisterBuilding from "@/views/admin/location/building/RegisterBuilding";
import EditCampus from "@/views/admin/location/campus/EditCampus";
import EditBuilding from "@/views/admin/location/building/EditBuilding";
import RoomView from "@/views/admin/location/room/RoomView";
import RoomEditView from "@/views/admin/location/room/RoomEditView";
import CourseInfo from "@/components/admin/course/info/CourseInfo";
import InsideQueue from "@/components/queue/InsideQueue";
import ValidateStudentView from "@/views/forms/teaching-assistant/ValidateStudentView";
import CreateUserView from "@/views/admin/user/CreateUserView";
import { hasAdminAccess, hasTAAccess } from "@/api/AuthAPI";

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomeView,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
  {
    path: "/courses/:id/:studQueueId/validate",
    name: "ValidateStudentView",
    component: ValidateStudentView,
    meta: {
      requiresLogin: true,
      requiresTa: true,
    },
  },
  {
    path: "/courses/:id/info",
    name: "NotFound",
    component: CourseInfo,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/courses/:id/register",
    name: "QueueForm",
    component: QueueForm,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/courses/:id/queue",
    name: "InsideQueue",
    component: InsideQueue,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/:catchAll(.*)",
    name: "NotFound",
    component: NotFound,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/404/:resource",
    name: "404Resource",
    component: NotFound,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/network-error",
    name: "NetworkError",
    component: NetworkError,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/401",
    name: "NotAuthorized",
    component: NotAuthorized,
    beforeEnter: (to, from, next) => {
      return next();
    },
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/login",
    name: "Login",
    component: LoginPage,
    // eslint-disable-next-line no-unused-vars
    beforeEnter: (to, from, next) => {
      if (store.state.personLoggedIn !== undefined) {
        return next({ path: "/" });
      }
      return next();
    },
  },
  {
    path: "/logout",
    // eslint-disable-next-line no-unused-vars
    beforeEnter: (to, from, next) => {
      store.state.personLoggedIn = undefined;
      store.state.auth.token = "";
      return next({ name: "Login" });
    },
  },
  {
    path: "/temp",
    name: "Temp",
    component: homeAdminView,
  },
  {
    path: "/courses",
    name: "Courses",
    component: StudentCoursesView,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/courses/:id",
    name: "CourseInfo",
    component: StudentCourseInfo,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/ta-courses",
    name: "TACoursesView",
    component: TACoursesView,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/course-registration",
    name: "CourseRegistration",
    component: CourseRegistration,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/users",
    name: "AllUsers",
    component: AllUsersView,
    props: true,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/users/user/:id",
    name: "User",
    component: UserInfoView,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/settings",
    name: "Settings",
    component: SettingsView,
    meta: {
      requiresLogin: true,
    },
  },
  {
    path: "/users/user/:id/edit",
    name: "UserInfoEdit",
    component: EditUserAdminView,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/users/all",
    name: "AllUsersDetails",
    component: AllUsersDetailsView,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/campus",
    name: "Campuses",
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
    component: () => import("../views/admin/location/campus/AllCampusesView"),
  },
  {
    path: "/locations/campus/:id",
    name: "Campus",
    component: () => import("../views/admin/location/campus/CampusView"),
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/building/:id",
    name: "Building",
    component: () => import("../views/admin/location/building/BuildingView"),
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/campus/register",
    name: "AddCampus",
    component: AddCampus,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/building/:id/register",
    name: "RegisterRoom",
    component: RegisterRoom,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/campus/:id/register",
    name: "RegisterBuilding",
    component: RegisterBuilding,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/campus/:id/edit",
    name: "EditCampus",
    component: EditCampus,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/building/:id/edit",
    name: "EditBuilding",
    component: EditBuilding,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/room/:id",
    name: "RoomView",
    component: RoomView,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/room/:id/edit",
    name: "RoomEdit",
    component: RoomEditView,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/users/user/register",
    name: "CreateUserView",
    component: CreateUserView,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  NProgress.start();
  console.log(store.state.personLoggedIn);
  if (to.matched.some((record) => record.meta.requiresLogin)) {
    console.log(store.state.personLoggedIn);
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (store.state.personLoggedIn === undefined) {
      return next({ name: "Login" });
    }
  }
  if (to.matched.some((record) => record.meta.requiresAdmin)) {
    console.log("here");
    if (!hasAdminAccess(store.state.auth.role)) {
      console.log("Here");
      return next({ path: "/401" });
    }
  }
  if (to.matched.some((record) => record.meta.requiresTa)) {
    if (!hasTAAccess(store.state.auth.role)) {
      return next({ path: "/401" });
    }
  }
  return next();
});

router.afterEach(() => {
  NProgress.done();
});

export default router;
