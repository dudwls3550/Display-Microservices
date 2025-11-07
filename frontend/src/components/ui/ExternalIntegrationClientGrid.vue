<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="updateClientDetailsDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>클라이언트 상세 정보 수정
                </v-btn>
                <v-dialog v-model="updateClientDetailsDialog" width="500">
                    <UpdateClientDetails
                        @closeDialog="updateClientDetailsDialog = false"
                        @updateClientDetails="updateClientDetails"
                    ></UpdateClientDetails>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="registerClientDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>클라이언트 등록
                </v-btn>
                <v-dialog v-model="registerClientDialog" width="500">
                    <RegisterClient
                        @closeDialog="registerClientDialog = false"
                        @registerClient="registerClient"
                    ></RegisterClient>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="deactivateClientDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>클라이언트 비활성화
                </v-btn>
                <v-dialog v-model="deactivateClientDialog" width="500">
                    <DeactivateClient
                        @closeDialog="deactivateClientDialog = false"
                        @deactivateClient="deactivateClient"
                    ></DeactivateClient>
                </v-dialog>
            </div>
            <ClientConfiguration @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ClientConfiguration>
            <ClientList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ClientList>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>ClientName</th>
                        <th>Type</th>
                        <th>AuthToken</th>
                        <th>Status</th>
                        <th>RegisteredAt</th>
                        <th>UpdatedAt</th>
                        <th>LastAccessedAt</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="ClientName">{{ val.clientName }}</td>
                            <td class="whitespace-nowrap" label="Type">{{ val.type }}</td>
                            <td class="whitespace-nowrap" label="AuthToken">{{ val.authToken }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="RegisteredAt">{{ val.registeredAt }}</td>
                            <td class="whitespace-nowrap" label="UpdatedAt">{{ val.updatedAt }}</td>
                            <td class="whitespace-nowrap" label="LastAccessedAt">{{ val.lastAccessedAt }}</td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">ExternalIntegrationClient 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <ExternalIntegrationClient :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">ExternalIntegrationClient 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="ClientId" v-model="selectedRow.clientId" :editMode="true"/>
                            <String label="ClientName" v-model="selectedRow.clientName" :editMode="true"/>
                            <Date label="RegisteredAt" v-model="selectedRow.registeredAt" :editMode="true"/>
                            <Date label="UpdatedAt" v-model="selectedRow.updatedAt" :editMode="true"/>
                            <Date label="LastAccessedAt" v-model="selectedRow.lastAccessedAt" :editMode="true"/>
                            <ClientType offline label="Type" v-model="selectedRow.type" :editMode="true"/>
                            <AuthToken offline label="AuthToken" v-model="selectedRow.authToken" :editMode="true"/>
                            <ClientStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'externalIntegrationClientGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'externalIntegrationClients',
        updateClientDetailsDialog: false,
        registerClientDialog: false,
        deactivateClientDialog: false,
    }),
    watch: {
    },
    methods:{
        async updateClientDetails(params){
            try{
                var path = "updateClientDetails".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UpdateClientDetails 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.updateClientDetailsDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async registerClient(params){
            try{
                var path = "registerClient".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RegisterClient 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.registerClientDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async deactivateClient(params){
            try{
                var path = "deactivateClient".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','DeactivateClient 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.deactivateClientDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>