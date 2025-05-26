<script setup lang="ts">
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import type { ApplicationResponse } from '@/model/response/ApplicationResponse.ts'

const props = defineProps<{
  application: ApplicationResponse
}>()
const router = useRouter()
const route = useRoute()
const pathChild = ref('resources')

watch(
  () => route.matched.slice(-1)[0]?.path,
  (newPath) => {
    const segments = newPath.split('/')
    pathChild.value = segments[4]
  },
  { immediate: true }
)


const tabs = ref([
  { label: 'Resources', route: 'resources' },
  { label: 'Scopes', route: 'scopes' },
  { label: 'Permissions', route: 'permissions' }
])
</script>

<template>
  <Tabs :value="pathChild">
    <TabList>
      <Tab v-for="(tab, index) in tabs" :key="index" :value="tab.route"
           @click="() => router.push({name: `application-authorization-${tab.route}` })">
        {{ tab.label }}
      </Tab>
    </TabList>
  </Tabs>

  <div class="mt-5">
    <router-view :application-id="application.id"/>
  </div>
</template>

<style scoped lang="scss">

</style>