<template>
  <base-display label="ID" :message="user.id"></base-display>
  <base-display label="First name" :message="user.firstname"></base-display>
  <base-display label="Last name" :message="user.lastname"></base-display>
  <base-display label="Email" :message="user.email"></base-display>
</template>

<script>
import BaseDisplay from "@/components/input-components/BaseDisplay";

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
