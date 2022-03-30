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
    personLoggedIn: {
      type: Object,
      default: undefined,
    },
  },
  getters: {},
  mutations: {
    SET_TOKEN(state, token) {
      state.auth.token = token;
    },
    SET_LOGIN(state, person) {
      state.personLoggedIn = person;
    },
  },
  actions: {
    setToken({ commit }, token) {
      return commit("SET_TOKEN", token);
    },
    setLogin({ commit }, token) {
      return commit("SET_LOGIN", token);
    },
  },
  modules: {},
});
