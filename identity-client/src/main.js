import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'

import Aura from '@primeuix/themes/aura'
import PrimeVue from 'primevue/config'
import ConfirmationService from 'primevue/confirmationservice'
import ToastService from 'primevue/toastservice'

import '@/assets/styles.scss'
import { definePreset } from '@primeuix/themes'

const app = createApp(App)

app.use(router)
app.use(PrimeVue, {
  theme: {
    preset: definePreset(Aura, {
      semantic: {
        primary: {
          50: '#f5f3ff',
          100: '#ede9fe',
          200: '#ddd6fe',
          300: '#c4b5fd',
          400: '#a78bfa',
          500: '#8b5cf6',
          600: '#7c3aed',
          700: '#6d28d9',
          800: '#5b21b6',
          900: '#4c1d95',
          950: '#2e1065'
        }
      }
    }),
    options: {
      darkModeSelector: '.app-dark'
    }
  }
})
app.use(ToastService)
app.use(ConfirmationService)

app.mount('#app')