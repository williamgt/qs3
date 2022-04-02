<template>
  <h1 class="header">Campuses</h1>
  <div class="table-container">
    <element-header :element="header"></element-header>
    <element-to-list
      v-for="campus in campuses"
      :key="campus.id"
      :element="campus"
      :path="path"
    ></element-to-list>
    <div class="button-container">
      <button></button>
    </div>
  </div>
</template>

<script>
import ElementHeader from "@/components/admin/location/ElementHeader";
import ElementToList from "@/components/admin/location/ElementToList";
export default {
  name: "CampusList",
  components: {
    ElementToList,
    ElementHeader,
  },
  props: {
    campuses: Array,
  },
  data() {
    return {
      header: {
        st: "ID",
        nd: "Name",
      },
      path: "campus/",
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
.button-container {
  text-align: center;
}
</style>
