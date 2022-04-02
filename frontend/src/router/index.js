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
import hasAdminAccess from "@/api/AuthAPI";
import AllLocationView from "@/views/admin/location/AllLocationView";
import LocationView from "@/views/admin/location/LocationView";
import AddCampus from "@/views/admin/location/campus/RegisterCampus";
import RegisterRoom from "@/views/admin/location/room/RegisterRoom";

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
    path: "/courses/:id",
    name: "NotFound",
    component: NotFound,
    children: [
      {
        path: "register",
        name: "QueueForm",
        component: QueueForm,
      },
    ],
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
    path: "/course-info",
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
    path: "/locations/all",
    name: "Locations",
    component: AllLocationView,
    meta: {
      requiresLogin: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/locations/location/:id",
    name: "Location",
    component: LocationView,
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
    name: "AddRoom",
    component: RegisterRoom,
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
  return next();
});

router.afterEach(() => {
  NProgress.done();
});

export default router;
