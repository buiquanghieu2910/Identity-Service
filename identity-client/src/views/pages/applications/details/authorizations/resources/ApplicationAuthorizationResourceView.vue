<script setup lang="ts">
import ApplicationAuthorizationResourceListComponent
  from '@/components/applications/details/authorizations/resources/ApplicationAuthorizationResourceListComponent.vue'

const props = defineProps<{
  applicationId: string
}>()
</script>

<template>
  <ApplicationAuthorizationResourceListComponent :application-id="applicationId"/>
</template>

<style scoped lang="scss">

</style>