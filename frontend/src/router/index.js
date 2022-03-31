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
import AllUsersView from "@/views/admin/AllUsersView";
import AllUsersDetailsView from "@/views/admin/AllUsersDetailsView";

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
  },
  {
    path: "/404/:resource",
    name: "404Resource",
    component: NotFound,
    props: true,
  },
  {
    path: "/network-error",
    name: "NetworkError",
    component: NetworkError,
  },
  {
    path: "/401",
    name: "NotAuthorized",
    component: NotAuthorized,
  },
  {
    path: "/login",
    name: "Login",
    component: LoginPage,
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
  },
  {
    path: "/course-info",
    name: "CourseInfo",
    component: StudentCourseInfo,
  },
  {
    path: "/ta-courses",
    name: "TACoursesView",
    component: TACoursesView,
  },
  {
    path: "/course-registration",
    name: "CourseRegistration",
    component: CourseRegistration,
  },
  {
    path: "/users",
    name: "AllUsers",
    component: AllUsersView,
  },
  {
    path: "/users/all",
    name: "AllUsersDetails",
    component: AllUsersDetailsView,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  NProgress.start();
  console.log(store.state.auth.token);
  console.log(store.state.personLoggedIn);
  console.log(store.state.personLoggedIn.default === undefined);
  if (to.matched.some((record) => record.meta.requiresLogin)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (store.state.personLoggedIn.default === undefined) {
      return next({ name: "Login" });
    } else {
      return next(); // go to wherever I'm going
    }
  }
  return next();
});

router.afterEach(() => {
  NProgress.done();
});

export default router;
