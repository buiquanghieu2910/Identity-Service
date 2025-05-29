<script setup lang="ts">
import type { SearchParams } from '@/model/Common.ts'
import { PAGE_DEFAULT, PAGE_SIZE_DEFAULT } from '@/constants/Constant.ts'
import { onMounted, ref } from 'vue'
import { hideLoading, showLoading } from '@/service/LoadingService.ts'
import type { PermissionResponse } from '@/model/response/PermissionResponse.ts'
import { fetchPermissions } from '@/service/PermissionService.ts'

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

const permissions = ref<PermissionResponse[]>([])

const getPermissions = async () => {
  try {
    showLoading()
    const resp = await fetchPermissions({ ...paramSearch })
    permissions.value = resp?.data || []
  } catch (error) {
  } finally {
    hideLoading()
  }
}

onMounted(async () => {
  await getPermissions()
})
</script>

<template>
  <DataTable class="mt-5" :value="permissions" :scrollable="true" scrollHeight="500px">
    <Column v-for="(col, index) in columns" :key="index" :header="col.header" :field="col.field">
      <template #body="slotProps">
        <template v-if="col.dataType==='link'">
          <router-link
            :to="{name: 'application-authorization-permission-detail', params: {applicationId: props.applicationId, permissionId: slotProps.data.id}}"
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