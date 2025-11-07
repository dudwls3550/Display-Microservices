<template>
    <div>
        <Number
            label="NotificationId"
            v-model="value.notificationId"
            :editMode="editMode"
        />
        <Number
            label="ExternalSystemId"
            v-model="value.externalSystemId"
            :editMode="editMode"
        />
        <String
            label="Metadata"
            v-model="value.metadata"
            :editMode="editMode"
        />
        <Date
            label="NotifiedAt"
            v-model="value.notifiedAt"
            :editMode="editMode"
        />
        <Date
            label="CreatedAt"
            v-model="value.createdAt"
            :editMode="editMode"
        />
        <Date
            label="UpdatedAt"
            v-model="value.updatedAt"
            :editMode="editMode"
        />
        <ContentId
            offline
            label="ContentId"
            v-model="value.contentId"
            :editMode="editMode"
            @change="change"
        />
        <NotificationChannel
            offline
            label="Channel"
            v-model="value.channel"
            :editMode="editMode"
            @change="change"
        />
        <NotificationStatus
            offline
            label="Status"
            v-model="value.status"
            :editMode="editMode"
            @change="change"
        />
        <ExternalIntegrationClientId
            offline
            label="ExternalIntegrationClientId"
            v-model="value.externalIntegrationClientId"
            :editMode="editMode"
            @change="change"
        />
        <MetadataId
            offline
            label="MetadataId"
            v-model="value.metadataId"
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
    name: 'ExternalNotification',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "externalNotifications",
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
