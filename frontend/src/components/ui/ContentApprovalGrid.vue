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
                <v-btn style="margin-left: 5px;" @click="startApprovalProcessDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>승인 절차 시작
                </v-btn>
                <v-dialog v-model="startApprovalProcessDialog" width="500">
                    <StartApprovalProcess
                        @closeDialog="startApprovalProcessDialog = false"
                        @startApprovalProcess="startApprovalProcess"
                    ></StartApprovalProcess>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="approveContentDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>콘텐츠 승인
                </v-btn>
                <v-dialog v-model="approveContentDialog" width="500">
                    <ApproveContent
                        @closeDialog="approveContentDialog = false"
                        @approveContent="approveContent"
                    ></ApproveContent>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="rejectContentDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>콘텐츠 반려
                </v-btn>
                <v-dialog v-model="rejectContentDialog" width="500">
                    <RejectContent
                        @closeDialog="rejectContentDialog = false"
                        @rejectContent="rejectContent"
                    ></RejectContent>
                </v-dialog>
            </div>
            <PendingApprovalList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></PendingApprovalList>
            <ApprovalHistory @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ApprovalHistory>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>ContentId</th>
                        <th>RequestedBy</th>
                        <th>RequestedAt</th>
                        <th>ApprovalStatus</th>
                        <th>ApprovalHistory</th>
                        <th>UserId</th>
                        <th>사용자</th>
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
                            <td class="whitespace-nowrap" label="RequestedBy">{{ val.requestedBy }}</td>
                            <td class="whitespace-nowrap" label="RequestedAt">{{ val.requestedAt }}</td>
                            <td class="whitespace-nowrap" label="ApprovalStatus">{{ val.approvalStatus }}</td>
                            <td class="whitespace-nowrap" label="ApprovalHistory">{{ val.approvalHistory }}</td>
                            <td class="whitespace-nowrap" label="UserId">{{ val.userId }}</td>
                            <td class="whitespace-nowrap" label="사용자">
                                <UserId :editMode="editMode" v-model="val.userId"></UserId>
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
                        <div style="color:white; font-size:17px; font-weight:700;">ContentApproval 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <ContentApproval :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">ContentApproval 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="ApprovalId" v-model="selectedRow.approvalId" :editMode="true"/>
                            <Number label="ContentId" v-model="selectedRow.contentId" :editMode="true"/>
                            <Number label="RequestedBy" v-model="selectedRow.requestedBy" :editMode="true"/>
                            <Date label="RequestedAt" v-model="selectedRow.requestedAt" :editMode="true"/>
                            <ApprovalStatus offline label="ApprovalStatus" v-model="selectedRow.approvalStatus" :editMode="true"/>
                            <ApprovalHistory offline label="ApprovalHistory" v-model="selectedRow.approvalHistory" :editMode="true"/>
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
    name: 'contentApprovalGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'contentApprovals',
        startApprovalProcessDialog: false,
        approveContentDialog: false,
        rejectContentDialog: false,
    }),
    watch: {
    },
    methods:{
        async startApprovalProcess(params){
            try{
                var path = "startApprovalProcess".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','StartApprovalProcess 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.startApprovalProcessDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async approveContent(params){
            try{
                var path = "approveContent".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ApproveContent 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.approveContentDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async rejectContent(params){
            try{
                var path = "rejectContent".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RejectContent 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.rejectContentDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>