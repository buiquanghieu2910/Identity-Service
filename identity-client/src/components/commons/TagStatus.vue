<script setup lang="ts">
import { Status } from '@/model/enumable/Status.ts'

const props = defineProps<{
  value: Status;
}>()
</script>

<template>
  <Tag severity="success" :value="props.value" v-if="[Status.ACTIVE].includes(value)"></Tag>
  <Tag severity="danger" :value="props.value" v-if="[Status.IN_ACTIVE].includes(value)"></Tag>
</template>

<style scoped lang="scss">

</style>