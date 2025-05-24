<script setup lang="ts">
import type { ApplicationResponse } from '@/model/response/ApplicationResponse.ts'

const props = defineProps<{
  application: ApplicationResponse;
}>()

</script>

<template>
  <div>
    <div class="flex flex-col gap-2">
      <label for="clientSecret">Client Secret <span class="text-red-700">*</span></label>
      <div class="flex gap-5">
        <InputGroup>
          <Password id="clientSecret" v-model="props.application.clientSecret" :feedback="false" size="large"
                    class="w-full"
                    input-class="w-full" toggleMask />
          <InputGroupAddon>
            <Button size="large" icon="pi pi-copy" severity="secondary" toolti v-tooltip="'Copy to clipboard'" />
          </InputGroupAddon>
        </InputGroup>
        <Button size="large" variant="outlined" label="Regenerate"/>
      </div>

    </div>
  </div>
</template>

<style scoped lang="scss">

</style>