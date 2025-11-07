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
                <v-btn style="margin-left: 5px;" @click="recordAbnormalEventDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>이상 이벤트 기록
                </v-btn>
                <v-dialog v-model="recordAbnormalEventDialog" width="500">
                    <RecordAbnormalEvent
                        @closeDialog="recordAbnormalEventDialog = false"
                        @recordAbnormalEvent="recordAbnormalEvent"
                    ></RecordAbnormalEvent>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="resolveAbnormalEventDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>이상 이벤트 해결 처리
                </v-btn>
                <v-dialog v-model="resolveAbnormalEventDialog" width="500">
                    <ResolveAbnormalEvent
                        @closeDialog="resolveAbnormalEventDialog = false"
                        @resolveAbnormalEvent="resolveAbnormalEvent"
                    ></ResolveAbnormalEvent>
                </v-dialog>
            </div>
            <AbnormalEventHistory @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></AbnormalEventHistory>
            <AbnormalEventAlert @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></AbnormalEventAlert>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>DetectedAt</th>
                        <th>RelatedLogId</th>
                        <th>Symptom</th>
                        <th>Severity</th>
                        <th>Status</th>
                        <th>NotificationSent</th>
                        <th>SystemLogId</th>
                        <th>UserId</th>
                        <th>사용자</th>
                        <th>시스템 로그</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="DetectedAt">{{ val.detectedAt }}</td>
                            <td class="whitespace-nowrap" label="RelatedLogId">{{ val.relatedLogId }}</td>
                            <td class="whitespace-nowrap" label="Symptom">{{ val.symptom }}</td>
                            <td class="whitespace-nowrap" label="Severity">{{ val.severity }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="NotificationSent">{{ val.notificationSent }}</td>
                            <td class="whitespace-nowrap" label="SystemLogId">{{ val.systemLogId }}</td>
                            <td class="whitespace-nowrap" label="UserId">{{ val.userId }}</td>
                            <td class="whitespace-nowrap" label="사용자">
                                <UserId :editMode="editMode" v-model="val.userId"></UserId>
                            </td>
                            <td class="whitespace-nowrap" label="시스템 로그">
                                <SystemLogId :editMode="editMode" v-model="val.systemLogId"></SystemLogId>
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
                        <div style="color:white; font-size:17px; font-weight:700;">AbnormalEvent 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <AbnormalEvent :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">AbnormalEvent 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <String label="AbnormalEventId" v-model="selectedRow.abnormalEventId" :editMode="true"/>
                            <Date label="DetectedAt" v-model="selectedRow.detectedAt" :editMode="true"/>
                            <Number label="RelatedLogId" v-model="selectedRow.relatedLogId" :editMode="true"/>
                            <String label="Symptom" v-model="selectedRow.symptom" :editMode="true"/>
                            <Boolean label="NotificationSent" v-model="selectedRow.notificationSent" :editMode="true"/>
                            <EventSeverity offline label="Severity" v-model="selectedRow.severity" :editMode="true"/>
                            <AbnormalEventStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
                            <SystemLogId offline label="SystemLogId" v-model="selectedRow.systemLogId" :editMode="true"/>
                            <UserId offline label="UserId" v-model="selectedRow.userId" :editMode="true"/>
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
    name: 'abnormalEventGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'abnormalEvents',
        recordAbnormalEventDialog: false,
        resolveAbnormalEventDialog: false,
    }),
    watch: {
    },
    methods:{
        async recordAbnormalEvent(params){
            try{
                var path = "recordAbnormalEvent".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RecordAbnormalEvent 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.recordAbnormalEventDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async resolveAbnormalEvent(params){
            try{
                var path = "resolveAbnormalEvent".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ResolveAbnormalEvent 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.resolveAbnormalEventDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>