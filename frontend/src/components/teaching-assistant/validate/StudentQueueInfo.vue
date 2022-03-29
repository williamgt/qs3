<template>
  <h2>Info</h2>
  <base-display-no-border
    label="Group"
    :message="JSON.stringify(group)"
  ></base-display-no-border>
  <base-display-text-area
    label="Message"
    :message="message"
  ></base-display-text-area>
  <base-display-no-border
    label="Type"
    :message="getType()"
  ></base-display-no-border>
</template>

<script>
import BaseDisplayNoBorder from "@/input-components/BaseDisplayNoBorder";
import BaseDisplayTextArea from "@/input-components/BaseDisplayTextArea";
export default {
  name: "StudentQueueInfo",
  components: { BaseDisplayTextArea, BaseDisplayNoBorder },
  props: {
    group: {
      type: [],
      default: [],
    },
    message: {
      type: String,
      default: "",
    },
    validate: {
      type: Boolean,
      default: true,
    },
  },
  methods: {
    getType() {
      if (this.validate) return "Validate";
      return "Help";
    },
  },
};
</script>

<style scoped>
.message-display {
  padding: 20px;
  font-size: 20px;
  text-align: left;
  border-bottom-color: black;
  border-bottom: solid;
}
.display-container {
  clear: both;
  font-size: 24px;
  font-weight: bold;
  display: grid;
  grid-template-columns: auto auto;
  padding: 10px;
}
.label-display {
  padding: 20px;
  font-size: 24px;
  text-align: left;
  border-bottom-color: black;
  border-bottom: solid;
}
</style>
