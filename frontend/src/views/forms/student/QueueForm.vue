<template>
  <h1>Lokasjon</h1>
  <base-checkbox
    label="Jobber hjemme"
    :model-value="location.home"
  ></base-checkbox>
  <div class="base-select-container">
    <base-select
      :disabled="location.home"
      label="Campus"
      :options="this.location.campus"
    ></base-select>
    <base-select
      :disabled="location.home"
      label="Room"
      :options="this.location.rooms"
    ></base-select>
    <base-select
      :disabled="location.home"
      label="Table"
      :options="this.location.table"
    ></base-select>
  </div>
</template>

<script>
import BaseSelect from "@/input-components/BaseSelect";
import BaseCheckbox from "@/input-components/BaseCheckbox";
export default {
  name: "QueueForm",
  components: { BaseCheckbox, BaseSelect },
  data() {
    return {
      location: {
        home: false,
        campus: ["Gløshaugen", "Dragvoll", "Kalvskinnet"],
        rooms: ["A4-112", "Thildekontoret", "S7"],
        table: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      },
    };
  },
};
</script>
<style scoped>
.base-select-container select {
  pointer-events: none;
}
</style>
