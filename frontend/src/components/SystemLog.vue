<template>
    <div>
        <Number
            label="LogId"
            v-model="value.logId"
            :editMode="editMode"
        />
        <Date
            label="EventTimestamp"
            v-model="value.eventTimestamp"
            :editMode="editMode"
        />
        <Number
            label="Level"
            v-model="value.level"
            :editMode="editMode"
        />
        <String
            label="Description"
            v-model="value.description"
            :editMode="editMode"
        />
        <Date
            label="CreatedAt"
            v-model="value.createdAt"
            :editMode="editMode"
        />
        <Date
            label="ExpiredAt"
            v-model="value.expiredAt"
            :editMode="editMode"
        />
        <LogType
            offline
            label="EventType"
            v-model="value.eventType"
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
        <ContentId
            offline
            label="ContentId"
            v-model="value.contentId"
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
    name: 'SystemLog',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "systemLogs",
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
