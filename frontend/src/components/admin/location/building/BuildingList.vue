<template>
  <h1 class="header">{{ campus.name }}</h1>
  <div class="table-container">
    <element-header :element="header"></element-header>
    <element-to-list
      v-for="building in campus.buildings"
      :key="building.id"
      :element="building"
      :path="path"
    ></element-to-list>
  </div>
  <div class="button-container">
    <button>Edit Campus</button>
    <button @click="toRegisterBuilding">Register Building</button>
  </div>
</template>

<script>
import ElementHeader from "@/components/admin/location/ElementHeader";
import ElementToList from "@/components/admin/location/ElementToList";
export default {
  name: "BuildingList",
  components: {
    ElementToList,
    ElementHeader,
  },
  props: {
    campus: Object,
  },
  data() {
    return {
      header: {
        st: "ID",
        nd: "Name",
      },
      path: "/locations/building/", //"/building",
    };
  },
  methods: {
    toRegisterBuilding() {
      this.$router.push(
        "/locations/campus/" + this.$route.params.id + "/register"
      );
    },
  },
};
</script>

<style scoped>
.table-container {
  margin-right: 5%;
  margin-left: 5%;
}
.header {
  text-align: center;
}
.button-container {
  margin-top: 10px;
  text-align: center;
}
button {
  margin-left: 10px;
  margin-right: 10px;
}
</style>
