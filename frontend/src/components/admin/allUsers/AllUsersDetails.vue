<template>
  <user-detail-element
    v-for="user in users"
    :user="user"
    :key="user.id"
  ></user-detail-element>
</template>

<script>
import UserDetailElement from "@/components/user/UserDetailElement";
export default {
  name: "AllDetailsUsers",
  components: { UserDetailElement },
  props: {
    users: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped></style>
