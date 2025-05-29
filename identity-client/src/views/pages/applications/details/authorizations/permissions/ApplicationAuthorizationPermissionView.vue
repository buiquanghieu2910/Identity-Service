<script setup lang="ts">
import ApplicationAuthorizationPermissionListComponent
  from '@/components/applications/details/authorizations/permissions/ApplicationAuthorizationPermissionListComponent.vue'

const props = defineProps<{
  applicationId: string
}>()
</script>

<template>
  <ApplicationAuthorizationPermissionListComponent :application-id="applicationId"/>
</template>

<style scoped lang="scss">

</style>