<script setup lang="ts">
import ApplicationSettingComponent from '@/components/applications/ApplicationDetailComponent.vue'

import { useRoute } from 'vue-router'
import { ref } from 'vue'

const route = useRoute()
const id = route.params.id as string
const applicationName = ref<string>('')

</script>

<template>
  <div class="card">
    <h3>Applications | {{ applicationName }}</h3>
    <ApplicationSettingComponent :application-id="id" @application-name="applicationName = $event" />
  </div>
</template>

<style scoped lang="scss">

</style>