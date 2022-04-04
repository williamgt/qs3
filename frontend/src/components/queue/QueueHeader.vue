<template>
  <div class="grid-container">
    <div class="grid-item">Last name</div>
    <div class="grid-item">Room</div>
    <div class="grid-item">Type</div>
    <div class="grid-item">Tasks</div>
  </div>
</template>

<script>
export default {
  name: "QueueHeader",
};
</script>

<style scoped>
@media (max-width: 960px) {
  .header {
    font-size: 32px;
    text-align: center;
  }
  .grid-container {
    display: grid;
    grid-template-columns: 25% 25% 25% 25%;
    background-color: grey;
    width: 100%;
  }
  .grid-item {
    background-color: rgba(255, 255, 255, 0.8);
    border: 1px solid rgba(0, 0, 0, 0.8);
    padding-bottom: 20px;
    padding-top: 20px;
    font-size: 20px;
    text-align: center;
    overflow: hidden;
    max-width: 100%;
    width: 100%;
  }
}
@media (min-width: 960px) {
  .header {
    font-size: 32px;
    text-align: center;
  }
  .grid-container {
    display: grid;
    grid-template-columns: 25% 25% 25% 25%;
    background-color: grey;
    width: 100%;
    /*border-radius: 10px 10px 0 0;*/
  }
  .grid-item {
    background-color: rgba(255, 255, 255, 0.8);
    border: 1px solid rgba(0, 0, 0, 0.8);
    padding-bottom: 20px;
    padding-top: 20px;
    font-size: 24px;
    text-align: center;
    overflow: hidden;
    max-width: 100%;
    width: 100%;
  }
}
</style>
