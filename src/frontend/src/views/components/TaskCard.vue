<template>
  <div class="shadow px-5 pt-3 pb-5 taskCardBg rounded">
    <div class="flex justify-between">
      <p class="text-gray-700 font-semibold font-sans tracking-wide text-sm">{{task.title}}</p>

      <img
        class="w-6 h-6 rounded-full ml-3"
        src="https://pickaface.net/gallery/avatar/unr_sample_161118_2054_ynlrg.png"
        alt="Avatar"
      >
    </div>
    <div class="flex mt-4 justify-between items-center">
      <span class="text-sm text-gray-600">{{task.date}}</span>
      <tag v-if="task.type" :color="tagColor">{{task.type}}</tag>
    </div>
  </div>
</template>
<script>
import Tag from "./Tag.vue";
export default {
  components: {
    Tag
  },
  props: {
    task: {
      type: Object,
      default: () => ({})
    }
  },
  computed: {
    tagColor() {
      const mappings = {
        Design: "purple",
        "Feature Request": "teal",
        Backend: "blue",
        QA: "green",
        default: "teal"
      };
      return mappings[this.task.type] || mappings.default;
    }
  }
};
</script>
<style scoped>
    .taskCardBg{
        background: #C3D0E9;
    }
</style>
