<template>
  <home-admin :courses="courses"></home-admin>
</template>

<script>
import HomeAdmin from "@/components/admin/home/HomeAdmin";
export default {
  name: "HomeAdminView",
  components: { HomeAdmin },
  data() {
    return {
      courses: [
        {
          id: "IDATT2002",
        },
        {
          id: "IDATT2222",
        },
        {
          id: "IDATT2032",
        },
      ],
    };
  },
};
</script>

<style scoped></style>
