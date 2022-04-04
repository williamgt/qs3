<template>
  <user-info :user="user"></user-info>
  <div class="button-container">
    <button @click="toEdit">Edit</button>
  </div>
</template>

<script>
import UserInfo from "@/components/admin/allUsers/UserInfo";
import { deleteUser, getUser } from "@/services/userService";

export default {
  name: "UserInfoView",
  components: { UserInfo },
  data() {
    return {
      user: undefined,
    };
  },
  methods: {
    toEdit() {
      this.$router.push({ name: "UserInfoEdit", params: this.user.id });
    },
    deleteUser() {
      if (
        confirm("Do you really want to delete user: " + this.user.email + "?")
      ) {
        deleteUser(this.user)
          .then(() => {
            this.$router.push({ path: "/users" });
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
  async created() {
    this.user = getUser(this.$route.params.id).then((response) => {
      this.user = response.data;
    });
  },
};
</script>

<style scoped>
.button-container {
  text-align: center;
}
</style>
