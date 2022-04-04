import { createStore } from "vuex";

export default createStore({
  state: {
    validateStud: Object,
    navbar: {
      student: {
        navbarElements: [
          { name: "Courses", path: "/courses" },
          { name: "Settings", path: "/settings" },
          { name: "Log out", path: "/logout" },
        ],
      },
      admin: [
        { name: "Add course", path: "/course-registration" },
        { name: "All Users", path: "/users" },
        { name: "Locations", path: "/locations/campus" },
        { name: "Settings", path: "/settings" },
        { name: "Log out", path: "/logout" },
      ],
      teacher: [
        { name: "Courses", path: "/courses" },
        { name: "Settings", path: "/settings" },
        { name: "Log out", path: "/logout" },
      ],
      ta: [
        { name: "Courses", path: "/courses" },
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
    inQueue: false,
  },
  getters: {
    getPersonLoggedIn(state) {
      return state.personLoggedIn;
    },
  },
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
    SET_VALIDATE_STUD(state, stud) {
      state.validateStud = stud;
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
    setValidateStud({ commit }, stud) {
      return commit("SET_VALIDATE_STUD", stud);
    },
  },
  modules: {},
});
