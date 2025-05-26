<script setup lang="ts">

import { onMounted, ref, watch } from 'vue'
import type { ApplicationResponse } from '@/model/response/ApplicationResponse.ts'
import { fetchApplicationById } from '@/service/ApplicationService.ts'
import { hideLoading, showLoading } from '@/service/LoadingService.ts'
import { useRoute, useRouter } from 'vue-router'

const props = defineProps<{
  applicationId: string;
}>()

const router = useRouter()
const route = useRoute()

const emit = defineEmits<{
  (e: 'applicationName', payload: string): void
}>()

const tabs = ref([
  { label: 'Settings', route: 'settings' },
  { label: 'Credentials', route: 'credentials' },
  { label: 'Roles', route: 'roles' },
  { label: 'System menu', route: 'system-menu' },
  { label: 'Authorization', route: 'authorization' },
  { label: 'Sessions', route: 'sessions' },
  { label: 'Advanced', route: 'advanced' }
])

const application = ref<ApplicationResponse>()

const pathChild = ref('settings')

onMounted(async () => {
  await getApplication()
})

watch(
  () => route.matched.slice(-1)[0]?.path,
  (newPath) => {
    const segments = newPath.split('/')
    pathChild.value = segments[3]
  },
  { immediate: true }
)

const getApplication = async () => {
  try {
    showLoading()
    const resp = await fetchApplicationById(props.applicationId)
    if (resp?.data) {
      application.value = resp.data
      emit('applicationName', resp.data.name)
    }
  } finally {
    hideLoading()
  }
}

</script>

<template>
  <Tabs :value="pathChild">
    <TabList>
      <Tab v-for="(tab, index) in tabs" :key="index" :value="tab.route"
           @click="() => router.push({ name: `application-${tab.route}`})">
        {{ tab.label }}
      </Tab>
    </TabList>
  </Tabs>

  <div class="mt-5">
    <router-view v-if="application" :application="application" />
  </div>
</template>

<style scoped lang="scss">

</style>