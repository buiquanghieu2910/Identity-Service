<script setup lang="ts">
import ApplicationSessionComponent from '@/components/applications/details/ApplicationSessionComponent.vue'

defineProps(['application'])

</script>

<template>
  <ApplicationSessionComponent :application="application" />
</template>

<style scoped lang="scss">

</style>