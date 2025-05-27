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
          path: '/applications/:applicationId',
          name: 'application-detail',
          component: () => import('@/views/pages/applications/ApplicationDetailView.vue'),
          redirect: to => {
            const { id } = to.params
            return `/applications/${id}/settings`
          },
          children: [
            {
              path: 'settings',
              component: () => import('@/views/pages/applications/details/ApplicationSettingView.vue'),
              name: 'application-settings'
            },
            {
              path: 'credentials',
              component: () => import('@/views/pages/applications/details/ApplicationCredentialView.vue'),
              name: 'application-credentials'
            },
            {
              path: 'roles',
              component: () => import('@/views/pages/applications/details/ApplicationRoleView.vue'),
              name: 'application-roles'
            },
            {
              path: 'system-menu',
              component: () => import('@/views/pages/applications/details/ApplicationSystemMenuView.vue'),
              name: 'application-system-menu'
            },
            {
              path: 'authorization',
              component: () => import('@/views/pages/applications/details/ApplicationAuthorizationView.vue'),
              name: 'application-authorization',
              redirect: to => {
                const { applicationId } = to.params
                return `/applications/${applicationId}/authorization/resources`
              },
              children: [
                {
                  path: 'resources',
                  component: () => import('@/views/pages/applications/details/authorizations/resources/ApplicationAuthorizationResourceView.vue'),
                  name: 'application-authorization-resources',
                },
                {
                  path: 'resources/:resourceId',
                  component: () => import('@/views/pages/applications/details/authorizations/resources/ApplicationAuthorizationResourceDetailView.vue'),
                  name: 'application-authorization-resource-detail'
                },
                {
                  path: 'scopes',
                  component: () => import('@/views/pages/applications/details/authorizations/ApplicationAuthorizationScopeView.vue'),
                  name: 'application-authorization-scopes'
                },
                {
                  path: 'permissions',
                  component: () => import('@/views/pages/applications/details/authorizations/ApplicationAuthorizationPermissionView.vue'),
                  name: 'application-authorization-permissions'
                }
              ]
            },
            {
              path: 'sessions',
              component: () => import('@/views/pages/applications/details/ApplicationSessionView.vue'),
              name: 'application-sessions'
            },
            {
              path: 'advanced',
              component: () => import('@/views/pages/applications/details/ApplicationAdvancedView.vue'),
              name: 'application-advanced'
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
