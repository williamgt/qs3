<template>
  <campus-list :campuses="campuses"></campus-list>
</template>

<script>
import CampusList from "@/components/admin/location/campus/CampusList";
import { getCampuses } from "@/services/locationSerivce";
export default {
  name: "AllCampusesView",
  components: { CampusList },
  data() {
    return {
      campuses: undefined,
    };
  },
  async created() {
    await getCampuses()
      .then((response) => {
        this.campuses = response.data;
      })
      .catch(() => {});
  },
};
</script>

<style scoped></style>
