import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/permissions',
      component: () => import('../components/ui/PermissionGrid.vue'),
    },
    {
      path: '/users',
      component: () => import('../components/ui/UserGrid.vue'),
    },
    {
      path: '/externalNotifications',
      component: () => import('../components/ui/ExternalNotificationGrid.vue'),
    },
    {
      path: '/externalIntegrationClients',
      component: () => import('../components/ui/ExternalIntegrationClientGrid.vue'),
    },
    {
      path: '/statistics',
      component: () => import('../components/ui/StatisticsGrid.vue'),
    },
    {
      path: '/systemLogs',
      component: () => import('../components/ui/SystemLogGrid.vue'),
    },
    {
      path: '/abnormalEvents',
      component: () => import('../components/ui/AbnormalEventGrid.vue'),
    },
    {
      path: '/metadata',
      component: () => import('../components/ui/MetadataGrid.vue'),
    },
    {
      path: '/contents',
      component: () => import('../components/ui/ContentGrid.vue'),
    },
    {
      path: '/contentApprovals',
      component: () => import('../components/ui/ContentApprovalGrid.vue'),
    },
  ],
})

export default router;
