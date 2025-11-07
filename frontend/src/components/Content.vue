<template>
    <div>
        <Number
            label="ContentId"
            v-model="value.contentId"
            :editMode="editMode"
        />
        <Number
            label="OwnerId"
            v-model="value.ownerId"
            :editMode="editMode"
        />
        <String
            label="FileUrl"
            v-model="value.fileUrl"
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
        <Date
            label="DeletedAt"
            v-model="value.deletedAt"
            :editMode="editMode"
        />
        <ContentMetadata
            offline
            label="Metadata"
            v-model="value.metadata"
            :editMode="editMode"
            @change="change"
        />
        <ContentStatus
            offline
            label="Status"
            v-model="value.status"
            :editMode="editMode"
            @change="change"
        />
        <ContentApprovalId
            offline
            label="ContentApprovalId"
            v-model="value.contentApprovalId"
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
    name: 'Content',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "contents",
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
