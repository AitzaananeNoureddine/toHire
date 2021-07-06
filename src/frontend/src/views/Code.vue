<template>
  <div class="section-shaped section-lg my-0">
    <h1>welcome to the code component</h1>
    <h3 v-if="params">{{params.code}}</h3>
  </div>
</template>

<script>
export default {
    name: 'Code',
    data() {
        return {
            params,
        }
    },
    created() {
        this.params = this.$route.params;
        console.log(this.params);
    },
}
</script>

<style>

</style>