<script setup lang="ts">
const props = defineProps<{
  applicationId: string
}>()
</script>

<template>
  ApplicationAuthorizationScopeView-applicationId: {{ props.applicationId }}
</template>

<style scoped lang="scss">

</style>