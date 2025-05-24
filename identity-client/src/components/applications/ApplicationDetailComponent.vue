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
  { label: 'Settings', content: 'Tab 1 Content', route: 'settings' },
  { label: 'Credentials', content: 'Tab 2 Content', route: 'credentials' },
  { label: 'Authorization', content: 'Tab 3 Content', route: 'authorization' },
  { label: 'Sessions', content: 'Tab 3 Content', route: 'sessions' },
  { label: 'Advanced', content: 'Tab 3 Content', route: 'advanced' },
]);

const application = ref<ApplicationResponse>()

const pathChild = ref('')

onMounted(async () => {
  await getApplication()
})

watch(
  () => route.matched.at(-1)?.path,
  (newPath) => {
    const segments = newPath.split('/')
    pathChild.value = segments[segments.length - 1]
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
      <Tab v-for="(tab, index) in tabs" :key="index" :value="tab.route" @click="() => router.push(`/applications/${props.applicationId}/${tab.route}`)">
        {{ tab.label }}
      </Tab>
    </TabList>
  </Tabs>

  <div class="mt-10">
    <router-view v-if="application" :application="application"/>
  </div>
</template>

<style scoped lang="scss">

</style>