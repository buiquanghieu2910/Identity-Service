<script setup lang="ts">
import { useRoute } from 'vue-router'
import ApplicationAuthorizationScopeDetailComponent
  from '@/components/applications/details/authorizations/scopes/ApplicationAuthorizationScopeDetailComponent.vue'

const props = defineProps<{
  applicationId: string;
}>()

const route = useRoute()
const scopeId = route.params.scopeId as string
</script>

<template>
  <ApplicationAuthorizationScopeDetailComponent :scope-id="scopeId" :application-id="applicationId"/>
</template>

<style scoped lang="scss">

</style>