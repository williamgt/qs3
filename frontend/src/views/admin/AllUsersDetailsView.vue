<template>
  <all-users-details :users="users"></all-users-details>
</template>

<script>
import AllUsersDetails from "@/components/admin/allUsers/AllUsersDetails";
export default {
  name: "AllUsersDetailsView",
  components: { AllUsersDetails },
  data() {
    return {
      users: [
        { email: "Test@stud.ntnu.no", id: 1, password: "123" },
        { email: "Test2@stud.ntnu.no", id: 2, password: "123" },
        { email: "Test3@stud.ntnu.no", id: 3, password: "123" },
      ],
    };
  },
};
</script>

<style scoped></style>
