<script setup lang="ts">

import { useRoute } from 'vue-router'
import ApplicationAuthorizationResourceDetailComponent
  from '@/components/applications/details/authorizations/resources/ApplicationAuthorizationResourceDetailComponent.vue'

const props = defineProps<{
  applicationId: string;
}>()

const route = useRoute()
const resourceId = route.params.resourceId as string


</script>

<template>
   <ApplicationAuthorizationResourceDetailComponent :resource-id="resourceId" :application-id="applicationId"/>
</template>

<style scoped lang="scss">

</style>