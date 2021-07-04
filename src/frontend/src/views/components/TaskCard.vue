<template>
  <div class="shadow px-5 pt-3 pb-5 taskCardBg rounded">
    <div class="flex justify-between">
      <p class="font-semibold font-sans tracking-wide text-sm wht">{{task.title}}</p>

      <img
        class="w-6 h-6 rounded-full ml-3"
        src="https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50"
        alt="Avatar"
      >
    </div>
    <div class="flex mt-4 justify-between items-center">
      <span class="text-sm text-600 wht">{{task.dueOn}}</span>
      <tag v-if="task.tagName" :color="task.tagColor">{{task.tagName}}</tag>
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
    task: {},
  },
  created() {
    const dueOn = new Date(this.task.dueOn);
    this.task.dueOn = dueOn.toDateString();
  },
};
</script>
<style scoped>
    .taskCardBg{
        /* background: #172b4d; */
        background: #7898CE;
    }
    .wht{
      color: white !important;
    }
</style>

