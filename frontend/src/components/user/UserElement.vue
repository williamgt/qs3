<template>
  <router-link :to="'/users/user/' + this.user.id" class="link">
    <div class="grid-container">
      <div class="grid-item">{{ user.id }}</div>
      <div class="grid-item">{{ user.email }}</div>
      <div class="grid-item">{{ user.firstname }}</div>
      <div class="grid-item">{{ user.lastname }}</div>
    </div>
  </router-link>
</template>

<script>
export default {
  name: "UserElement",
  props: {
    user: {
      type: Object,
      required: true,
      email: {
        type: String,
        required: true,
      },
      id: {
        type: Number,
        required: true,
      },
      firstName: {
        type: String,
        required: true,
      },
      lastName: {
        type: String,
        required: true,
      },
      password: {
        type: String,
        required: true,
      },
    },
  },
};
</script>

<style scoped>
link {
  text-decoration: none;
  display: inline-block;
  border-bottom: none;
}
.grid-container {
  display: grid;
  grid-template-columns: 25% 25% 25% 25%;
  width: 100%;
}
.grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 1px solid rgba(0, 0, 0, 0.8);
  padding: 20px;
  font-size: 24px;
  text-align: center;
  overflow: hidden;
  max-width: 100%;
  text-decoration: none;
  display: inline-block;
  color: black;
}
.grid-item-text {
  border: none;
  outline-width: 0;
}
a:link,
a:visited,
a:hover,
a:active {
  text-decoration: none;
  color: #f00;
}
</style>
