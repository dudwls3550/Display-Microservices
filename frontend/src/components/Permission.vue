<template>
    <div>
        <Number
            label="PermissionId"
            v-model="value.permissionId"
            :editMode="editMode"
        />
        <Date
            label="GrantedAt"
            v-model="value.grantedAt"
            :editMode="editMode"
        />
        <UserId
            offline
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
            @change="change"
        />
        <PermissionType
            offline
            label="PermissionType"
            v-model="value.permissionType"
            :editMode="editMode"
            @change="change"
        />
        <PermissionStatus
            offline
            label="Status"
            v-model="value.status"
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
    name: 'Permission',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "permissions",
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
