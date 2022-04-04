<template>
  <base-display label="ID" :message="this.campus.building.id"></base-display>
  <base-edit-display
    label="Building name"
    v-model="this.name"
    :message="this.name"
    :holder="this.campus.building.name"
  ></base-edit-display>
  <div class="submit-button">
    <br v-if="error === ''" />
    <br v-if="error === ''" />
    <p class="error">{{ this.error }}</p>
    <base-button type="submit" @click="cancel">Cancel</base-button>
    <base-button type="submit" @click="submit">Save</base-button>
  </div>
</template>

<script>
import BaseDisplay from "@/components/input-components/BaseDisplay";
import { getBuilding, updateBuilding } from "@/services/locationSerivce";
import BaseButton from "@/components/input-components/BaseButton";
import BaseEditDisplay from "@/components/input-components/BaseEditDisplay";

export default {
  name: "EditBuilding",
  components: { BaseEditDisplay, BaseButton, BaseDisplay },
  data() {
    return {
      campus: {
        type: Object,
        building: {
          id: -1,
        },
      },
      error: "",
      name: "",
    };
  },
  inject: ["GStore"],
  async created() {
    await getBuilding(this.$route.params.id)
      .then((response) => {
        console.log(response.data);
        this.campus = response.data;
        this.name = this.campus.building.name;
      })
      .catch((err) => {
        console.log(err.response);
      });
  },
  methods: {
    validateInput() {
      if (this.campus.building.name.length === 0) {
        this.error = "Name can't be empty";
        return false;
      }
      if (this.campus.building.name === this.name) {
        this.error = "Can't update to same name";
        return false;
      }
      this.campus.building.name = this.name;
      return true;
    },
    cancel() {
      this.$router.push("/locations/building/" + this.campus.building.id);
    },
    submit() {
      if (this.validateInput()) {
        console.log(this.campus);
        const building = {
          buildingId: this.campus.building.id,
          buildingName: this.campus.building.name,
        };
        updateBuilding(building)
          .then(() => {
            this.GStore.flashMessage = "building Edited!";
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            this.$router.push("/locations/building/" + building.buildingId);
          })
          .catch((err) => {
            console.log(err.response);
            this.error = err;
            this.GStore.flashMessage = "Problems Editing building";
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            this.$router.push("/locations/building/" + building.buildingId);
          });
      }
    },
  },
};
</script>

<style scoped>
.header {
  text-align: center;
}
.error {
  color: red;
}
</style>
