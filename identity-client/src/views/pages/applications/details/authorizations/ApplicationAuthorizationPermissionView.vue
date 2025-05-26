<script setup lang="ts">
const props = defineProps<{
  applicationId: string
}>()
</script>

<template>
  ApplicationAuthorizationPermissionView-applicationId: {{ props.applicationId }}
</template>

<style scoped lang="scss">

</style>