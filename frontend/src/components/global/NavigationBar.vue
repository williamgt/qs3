<template>
  <div class="navbar">
    <div class="navbar-text">
      <nav>
        <div class="">
          <div class="dropdown">
            <button class="dropbtn">
              Dropdown
              <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-content">
              <router-link
                v-for="(element, i) in navbarElements"
                :to="element.path"
                :key="i"
                >{{ element.name }}</router-link
              >
            </div>
          </div>
        </div>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavigationBar",
  props: {
    navbarElements: {
      type: [],
      required: true,
    },
  },
};
</script>

<style scoped>
.navbar {
  overflow: hidden;
  background-color: cornflowerblue;
  font-family: Arial;
}
.navbar-text {
  text-align: right;
}
.dropdown {
  float: right;
  overflow: hidden;
  /*background: url("src/assets/hamburger-bar.png");*/
  background-color: darkblue;
}

.dropdown .dropbtn {
  font-size: 20px;
  border: none;
  outline: none;
  color: white;
  padding: 20px 24px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover,
.dropdown:hover .dropbtn {
  background-color: midnightblue;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
  width: 100%;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}

nav {
  background-color: cornflowerblue;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
