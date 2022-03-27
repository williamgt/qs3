import { createRouter, createWebHashHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import NotFound from "@/views/errors/NotFound";
import NetworkError from "@/views/errors/NetworkError";
import NProgress from "nprogress";
import NotAuthorized from "@/views/errors/NotAuthorized";
const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
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
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

router.beforeEach((to, from) => {
  NProgress.start();
  const notAuthorized = false;
  if (notAuthorized) {
    if (from.href) {
      return { path: "/401" };
    } else {
      return { path: "/" };
    }
  }
});

router.afterEach(() => {
  NProgress.done();
});

export default router;
