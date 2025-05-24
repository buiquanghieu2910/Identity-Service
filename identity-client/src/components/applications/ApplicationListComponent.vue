<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { fetchApplications } from '@/service/ApplicationService.ts'
import { hideLoading, showLoading } from '@/service/LoadingService.ts'
import type { ApplicationResponse } from '@/model/response/ApplicationResponse.ts'
import type { SearchParams } from '@/model/Common.ts'
import { PAGE_DEFAULT, PAGE_SIZE_DEFAULT } from '@/constants/Constant.ts'
import TagStatus from '@/components/commons/TagStatus.vue'

const columns = ref([
    { header: 'Client ID', field: 'clientId', dataType: 'clientId' },
    { header: 'Name', field: 'name', dataType: 'text' },
    { header: 'Description', field: 'description', dataType: 'text' },
    { header: 'Status', field: 'status', dataType: 'status' }
  ]
)

const paramSearch: SearchParams = {
  page: PAGE_DEFAULT,
  size: PAGE_SIZE_DEFAULT,
  sort: [],
  search: []
}

const applications = ref<ApplicationResponse[]>([])

const loadApplications = async () => {
  try {
    showLoading()
    const resp = await fetchApplications({ ...paramSearch })
    applications.value = resp?.data || []
  } catch (error) {
  } finally {
    hideLoading()
  }
}

onMounted(async () => {
  await loadApplications()
})

</script>

<template>
  <DataTable class="mt-5" :value="applications" :scrollable="true" scrollHeight="500px">
    <Column v-for="(col, index) in columns" :key="index" :header="col.header" :field="col.field">
      <template #body="slotProps">
        <template v-if="col.dataType==='status'">
          <TagStatus :value="slotProps.data[col.field]" />
        </template>
        <template v-else-if="col.dataType==='clientId'">
          <router-link :to="'/applications/'+slotProps.data.id+'/settings'" :class="`text-primary-500 hover:underline`">
            {{ slotProps.data[col.field] }}
          </router-link>
        </template>
        <template v-else>
          {{ slotProps.data[col.field] }}
        </template>
      </template>
    </Column>
  </DataTable>
</template>

<style scoped lang="scss">

</style>