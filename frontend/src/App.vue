<template>
  <NavigationBar :navbar-elements="this.navbarElements"></NavigationBar>
  <router-view />
</template>

<style>
* {
  margin: 0;
  padding: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
<script>
import NavigationBar from "@/components/global/NavigationBar";

export default {
  components: { NavigationBar },
  data() {
    return {
      navbarElements: this.$store.state.navbar.student.navbarElements,
    };
  },
};
</script>
