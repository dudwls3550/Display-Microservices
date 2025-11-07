<template>
    <div>
        <Number
            label="ApprovalId"
            v-model="value.approvalId"
            :editMode="editMode"
        />
        <Number
            label="ContentId"
            v-model="value.contentId"
            :editMode="editMode"
        />
        <Number
            label="RequestedBy"
            v-model="value.requestedBy"
            :editMode="editMode"
        />
        <Date
            label="RequestedAt"
            v-model="value.requestedAt"
            :editMode="editMode"
        />
        <ApprovalStatus
            offline
            label="ApprovalStatus"
            v-model="value.approvalStatus"
            :editMode="editMode"
            @change="change"
        />
        <ApprovalHistory
            offline
            label="ApprovalHistory"
            v-model="value.approvalHistory"
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
    name: 'ContentApproval',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "contentApprovals",
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
