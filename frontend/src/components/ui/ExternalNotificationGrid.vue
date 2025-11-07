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
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="retryNotificationDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>알림 재시도
                </v-btn>
                <v-dialog v-model="retryNotificationDialog" width="500">
                    <RetryNotification
                        @closeDialog="retryNotificationDialog = false"
                        @retryNotification="retryNotification"
                    ></RetryNotification>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="sendExternalNotificationDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>외부 알림 전송
                </v-btn>
                <v-dialog v-model="sendExternalNotificationDialog" width="500">
                    <SendExternalNotification
                        @closeDialog="sendExternalNotificationDialog = false"
                        @sendExternalNotification="sendExternalNotification"
                    ></SendExternalNotification>
                </v-dialog>
            </div>
            <ExternalContentList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ExternalContentList>
            <NotificationLog @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></NotificationLog>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>ContentId</th>
                        <th>ExternalSystemId</th>
                        <th>Channel</th>
                        <th>Status</th>
                        <th>Metadata</th>
                        <th>NotifiedAt</th>
                        <th>CreatedAt</th>
                        <th>UpdatedAt</th>
                        <th>ExternalIntegrationClientId</th>
                        <th>MetadataId</th>
                        <th>콘텐츠</th>
                        <th>메타데이터</th>
                        <th>외부 연동 클라이언트</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="ContentId">{{ val.contentId }}</td>
                            <td class="whitespace-nowrap" label="ExternalSystemId">{{ val.externalSystemId }}</td>
                            <td class="whitespace-nowrap" label="Channel">{{ val.channel }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="Metadata">{{ val.metadata }}</td>
                            <td class="whitespace-nowrap" label="NotifiedAt">{{ val.notifiedAt }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="UpdatedAt">{{ val.updatedAt }}</td>
                            <td class="whitespace-nowrap" label="ExternalIntegrationClientId">{{ val.externalIntegrationClientId }}</td>
                            <td class="whitespace-nowrap" label="MetadataId">{{ val.metadataId }}</td>
                            <td class="whitespace-nowrap" label="콘텐츠">
                                <ContentId :editMode="editMode" v-model="val.contentId"></ContentId>
                            </td>
                            <td class="whitespace-nowrap" label="메타데이터">
                                <MetadataId :editMode="editMode" v-model="val.metadataId"></MetadataId>
                            </td>
                            <td class="whitespace-nowrap" label="외부 연동 클라이언트">
                                <ExternalIntegrationClientId :editMode="editMode" v-model="val.externalIntegrationClientId"></ExternalIntegrationClientId>
                            </td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">ExternalNotification 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <ExternalNotification :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">ExternalNotification 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="NotificationId" v-model="selectedRow.notificationId" :editMode="true"/>
                            <Number label="ExternalSystemId" v-model="selectedRow.externalSystemId" :editMode="true"/>
                            <String label="Metadata" v-model="selectedRow.metadata" :editMode="true"/>
                            <Date label="NotifiedAt" v-model="selectedRow.notifiedAt" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="UpdatedAt" v-model="selectedRow.updatedAt" :editMode="true"/>
                            <ContentId offline label="ContentId" v-model="selectedRow.contentId" :editMode="true"/>
                            <NotificationChannel offline label="Channel" v-model="selectedRow.channel" :editMode="true"/>
                            <NotificationStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
                            <ExternalIntegrationClientId offline label="ExternalIntegrationClientId" v-model="selectedRow.externalIntegrationClientId" :editMode="true"/>
                            <MetadataId offline label="MetadataId" v-model="selectedRow.metadataId" :editMode="true"/>
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
    name: 'externalNotificationGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'externalNotifications',
        retryNotificationDialog: false,
        sendExternalNotificationDialog: false,
    }),
    watch: {
    },
    methods:{
        async retryNotification(params){
            try{
                var path = "retryNotification".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RetryNotification 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.retryNotificationDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async sendExternalNotification(params){
            try{
                var path = "sendExternalNotification".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','SendExternalNotification 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.sendExternalNotificationDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>