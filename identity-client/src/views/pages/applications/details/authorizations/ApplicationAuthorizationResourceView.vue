<script setup lang="ts">
const props = defineProps<{
  applicationId: string
}>()
</script>

<template>
  ApplicationAuthorizationResourceView-applicationId: {{ props.applicationId }}
</template>

<style scoped lang="scss">

</style>