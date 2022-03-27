<template>
  <h1>NetworkError</h1>
  <h3>
    It looks like you're experiencing some network issues, please take a breath
    and <a href="#" @click="$router.go(-1)">click here</a> to try again.
  </h3>
</template>

<script>
export default {
  name: "NetworkError",
};
</script>

<style scoped></style>
