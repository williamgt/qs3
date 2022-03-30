<template>
  <all-users :users="users"></all-users>
</template>

<script>
import AllUsers from "@/components/admin/allUsers/AllUsers";
export default {
  name: "AllUsersView",
  components: { AllUsers },
  data() {
    return {
      users: [
        { email: "Test@stud.ntnu.no", id: 1 },
        { email: "Test2@stud.ntnu.no", id: 2 },
        { email: "Test3@stud.ntnu.no", id: 3 },
      ],
    };
  },
};
</script>

<style scoped></style>
