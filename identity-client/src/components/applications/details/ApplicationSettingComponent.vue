<script setup lang="ts">
import { ref, watch } from 'vue'
import { ApplicationResponse } from '@/model/response/ApplicationResponse.ts'

const props = defineProps<{
  application: ApplicationResponse;
}>()

const applicationClone = ref(new ApplicationResponse(props.application))

const cloneApplication = (source: ApplicationResponse) => {
  applicationClone.value = new ApplicationResponse({ ...source })
}

watch(
  () => props.application,
  (newVal) => {
    cloneApplication(newVal)
  },
  { deep: true }
)

</script>

<template v-if="applicationClone">
  <h5>General settings</h5>
  <div>
    <div class="flex flex-col gap-2">
      <label for="clientId">Client ID <span class="text-red-700">*</span></label>
      <InputText id="clientId" v-model="applicationClone.clientId" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="name">Name</label>
      <InputText id="name" v-model="applicationClone.name" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="description">Description</label>
      <InputText id="description" v-model="applicationClone.description" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="description">Status</label>
      <ToggleSwitch v-model="applicationClone.statusCheckbox" />
    </div>
  </div>

  <h5 class="mt-8">Access settings</h5>
  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="redirectUris">Valid redirect URIs</label>
      <div class="flex gap-5" v-for="(uri, index) in applicationClone.redirectUris" :key="index">
        <InputText
          id="redirectUris"
          size="large"
          class="w-full"
          v-model="applicationClone.redirectUris[index]"
        />
        <div class="flex items-center justify-center">
          <Button size="small" icon="pi pi-minus" severity="danger" rounded
                  :disabled="applicationClone.redirectUris?.length <=1"
                  style="height: 1.5rem !important; width: 1.5rem !important;"
                  @click="applicationClone.redirectUris.splice(index, 1)" />
        </div>
      </div>
    </div>
    <div class="mt-1">
      <Button icon="pi pi-plus" label="Add valid redirect URIs" variant="text"
              @click="applicationClone.redirectUris.push('')" />
    </div>

    <div class="mt-5">
      <div class="flex flex-col gap-2">
        <label for="webOrigins">Web origins</label>
        <div class="flex gap-5" v-for="(uri, index) in applicationClone.webOrigins" :key="index">
          <InputText
            id="redirectUris"
            size="large"
            class="w-full"
            v-model="applicationClone.webOrigins[index]"
          />
          <div class="flex items-center justify-center">
            <Button size="small" icon="pi pi-minus" severity="danger" rounded
                    :disabled="applicationClone.webOrigins?.length <=1"
                    style="height: 1.5rem !important; width: 1.5rem !important;"
                    @click="applicationClone.webOrigins.splice(index, 1)" />
          </div>
        </div>
      </div>
      <div class="mt-1">
        <Button icon="pi pi-plus" label="Add web origins" variant="text"
                @click="applicationClone.webOrigins.push('')" />
      </div>
    </div>
  </div>

  <div class="flex gap-5 mt-10 button-fixed-bottom-end">
    <Button label="Save" />
    <Button variant="text" label="Revert" @click="cloneApplication(props.application)" />
  </div>
</template>

<style scoped lang="scss">

</style>