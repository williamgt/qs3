<template>
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
export default {
  name: "AllUsers",
  // eslint-disable-next-line vue/no-unused-components
  components: { UserElementHeader, UserElement },
  props: {
    users: {
      type: Array,
      required: true,
    },
  },
  beforeRouteLeave() {},
};
</script>

<style scoped>
.table-container {
  text-align: center;
  margin-left: 5%;
  margin-right: 5%;
}
</style>
