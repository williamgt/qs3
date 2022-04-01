<template>
  <all-users :users="this.users"></all-users>
</template>

<script>
import AllUsers from "@/components/admin/allUsers/AllUsers";
import getUsers from "@/api/AllUsersAPI";
import { getAllUsers } from "@/services/userService";
export default {
  name: "AllUsersView",
  components: { AllUsers },
  data() {
    return {
      users: {
        type: Array,
      },
      error: null,
    };
  },
  // beforeRouteEnter(to, from, next) {
  //   getAllUsers((err, users) => {
  //     next((vm) => vm.setData(err, users));
  //   });
  // },
  // // when route changes and this component is already rendered,
  // // the logic will be slightly different.
  // // eslint-disable-next-line no-unused-vars
  // async beforeRouteUpdate(to, from) {
  //   this.users = null;
  //   try {
  //     this.users = await getAllUsers();
  //   } catch (error) {
  //     this.error = error.toString();
  //   }
  // },
  methods: {
    test() {
      getUsers();
    },
  },
  created() {
    getAllUsers().then((response) => {
      this.users = response.data;
    });
  },
};
</script>

<style scoped></style>
