<template>
    <div>
        <Number
            label="ClientId"
            v-model="value.clientId"
            :editMode="editMode"
        />
        <String
            label="ClientName"
            v-model="value.clientName"
            :editMode="editMode"
        />
        <Date
            label="RegisteredAt"
            v-model="value.registeredAt"
            :editMode="editMode"
        />
        <Date
            label="UpdatedAt"
            v-model="value.updatedAt"
            :editMode="editMode"
        />
        <Date
            label="LastAccessedAt"
            v-model="value.lastAccessedAt"
            :editMode="editMode"
        />
        <ClientType
            offline
            label="Type"
            v-model="value.type"
            :editMode="editMode"
            @change="change"
        />
        <AuthToken
            offline
            label="AuthToken"
            v-model="value.authToken"
            :editMode="editMode"
            @change="change"
        />
        <ClientStatus
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
    name: 'ExternalIntegrationClient',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "externalIntegrationClients",
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
