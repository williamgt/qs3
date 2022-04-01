<template>
  <user-info :user="user"></user-info>
  <button></button>
</template>

<script>
import UserInfo from "@/components/admin/allUsers/UserInfo";
import { getUser } from "@/services/userService";

export default {
  name: "UserInfoView",
  components: { UserInfo },
  data() {
    return {
      user: {
        type: Object,
      },
    };
  },
  created() {
    const id = this.$route.params.id;
    getUser(id).then((response) => {
      this.user = response.data;
    });
  },
};
</script>

<style scoped></style>
