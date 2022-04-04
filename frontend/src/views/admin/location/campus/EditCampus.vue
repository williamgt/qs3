<template>
  <base-display label="ID" :message="campus.id"></base-display>
  <base-edit-display
    label="Campus name"
    v-model="this.name"
    :message="this.name"
    :holder="this.campus.name"
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
import { getCampus, updateCampus } from "@/services/locationSerivce";
import BaseButton from "@/components/input-components/BaseButton";
import BaseEditDisplay from "@/components/input-components/BaseEditDisplay";

export default {
  name: "EditCampus",
  components: { BaseEditDisplay, BaseButton, BaseDisplay },
  data() {
    return {
      campus: {
        type: Object,
      },
      error: "",
      name: "",
    };
  },
  inject: ["GStore"],
  async created() {
    await getCampus(this.$route.params.id)
      .then((response) => {
        this.campus = response.data;
        this.name = this.campus.name;
      })
      .catch((err) => {
        console.log(err.response);
      });
  },
  methods: {
    validateInput() {
      if (this.campus.name.length === 0) {
        this.error = "Name can't be empty";
        return false;
      }
      if (this.campus.name === this.name) {
        this.error = "Can't update to same name";
        return false;
      }
      this.campus.name = this.name;
      return true;
    },
    cancel() {
      this.$router.push("/locations/campus/" + this.campus.id);
    },
    submit() {
      if (this.validateInput()) {
        const camp = { campusId: this.campus.id, campusName: this.campus.name };
        updateCampus(camp)
          .then(() => {
            this.GStore.flashMessage = "Campus Edited!";
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            this.$router.push("/locations/campus/" + this.campus.id);
          })
          .catch((err) => {
            console.log(err.response);
            this.error = err;
            this.GStore.flashMessage = "Problems Editing campus";
            setTimeout(() => {
              this.GStore.flashMessage = "";
            }, 3000);
            this.$router.push("/locations/campus/" + this.campus.id);
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
