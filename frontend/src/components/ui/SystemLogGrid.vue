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
                <v-btn style="margin-left: 5px;" @click="generateSystemLogDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>시스템 로그 생성
                </v-btn>
                <v-dialog v-model="generateSystemLogDialog" width="500">
                    <GenerateSystemLog
                        @closeDialog="generateSystemLogDialog = false"
                        @generateSystemLog="generateSystemLog"
                    ></GenerateSystemLog>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="deleteSystemLogDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>시스템 로그 삭제
                </v-btn>
                <v-dialog v-model="deleteSystemLogDialog" width="500">
                    <DeleteSystemLog
                        @closeDialog="deleteSystemLogDialog = false"
                        @deleteSystemLog="deleteSystemLog"
                    ></DeleteSystemLog>
                </v-dialog>
            </div>
            <SystemLogList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></SystemLogList>
            <SystemLogDetail @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></SystemLogDetail>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>EventType</th>
                        <th>EventTimestamp</th>
                        <th>UserId</th>
                        <th>Level</th>
                        <th>Description</th>
                        <th>CreatedAt</th>
                        <th>ExpiredAt</th>
                        <th>ContentId</th>
                        <th>사용자</th>
                        <th>콘텐츠</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="EventType">{{ val.eventType }}</td>
                            <td class="whitespace-nowrap" label="EventTimestamp">{{ val.eventTimestamp }}</td>
                            <td class="whitespace-nowrap" label="UserId">{{ val.userId }}</td>
                            <td class="whitespace-nowrap" label="Level">{{ val.level }}</td>
                            <td class="whitespace-nowrap" label="Description">{{ val.description }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="ExpiredAt">{{ val.expiredAt }}</td>
                            <td class="whitespace-nowrap" label="ContentId">{{ val.contentId }}</td>
                            <td class="whitespace-nowrap" label="사용자">
                                <UserId :editMode="editMode" v-model="val.userId"></UserId>
                            </td>
                            <td class="whitespace-nowrap" label="콘텐츠">
                                <ContentId :editMode="editMode" v-model="val.contentId"></ContentId>
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
                        <div style="color:white; font-size:17px; font-weight:700;">SystemLog 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <SystemLog :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">SystemLog 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="LogId" v-model="selectedRow.logId" :editMode="true"/>
                            <Date label="EventTimestamp" v-model="selectedRow.eventTimestamp" :editMode="true"/>
                            <Number label="Level" v-model="selectedRow.level" :editMode="true"/>
                            <String label="Description" v-model="selectedRow.description" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="ExpiredAt" v-model="selectedRow.expiredAt" :editMode="true"/>
                            <LogType offline label="EventType" v-model="selectedRow.eventType" :editMode="true"/>
                            <UserId offline label="UserId" v-model="selectedRow.userId" :editMode="true"/>
                            <ContentId offline label="ContentId" v-model="selectedRow.contentId" :editMode="true"/>
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
    name: 'systemLogGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'systemLogs',
        generateSystemLogDialog: false,
        deleteSystemLogDialog: false,
    }),
    watch: {
    },
    methods:{
        async generateSystemLog(params){
            try{
                var path = "generateSystemLog".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','GenerateSystemLog 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.generateSystemLogDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async deleteSystemLog(params){
            try{
                var path = "deleteSystemLog".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','DeleteSystemLog 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.deleteSystemLogDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>