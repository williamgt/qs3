<template>
  <room-list :campus="campus"></room-list>
</template>

<script>
import RoomList from "@/components/admin/location/room/RoomList";
import { getBuilding } from "@/services/locationSerivce";
import NProgress from "nprogress";
export default {
  name: "BuildingView",
  components: { RoomList },
  data() {
    return {
      campus: {
        name: "",
        building: {
          name: "",
        },
      },
    };
  },
  async created() {
    NProgress.start();
    await getBuilding(this.$route.params.id)
      .then((response) => {
        console.log(response.data);
        this.campus = response.data;
        NProgress.done();
      })
      .catch((err) => {
        console.log(err.response);
      });
  },
};
</script>

<style scoped></style>
