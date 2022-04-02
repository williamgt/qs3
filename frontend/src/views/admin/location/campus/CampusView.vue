<template>
  <building-list :campus="campus"></building-list>
</template>

<script>
import { getCampus } from "@/services/locationSerivce";
import BuildingList from "@/components/admin/location/building/BuildingList";
export default {
  name: "CampusView",
  components: { BuildingList },
  data() {
    return {
      campus: {
        name: "",
      },
    };
  },
  async created() {
    await getCampus(this.$route.params.id).then((response) => {
      this.campus = response.data;
    });
  },
};
</script>
<style scoped></style>
