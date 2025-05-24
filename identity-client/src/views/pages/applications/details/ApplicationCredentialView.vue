<script setup lang="ts">
import ApplicationCredentialComponent from '@/components/applications/details/ApplicationCredentialComponent.vue'

defineProps(['application'])

</script>

<template>
  <ApplicationCredentialComponent :application="application" />
</template>

<style scoped lang="scss">

</style>