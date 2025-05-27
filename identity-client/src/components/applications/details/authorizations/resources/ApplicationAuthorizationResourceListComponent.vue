<script setup lang="ts">
import type { SearchParams } from '@/model/Common.ts'
import { PAGE_DEFAULT, PAGE_SIZE_DEFAULT } from '@/constants/Constant.ts'
import { onMounted, ref } from 'vue'
import { hideLoading, showLoading } from '@/service/LoadingService.ts'
import type { ResourceResponse } from '@/model/response/ResourceResponse.ts'
import { fetchResources } from '@/service/ResourceService.ts'
import TagStatus from '@/components/commons/TagStatus.vue'

const props = defineProps<{
  applicationId: string;
}>()

const columns = ref([
    { header: 'Name', field: 'name', dataType: 'link' },
    { header: 'Description', field: 'description', dataType: 'text' }
  ]
)

const paramSearch: SearchParams = {
  page: PAGE_DEFAULT,
  size: PAGE_SIZE_DEFAULT,
  sort: [],
  search: [
    {
      field: 'applicationId',
      value: props.applicationId
    }
  ]
}

const resources = ref<ResourceResponse[]>([])

const loadResources = async () => {
  try {
    showLoading()
    const resp = await fetchResources({ ...paramSearch })
    resources.value = resp?.data || []
  } catch (error) {
  } finally {
    hideLoading()
  }
}

onMounted(async () => {
  await loadResources()
})

</script>

<template>
  <DataTable class="mt-5" :value="resources" :scrollable="true" scrollHeight="500px">
    <Column v-for="(col, index) in columns" :key="index" :header="col.header" :field="col.field">
      <template #body="slotProps">
        <template v-if="col.dataType==='status'">
          <TagStatus :value="slotProps.data[col.field]" />
        </template>
        <template v-else-if="col.dataType==='link'">
          <router-link
            :to="{name: 'application-authorization-resource-detail', params: {applicationId: props.applicationId, resourceId: slotProps.data.id}}"
            :class="`text-primary-500 hover:underline`">
            {{ slotProps.data[col.field] }}
          </router-link>
        </template>
        <template v-else>
          {{ slotProps.data[col.field] }}
        </template>
      </template>
    </Column>
    <template #empty> No data.</template>
  </DataTable>
</template>

<style scoped lang="scss">

</style>