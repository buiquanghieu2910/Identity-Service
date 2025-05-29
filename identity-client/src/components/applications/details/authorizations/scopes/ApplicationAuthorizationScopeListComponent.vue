<script setup lang="ts">
import { onMounted, ref } from 'vue'
import type { ScopeResponse } from '@/model/response/ScopeResponse.ts'
import { hideLoading, showLoading } from '@/service/LoadingService.ts'
import { fetchScopesByApplicationId } from '@/service/ScopeService.ts'

const props = defineProps<{
  applicationId: string;
}>()

const columns = ref([
    { header: 'Name', field: 'name', dataType: 'link' },
    { header: 'Description', field: 'description', dataType: 'text' }
  ]
)
const scopes = ref<ScopeResponse[]>([])

const getScopes = async () => {
  try {
    showLoading()
    const resp = await fetchScopesByApplicationId(props.applicationId)
    scopes.value = resp?.data || []
  } catch (error) {
  } finally {
    hideLoading()
  }
}

onMounted(async () => {
  await getScopes()
})
</script>

<template>
  <DataTable class="mt-5" :value="scopes" :scrollable="true" scrollHeight="500px">
    <Column v-for="(col, index) in columns" :key="index" :header="col.header" :field="col.field">
      <template #body="slotProps">
        <template v-if="col.dataType==='link'">
          <router-link
            :to="{name: 'application-authorization-scope-detail', params: {applicationId: props.applicationId, scopeId: slotProps.data.id}}"
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