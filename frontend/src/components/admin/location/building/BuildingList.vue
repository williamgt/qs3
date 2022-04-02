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
</style>
