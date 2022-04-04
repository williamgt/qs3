import { shallowMount } from "@vue/test-utils";

import HomeView from "@/views/HomeView";
import store from "@/store";
import router from "@/router";

function mountHome(config = {}) {
  config.mountOptions = config.mountOptions || {};
  config.plugins = config.plugins || {};
  return shallowMount(HomeView, {
    global: {
      plugins: [store, router],
    },
    ...config.mountOptions,
  });
}

describe("Home", () => {
  it("Check for correct home screen when admin", () => {
    const wrapper = mountHome();
    store.dispatch("setRole", "ADMIN");
    const component = wrapper.find("#admin");
    expect(component).toBeDefined;
  });
  it("Check for correct home screen when student", () => {
    store.dispatch("setRole", "STUDENT");
    const wrapper = mountHome();
    const component = wrapper.find("#student");
    expect(component).toBeDefined;
  });
  it("Check for correct home screen when teacher/ta", () => {
    const wrapper = mountHome();
    store.dispatch("setRole", "TEACHER");
    const component = wrapper.find("#teacher-ta");
    expect(component).toBeDefined;
  });
  it("Check for correct home screen when teacher/ta", () => {
    const wrapper = mountHome();
    store.dispatch("setRole", "TA");
    const component = wrapper.find("#teacher-ta");
    expect(component).toBeDefined;
  });
});
