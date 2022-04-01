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
      admin: [
        { name: "Courses", path: "/courses" },
        { name: "All Users", path: "/users" },
        { name: "Settings", path: "/settings" },
        { name: "Log out", path: "/logout" },
      ],
      current: undefined,
    },
    auth: {
      token: "",
      role: "",
    },
    admin: {},
    personLoggedIn: undefined,
  },
  getters: {},
  mutations: {
    SET_TOKEN(state, token) {
      state.auth.token = token;
    },
    SET_LOGIN(state, person) {
      state.personLoggedIn = person;
    },
    SET_ADMIN_STATUS(state, status) {
      state.auth.admin = status;
    },
    SET_NAVBAR(state, status) {
      state.navbar.current = status;
    },
    SET_ROLE(state, role) {
      state.auth.role = role;
    },
  },
  actions: {
    setToken({ commit }, token) {
      return commit("SET_TOKEN", token);
    },
    setLogin({ commit }, token) {
      return commit("SET_LOGIN", token);
    },
    setAdminStatus({ commit }, status) {
      return commit("SET_ADMIN_STATUS", status);
    },
    setNavbar({ commit }, status) {
      return commit("SET_NAVBAR", status);
    },
    setRole({ commit }, role) {
      return commit("SET_ROLE", role);
    },
  },
  modules: {},
});
