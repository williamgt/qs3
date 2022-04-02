<template>
  <h2>Campuses</h2>
  <campus-element-header></campus-element-header>
  <campus-element
    v-for="campus in campuses"
    :key="campus.id"
    :campus="campus"
  ></campus-element>
</template>

<script>
import CampusElement from "@/components/admin/location/campus/CampusElement";
import CampusElementHeader from "@/components/admin/location/campus/CampusElementHeader";
export default {
  name: "CampusList",
  components: { CampusElementHeader, CampusElement },
  props: {
    campuses: Array,
  },
};
</script>

<style scoped></style>
