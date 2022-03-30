<template>
  <user-element
    v-for="user in users"
    :user="user"
    :key="user.id"
  ></user-element>
</template>

<script>
import UserElement from "@/components/user/UserElement";
export default {
  name: "AllUsers",
  components: { UserElement },
  props: {
    users: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped></style>
