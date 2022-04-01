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
  methods: {
    test() {
      getUsers();
    },
  },
  created() {
    getAllUsers()
      .then((response) => {
        this.users = response.data;
      })
      .catch((error) => {
        console.log(error);
        console.log(error.response.data);
        if (
          error.response.data.status === 403 ||
          error.response.data.status === 401
        ) {
          ///return this.$router.push({ path: "/401" });
        }
        if (error.response.data.status === 404) {
          //this.$router.push({ path: "/404", params: 123 });
          //return;
        }
        //this.$router.push("/network-error");
      });
  },
};
</script>

<style scoped></style>
