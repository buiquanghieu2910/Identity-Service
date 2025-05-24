import AppLayout from '@/layout/AppLayout.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: AppLayout,
      children: [
        {
          path: '/applications',
          name: 'applications',
          component: () => import('@/views/pages/applications/ApplicationListView.vue')
        },
        {
          path: '/applications/:id',
          name: 'application-detail',
          component: () => import('@/views/pages/applications/ApplicationDetailView.vue'),
          children: [
            {
              path: 'settings',
              component: () => import('@/views/pages/applications/details/ApplicationSettingView.vue'),
            },
            {
              path: 'credentials',
              component: () => import('@/views/pages/applications/details/ApplicationCredentialView.vue'),
            },
            {
              path: 'authorization',
              component: () => import('@/views/pages/applications/details/ApplicationAuthorizationView.vue'),
            },
            {
              path: 'sessions',
              component: () => import('@/views/pages/applications/details/ApplicationSessionView.vue'),
            },
            {
              path: 'advanced',
              component: () => import('@/views/pages/applications/details/ApplicationAdvancedView.vue'),
            }
          ]
        },
        {
          path: '/users',
          name: 'users',
          component: () => import('@/views/pages/users/UserListView.vue')
        },
        {
          path: '/sessions',
          name: 'sessions',
          component: () => import('@/views/pages/sessions/SessionView.vue')
        },
        {
          path: '/roles',
          name: 'roles',
          component: () => import('@/views/pages/roles/RoleView.vue')
        },
        {
          path: '/settings',
          name: 'settings',
          component: () => import('@/views/pages/settings/SettingView.vue')
        },
        {
          path: '/identity-providers',
          name: 'identity-providers',
          component: () => import('@/views/pages/identity-providers/IdentityProviderView.vue')
        },
      ]
    }
  ]
});

export default router;
