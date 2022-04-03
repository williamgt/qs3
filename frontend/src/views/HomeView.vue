<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <router-link to="/users">users</router-link>
    <router-link to="/temp">temp</router-link>
    <HelloWorld msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "HomeView",
  components: {
    HelloWorld,
  },
};
</script>
