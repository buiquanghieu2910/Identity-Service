<script setup lang="ts">
import { hideLoading, showLoading } from '@/service/LoadingService.ts'
import { fetchResourceById } from '@/service/ResourceService.ts'
import { onMounted, ref } from 'vue'
import { ResourceResponse } from '@/model/response/ResourceResponse.ts'
import { IdNameResponse } from '@/model/Common.ts'
import { fetchIdNamesByApplicationId } from '@/service/ScopeService.ts'

const props = defineProps<{
  resourceId: string;
  applicationId: string;
}>()

const scopeIdNames = ref<IdNameResponse[]>([])
const resource = ref<ResourceResponse>(new ResourceResponse())

const getScopeIdNames = async () => {
  try {
    showLoading()
    const resp = await fetchIdNamesByApplicationId(props.applicationId)
    if (resp?.data) {
      scopeIdNames.value = resp.data || []
    }
  } finally {
    hideLoading()
  }
}

const getResource = async () => {
  try {
    showLoading()
    const resp = await fetchResourceById(props.resourceId)
    if (resp?.data) {
      resource.value = resp.data
    }
  } finally {
    hideLoading()
  }
}

onMounted(async () => {
  await getScopeIdNames()
  await getResource()
})
</script>

<template v-if="resource">
  <div>
    <div class="flex flex-col gap-2">
      <label for="name">Name <span class="text-red-700">*</span></label>
      <InputText id="name" v-model="resource.name" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="description">Description</label>
      <InputText id="description" v-model="resource.description" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="uris">URIs</label>
      <div class="flex gap-5" v-for="(uri, index) in resource.uris" :key="index">
        <InputText
          id="uris"
          size="large"
          class="w-full"
          v-model="resource.uris[index]"
        />
        <div class="flex items-center justify-center">
          <Button size="small" icon="pi pi-minus" severity="danger" rounded
                  :disabled="resource.uris?.length <=1"
                  style="height: 1.5rem !important; width: 1.5rem !important;"
                  @click="resource.uris.splice(index, 1)" />
        </div>
      </div>
      <div class="mt-1">
        <Button icon="pi pi-plus" label="Add URI" variant="text"
                @click="resource.uris.push('')" />
      </div>
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="scopes">Authorization scopes</label>
      <MultiSelect size="large" v-model="resource.scopeIds" :options="scopeIdNames" option-label="name" option-value="id" filter placeholder="Select scopes"
                   :maxSelectedLabels="5" class="w-full" />
      </div>
  </div>

  <div class="flex gap-5 mt-10 button-fixed-bottom-end">
    <Button label="Save" />
    <Button variant="text" label="Revert" />
  </div>
</template>

<style scoped lang="scss">

</style>