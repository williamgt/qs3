import { createStore } from "vuex";

export default createStore({
  state: {
    navbar: {
      student: {
        navbarElements: [
          { name: "Courses", path: "/courses" },
          { name: "Settings", path: "/settings" },
          { name: "Log out", path: "/logout" },
        ],
      },
    },
  },
  getters: {},
  mutations: {},
  actions: {},
  modules: {},
});
