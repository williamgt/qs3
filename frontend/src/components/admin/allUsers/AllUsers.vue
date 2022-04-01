<template>
  <user-element-header></user-element-header>
  <user-element
    v-for="user in users"
    :user="user"
    :key="user.id"
  ></user-element>
</template>

<script>
import UserElement from "@/components/user/UserElement";
import UserElementHeader from "@/components/user/UserElementHeader";
export default {
  name: "AllUsers",
  components: { UserElementHeader, UserElement },
  props: {
    users: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped></style>
