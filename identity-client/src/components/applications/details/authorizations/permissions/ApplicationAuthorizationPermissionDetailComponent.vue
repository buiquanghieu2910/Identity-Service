<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { hideLoading, showLoading } from '@/service/LoadingService.ts'
import { PermissionResponse } from '@/model/response/PermissionResponse.ts'
import { fetchPermissionById } from '@/service/PermissionService.ts'
import { IdNameResponse } from '@/model/Common.ts'
import { fetchIdNamesByResourceId } from '@/service/ScopeService.ts'
import { fetchIdNamesByApplicationId as fetchRoleIdNamesByApplicationId } from '@/service/RoleService.ts'

const props = defineProps<{
  applicationId: string;
  permissionId: string;
}>()

const permission = ref<PermissionResponse>(new PermissionResponse())
const scopeIdNames = ref<IdNameResponse[]>([])
const roleIdNames = ref<IdNameResponse[]>([])

const getScopeIdNames = async (resourceId: string) => {
  try {
    showLoading()
    const resp = await fetchIdNamesByResourceId(resourceId)
    if (resp?.data) {
      scopeIdNames.value = resp.data || []
    }
  } finally {
    hideLoading()
  }
}

const getRoleIdNames = async () => {
  try {
    showLoading()
    const resp = await fetchRoleIdNamesByApplicationId(props.applicationId)
    if (resp?.data) {
      roleIdNames.value = resp.data || []
    }
  } finally {
    hideLoading()
  }
}

const getScope = async () => {
  try {
    showLoading()
    const resp = await fetchPermissionById(props.permissionId)
    if (resp?.data) {
      permission.value = resp.data
    }
  } finally {
    hideLoading()
  }
}

onMounted(async () => {
  await getScope()
  await getRoleIdNames()
  await getScopeIdNames(permission.value.resourceId)
})

</script>

<template>
  <div>
    <div class="flex flex-col gap-2">
      <label for="name">Name <span class="text-red-700">*</span></label>
      <InputText id="name" v-model="permission.name" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="description">Description</label>
      <InputText id="description" v-model="permission.description" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="scopes">Authorization scopes <span class="text-red-700">*</span></label>
      <MultiSelect size="large" v-model="permission.scopeIds" :options="scopeIdNames" option-label="name"
                   option-value="id" filter placeholder="Select scopes"
                   :maxSelectedLabels="5" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="roles">Roles</label>
      <MultiSelect size="large" v-model="permission.roleIds" :options="roleIdNames" option-label="name"
                   option-value="id" filter placeholder="Select roles"
                   :maxSelectedLabels="5" class="w-full" />
    </div>
  </div>

</template>

<style scoped lang="scss">

</style>