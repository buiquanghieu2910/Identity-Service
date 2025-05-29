<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { ScopeResponse } from '@/model/response/ScopeResponse.ts'
import { hideLoading, showLoading } from '@/service/LoadingService.ts'
import { fetchScopeById } from '@/service/ScopeService.ts'

const props = defineProps<{
  applicationId: string;
  scopeId: string;
}>()

const scope = ref<ScopeResponse>(new ScopeResponse())

const getScope = async () => {
  try {
    showLoading()
    const resp = await fetchScopeById(props.scopeId)
    if (resp?.data) {
      scope.value = resp.data
    }
  } finally {
    hideLoading()
  }
}

onMounted(async () => {
  await getScope()
})

</script>

<template>
  <div>
    <div class="flex flex-col gap-2">
      <label for="name">Name <span class="text-red-700">*</span></label>
      <InputText id="name" v-model="scope.name" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="description">Description</label>
      <InputText id="description" v-model="scope.description" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="resources">Resources</label>
      <div class="flex gap-5">
        <router-link v-for="(item, index) in scope.resources" :key="index"
                     :to="{name: 'application-authorization-resource-detail', params: {applicationId: props.applicationId, resourceId: item.id}}"
                     :class="`text-primary-500 hover:underline`">
          {{ item.name }}
        </router-link>
      </div>
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="permissions">Permissions</label>
      <div class="flex gap-5">
        <router-link v-for="(item, index) in scope.permissions" :key="index"
                     :to="{name: 'application-authorization-permission-detail', params: {applicationId: props.applicationId, permissionId: item.id}}"
                     :class="`text-primary-500 hover:underline`">
          {{ item.name }}
        </router-link>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>