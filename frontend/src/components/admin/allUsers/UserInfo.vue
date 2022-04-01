<template>
  <base-display label="ID" :message="user.id"></base-display>
  <base-display label="First name" :message="user.firstName"></base-display>
  <base-display label="Last name" :message="user.lastName"></base-display>
  <base-display label="Email" :message="user.email"></base-display>
  <base-display label="Password" :message="user.password"></base-display>
</template>

<script>
import BaseDisplay from "@/input-components/BaseDisplay";

export default {
  name: "UserInfo",
  components: { BaseDisplay },
  props: {
    user: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped></style>
