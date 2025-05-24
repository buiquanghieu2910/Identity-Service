<script setup lang="ts">
import type { ApplicationResponse } from '@/model/response/ApplicationResponse.ts'

const props = defineProps<{
  application: ApplicationResponse;
}>()

</script>

<template>
  <h5>General settings</h5>
  <div>
    <div class="flex flex-col gap-2">
      <label for="clientId">Client ID <span class="text-red-700">*</span></label>
      <InputText id="clientId" v-model="props.application.clientId" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="name">Name</label>
      <InputText id="name" v-model="props.application.name" size="large" class="w-full" />
    </div>
  </div>

  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="description">Description</label>
      <InputText id="description" v-model="props.application.description" size="large" class="w-full" />
    </div>
  </div>

  <h5 class="mt-8">Access settings</h5>
  <div class="mt-5">
    <div class="flex flex-col gap-2">
      <label for="redirectUris">Valid redirect URIs</label>
      <div class="flex gap-5" v-for="(uri, index) in application.redirectUris" :key="index">
        <InputText
          id="redirectUris"
          size="large"
          class="w-full"
          v-model="application.redirectUris[index]"
        />
        <div class="flex items-center justify-center">
          <Button icon="pi pi-minus" severity="danger" rounded :disabled="application.redirectUris?.length <=1"
                  style="height: 2rem !important; width: 2rem !important;" />
        </div>
      </div>
    </div>

    <div class="mt-5">
      <div class="flex flex-col gap-2">
        <label for="webOrigins">Web origins</label>
        <div class="flex gap-5">
          <InputText
            v-for="(uri, index) in application.webOrigins"
            :key="index"
            id="webOrigins"
            size="large"
            class="w-full columns-10"
            v-model="application.webOrigins[index]"
          />
          <div class="flex items-center justify-center">
            <Button icon="pi pi-minus" severity="danger" rounded :disabled="application.webOrigins?.length <=1"
                    style="height: 2rem !important; width: 2rem !important;" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>