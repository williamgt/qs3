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
    auth: {
      token: {
        type: String,
        default: "",
      },
    },
  },
  getters: {},
  mutations: {
    SET_TOKEN(state, token) {
      state.auth.token = token;
    },
  },
  actions: {
    setToken({ commit }, token) {
      return commit("SET_TOKEN", token);
    },
  },
  modules: {},
});
