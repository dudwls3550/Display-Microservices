<template>
  <v-container fluid>
    <v-row>
      <v-card
          v-for="(card, index) in cards"
          :key="index"
          class="mx-auto"
          style="height:300px; width:300px; margin-bottom:20px;"
          outlined
      >
        <v-list-item>
          <v-icon style="margin-top: 30%; margin-left: 37.5%;" color="primary" size="64">mdi-apps</v-icon>
        </v-list-item>
        <v-card-actions>
          <v-btn
              class="mx-auto"
              outlined
              rounded
              :to="card.link"
              style="font-weight:500; font-size:20px; border:solid 2px; max-width:250px; overflow:hidden; margin-top: 10%;"
          >
              {{ card.text }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'Index',
  data: () => ({
    cards: [
      {
          text: "권한",
          link: "/permissions",
      },
      {
          text: "사용자",
          link: "/users",
      },
      {
          text: "사용자 목록 조회",
          link: "/userLists",
      },
      {
          text: "권한 목록",
          link: "/permissionCatalogs",
      },
      {
          text: "사용자 접근 프로필 조회",
          link: "/userAccessProfiles",
      },
      {
          text: "사용자 상세 정보 조회",
          link: "/userDetails",
      },
      {
          text: "권한 상세 정보",
          link: "/permissionDetails",
      },
      {
          text: "외부 알림",
          link: "/externalNotifications",
      },
      {
          text: "외부 연동 클라이언트",
          link: "/externalIntegrationClients",
      },
      {
          text: "클라이언트 설정 조회",
          link: "/clientConfigurations",
      },
      {
          text: "외부 콘텐츠 목록",
          link: "/externalContentLists",
      },
      {
          text: "알림 기록",
          link: "/notificationLogs",
      },
      {
          text: "클라이언트 목록 조회",
          link: "/clientLists",
      },
      {
          text: "통계",
          link: "/statistics",
      },
      {
          text: "시스템 로그",
          link: "/systemLogs",
      },
      {
          text: "이상 이벤트",
          link: "/abnormalEvents",
      },
      {
          text: "시스템 로그 목록",
          link: "/systemLogLists",
      },
      {
          text: "이상 이벤트 이력 조회",
          link: "/abnormalEventHistories",
      },
      {
          text: "운영 통계 상세 조회",
          link: "/operationalStatistics",
      },
      {
          text: "통계 요약",
          link: "/statisticsSummaries",
      },
      {
          text: "시스템 로그 상세",
          link: "/systemLogDetails",
      },
      {
          text: "이상 이벤트 알림 목록",
          link: "/abnormalEventAlerts",
      },
      {
          text: "메타데이터",
          link: "/metadata",
      },
      {
          text: "콘텐츠",
          link: "/contents",
      },
      {
          text: "승인 프로세스",
          link: "/contentApprovals",
      },
      {
          text: "콘텐츠 목록 조회",
          link: "/contentLists",
      },
      {
          text: "콘텐츠 검색 인덱스",
          link: "/contentSearchIndices",
      },
      {
          text: "승인 대기 목록 조회",
          link: "/pendingApprovalLists",
      },
      {
          text: "콘텐츠 상세 정보",
          link: "/contentDetails",
      },
      {
          text: "승인 이력 조회",
          link: "/approvalHistories",
      },
      {
          text: "메타데이터 요약 목록",
          link: "/metadataSummaries",
      },
    ],
  }),
};
</script>
