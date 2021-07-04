<template>
  <div
    class="px-3 h-6 rounded-full text-xs font-semibold flex items-center"
    :class="`bg-100 text-700`" :style="`background-color: ${color};color:white`"
  >
    <span class="w-2 h-2 rounded-full mr-1" :class="`bg-400`" :style="`background-color: #D2DDEF`"></span>
    <span>
      <slot></slot>
    </span>
  </div>
</template>
<script>
export default {
  props: {
    color: {
      type: String,
      default: "green"
    }
  }
};
</script>
