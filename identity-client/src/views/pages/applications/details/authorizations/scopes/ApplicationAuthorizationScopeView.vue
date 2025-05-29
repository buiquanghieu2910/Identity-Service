<script setup lang="ts">
import ApplicationAuthorizationScopeListComponent
  from '@/components/applications/details/authorizations/scopes/ApplicationAuthorizationScopeListComponent.vue'

const props = defineProps<{
  applicationId: string
}>()
</script>

<template>
  <ApplicationAuthorizationScopeListComponent :application-id="applicationId"/>
</template>

<style scoped lang="scss">

</style>