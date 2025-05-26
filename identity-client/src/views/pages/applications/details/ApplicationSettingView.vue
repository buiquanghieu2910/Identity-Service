<script setup lang="ts">
import ApplicationSettingComponent from '@/components/applications/details/ApplicationSettingComponent.vue'

defineProps(['application'])

</script>

<template>
  <ApplicationSettingComponent :application="application" />
</template>

<style scoped lang="scss">

</style>