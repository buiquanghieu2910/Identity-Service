<script setup lang="ts">
import ApplicationSettingComponent from '@/components/applications/ApplicationDetailComponent.vue'

import { useRoute } from 'vue-router'
import { ref } from 'vue'

const route = useRoute()
const applicationId = route.params.applicationId as string
const applicationName = ref<string>('')

</script>

<template>
  <div class="card">
    <h3>Applications | {{ applicationName }}</h3>
    <ApplicationSettingComponent :application-id="applicationId" @application-name="applicationName = $event" />
  </div>
</template>

<style scoped lang="scss">

</style>