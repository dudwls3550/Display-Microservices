<template>
    <div>
        <String
            label="AbnormalEventId"
            v-model="value.abnormalEventId"
            :editMode="editMode"
        />
        <Date
            label="DetectedAt"
            v-model="value.detectedAt"
            :editMode="editMode"
        />
        <Number
            label="RelatedLogId"
            v-model="value.relatedLogId"
            :editMode="editMode"
        />
        <String
            label="Symptom"
            v-model="value.symptom"
            :editMode="editMode"
        />
        <Boolean
            label="NotificationSent"
            v-model="value.notificationSent"
            :editMode="editMode"
        />
        <EventSeverity
            offline
            label="Severity"
            v-model="value.severity"
            :editMode="editMode"
            @change="change"
        />
        <AbnormalEventStatus
            offline
            label="Status"
            v-model="value.status"
            :editMode="editMode"
            @change="change"
        />
        <SystemLogId
            offline
            label="SystemLogId"
            v-model="value.systemLogId"
            :editMode="editMode"
            @change="change"
        />
        <UserId
            offline
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
            @change="change"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'AbnormalEvent',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "abnormalEvents",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
