<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '사용자 관리' }" />
        <VerticalNavLink
            :item="{
                title: '권한',
                to: '/permissions',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '사용자',
                to: '/users',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '사용자 목록 조회',
                to: '/userLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '권한 목록',
                to: '/permissionCatalogs',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '사용자 접근 프로필 조회',
                to: '/userAccessProfiles',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '사용자 상세 정보 조회',
                to: '/userDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '권한 상세 정보',
                to: '/permissionDetails',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '외부 연동' }" />
        <VerticalNavLink
            :item="{
                title: '외부 알림',
                to: '/externalNotifications',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '외부 연동 클라이언트',
                to: '/externalIntegrationClients',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '클라이언트 설정 조회',
                to: '/clientConfigurations',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '외부 콘텐츠 목록',
                to: '/externalContentLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '알림 기록',
                to: '/notificationLogs',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '클라이언트 목록 조회',
                to: '/clientLists',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '시스템 모니터링' }" />
        <VerticalNavLink
            :item="{
                title: '통계',
                to: '/statistics',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '시스템 로그',
                to: '/systemLogs',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '이상 이벤트',
                to: '/abnormalEvents',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '시스템 로그 목록',
                to: '/systemLogLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '이상 이벤트 이력 조회',
                to: '/abnormalEventHistories',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '운영 통계 상세 조회',
                to: '/operationalStatistics',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '통계 요약',
                to: '/statisticsSummaries',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '시스템 로그 상세',
                to: '/systemLogDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '이상 이벤트 알림 목록',
                to: '/abnormalEventAlerts',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '콘텐츠 관리' }" />
        <VerticalNavLink
            :item="{
                title: '메타데이터',
                to: '/metadata',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '콘텐츠',
                to: '/contents',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '승인 프로세스',
                to: '/contentApprovals',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '콘텐츠 목록 조회',
                to: '/contentLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '콘텐츠 검색 인덱스',
                to: '/contentSearchIndices',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '승인 대기 목록 조회',
                to: '/pendingApprovalLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '콘텐츠 상세 정보',
                to: '/contentDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '승인 이력 조회',
                to: '/approvalHistories',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '메타데이터 요약 목록',
                to: '/metadataSummaries',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '프론트 엔드' }" />
    </ul>
</template>
