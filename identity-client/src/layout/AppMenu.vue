<script setup>
import { ref } from 'vue'

import AppMenuItem from './AppMenuItem.vue'

const model = ref([
  {
    label: 'Manage',
    items: [
      { label: 'Applications', icon: '', to: '/applications' },
      { label: 'Users', icon: '', to: '/users' },
      { label: 'Sessions', icon: '', to: '/sessions' },
      { label: 'Roles', icon: '', to: '/roles' },
    ]
  },
  {
    label: 'Configure',
    items: [
      { label: 'Settings', icon: '', to: '/settings' },
      { label: 'Identity providers', icon: '', to: '/identity-providers' },
    ]
  }
])
</script>

<template>
  <ul class="layout-menu">
    <template v-for="(item, i) in model" :key="item">
      <app-menu-item v-if="!item.separator" :item="item" :index="i"></app-menu-item>
      <li v-if="item.separator" class="menu-separator"></li>
    </template>
  </ul>
</template>

<style lang="scss" scoped></style>
