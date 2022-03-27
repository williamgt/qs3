<template>
  <h1>404</h1>
  <h2>Page not found</h2>
  <h3>The {{ resource }} you're looking for is not here.</h3>
  <router-link :to="{ name: 'home' }">Back to the home page</router-link>
</template>

<script>
export default {
  name: "404NotFound",
  props: {
    resource: {
      type: String,
      required: true,
      default: "page",
    },
  },
};
</script>

<style scoped></style>
