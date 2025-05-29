<script setup lang="ts">
import { useRoute } from 'vue-router'
import ApplicationAuthorizationPermissionDetailComponent
  from '@/components/applications/details/authorizations/permissions/ApplicationAuthorizationPermissionDetailComponent.vue'

const props = defineProps<{
  applicationId: string
}>()

const route = useRoute()
const permissionId = route.params.permissionId as string
</script>

<template>
  <ApplicationAuthorizationPermissionDetailComponent :permission-id="permissionId" :application-id="applicationId"/>
</template>

<style scoped lang="scss">

</style>