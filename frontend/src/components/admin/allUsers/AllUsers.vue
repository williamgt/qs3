<template>
  <div class="button-container">
    <base-button type="submit" @click="registerUser"
      >Create new User</base-button
    >
  </div>
  <div class="table-container">
    <user-element-header id="header"></user-element-header>
    <user-element
      v-for="user in users"
      :user="user"
      :key="user.id"
    ></user-element>
  </div>
</template>

<script>
import UserElement from "@/components/user/UserElement";
import UserElementHeader from "@/components/user/UserElementHeader";
import BaseButton from "@/input-components/BaseButton";
export default {
  name: "AllUsers",
  // eslint-disable-next-line vue/no-unused-components
  components: { BaseButton, UserElementHeader, UserElement },
  props: {
    users: {
      type: Array,
      required: true,
    },
  },
  methods: {
    registerUser() {
      this.$router.push("/users/user/register");
    },
  },
};
</script>

<style scoped>
.table-container {
  text-align: center;
  margin-left: 5%;
  margin-right: 5%;
}
.button-container {
  text-align: center;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
