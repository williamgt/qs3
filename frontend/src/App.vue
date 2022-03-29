<template>
  <NavigationBar :navbar-elements="this.navbarElements"></NavigationBar>
  <router-view />
</template>
<style src="@vueform/multiselect/themes/default.css">
body {
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
button,
[type="button"],
[type="reset"],
[type="submit"] {
  -webkit-appearance: none;
}
button::-moz-focus-inner,
[type="button"]::-moz-focus-inner,
[type="reset"]::-moz-focus-inner,
[type="submit"]::-moz-focus-inner {
  border-style: none;
  padding: 0;
}
button:-moz-focusring,
[type="button"]:-moz-focusring,
[type="reset"]:-moz-focusring,
[type="submit"]:-moz-focusring {
  outline: 2px solid #39b982;
}
button {
  display: inline-flex;
  align-items: center;
  justify-content: space-between;
  height: 52px;
  padding: 0 40px;
  background: transparent;
  border: none;
  border-radius: 6px;
  text-align: center;
  font-weight: 600;
  white-space: nowrap;
  transition: all 0.2s linear;
}
button:hover {
  -webkit-transform: scale(1.02);
  transform: scale(1.02);
  box-shadow: 0 7px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
button:active {
  -webkit-transform: scale(1);
  transform: scale(1);
  box-shadow: none;
}
button:focus {
  outline: 0;
}
button:disabled {
  -webkit-transform: scale(1);
  transform: scale(1);
  box-shadow: none;
}
.button + .button {
  margin-left: 1em;
}
button.-fill-gradient {
  background: linear-gradient(to right, #16c0b0, #84cf6a);
  color: #ffffff;
}
button.-fill-gray {
  background: rgba(0, 0, 0, 0.5);
  color: #ffffff;
}
button.-size-small {
  height: 32px;
}
button.-icon-right {
  text-align: left;
  padding: 0 20px;
}
.button.-icon-right > .icon {
  margin-left: 10px;
}
button.-icon-left {
  text-align: right;
  padding: 0 20px;
}
button.-icon-left > .icon {
  margin-right: 10px;
}
button.-icon-center {
  padding: 0 20px;
}
.button-accept {
  background-color: limegreen;
}
.button-decline {
  background-color: orangered;
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
  beforeCreate() {
    document.querySelector("body").setAttribute("style", "margin:0px");
  },
};
</script>
