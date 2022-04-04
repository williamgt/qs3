import { shallowMount } from "@vue/test-utils";
import LoginPage from "@/components/LoginPage";
import store from "@/store";
import router from "@/router";

function mountLogin(config = {}) {
  config.mountOptions = config.mountOptions || {};
  config.plugins = config.plugins || {};
  return shallowMount(LoginPage, {
    global: {
      plugins: [store, router],
    },
    ...config.mountOptions,
  });
}

describe("LoginComponent.vue", () => {
  it("check that LoginComponent renders", () => {
    const loginTitle = "Login";
    const wrapper = mountLogin();
    expect(wrapper.text()).toMatch(loginTitle);
  }),
    it("Wrong username and password == failed", async () => {
      const wrapper = mountLogin();

      await wrapper.setData({ userLogin: { email: "wrong username" } });
      await wrapper.setData({ userLogin: { password: "wrong password" } });
      const button = wrapper.find("#submit-button");
      await button.trigger("click");
      await expect(wrapper.vm.error).toBe("");
    });
});
