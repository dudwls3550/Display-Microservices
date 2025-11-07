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
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="requestContentApprovalDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>콘텐츠 승인 요청
                </v-btn>
                <v-dialog v-model="requestContentApprovalDialog" width="500">
                    <RequestContentApproval
                        @closeDialog="requestContentApprovalDialog = false"
                        @requestContentApproval="requestContentApproval"
                    ></RequestContentApproval>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="registerContentDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>콘텐츠 등록
                </v-btn>
                <v-dialog v-model="registerContentDialog" width="500">
                    <RegisterContent
                        @closeDialog="registerContentDialog = false"
                        @registerContent="registerContent"
                    ></RegisterContent>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="publishContentDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>콘텐츠 배포
                </v-btn>
                <v-dialog v-model="publishContentDialog" width="500">
                    <PublishContent
                        @closeDialog="publishContentDialog = false"
                        @publishContent="publishContent"
                    ></PublishContent>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="modifyContentDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>콘텐츠 수정
                </v-btn>
                <v-dialog v-model="modifyContentDialog" width="500">
                    <ModifyContent
                        @closeDialog="modifyContentDialog = false"
                        @modifyContent="modifyContent"
                    ></ModifyContent>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="deleteContentDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>콘텐츠 삭제
                </v-btn>
                <v-dialog v-model="deleteContentDialog" width="500">
                    <DeleteContent
                        @closeDialog="deleteContentDialog = false"
                        @deleteContent="deleteContent"
                    ></DeleteContent>
                </v-dialog>
            </div>
            <ContentList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ContentList>
            <ContentSearchIndex @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ContentSearchIndex>
            <ContentDetail @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ContentDetail>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>OwnerId</th>
                        <th>Metadata</th>
                        <th>Status</th>
                        <th>FileUrl</th>
                        <th>CreatedAt</th>
                        <th>UpdatedAt</th>
                        <th>DeletedAt</th>
                        <th>ContentApprovalId</th>
                        <th>UserId</th>
                        <th>MetadataId</th>
                        <th>메타데이터</th>
                        <th>승인 프로세스</th>
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
                            <td class="whitespace-nowrap" label="OwnerId">{{ val.ownerId }}</td>
                            <td class="whitespace-nowrap" label="Metadata">{{ val.metadata }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="FileUrl">{{ val.fileUrl }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="UpdatedAt">{{ val.updatedAt }}</td>
                            <td class="whitespace-nowrap" label="DeletedAt">{{ val.deletedAt }}</td>
                            <td class="whitespace-nowrap" label="ContentApprovalId">{{ val.contentApprovalId }}</td>
                            <td class="whitespace-nowrap" label="UserId">{{ val.userId }}</td>
                            <td class="whitespace-nowrap" label="MetadataId">{{ val.metadataId }}</td>
                            <td class="whitespace-nowrap" label="메타데이터">
                                <MetadataId :editMode="editMode" v-model="val.metadataId"></MetadataId>
                            </td>
                            <td class="whitespace-nowrap" label="승인 프로세스">
                                <ContentApprovalId :editMode="editMode" v-model="val.contentApprovalId"></ContentApprovalId>
                            </td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Content 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Content :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Content 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="ContentId" v-model="selectedRow.contentId" :editMode="true"/>
                            <Number label="OwnerId" v-model="selectedRow.ownerId" :editMode="true"/>
                            <String label="FileUrl" v-model="selectedRow.fileUrl" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="UpdatedAt" v-model="selectedRow.updatedAt" :editMode="true"/>
                            <Date label="DeletedAt" v-model="selectedRow.deletedAt" :editMode="true"/>
                            <ContentMetadata offline label="Metadata" v-model="selectedRow.metadata" :editMode="true"/>
                            <ContentStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
                            <ContentApprovalId offline label="ContentApprovalId" v-model="selectedRow.contentApprovalId" :editMode="true"/>
                            <UserId offline label="UserId" v-model="selectedRow.userId" :editMode="true"/>
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
    name: 'contentGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'contents',
        requestContentApprovalDialog: false,
        registerContentDialog: false,
        publishContentDialog: false,
        modifyContentDialog: false,
        deleteContentDialog: false,
    }),
    watch: {
    },
    methods:{
        async requestContentApproval(params){
            try{
                var path = "requestContentApproval".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RequestContentApproval 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.requestContentApprovalDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async registerContent(params){
            try{
                var path = "registerContent".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RegisterContent 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.registerContentDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async publishContent(params){
            try{
                var path = "publishContent".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PublishContent 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.publishContentDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async modifyContent(params){
            try{
                var path = "modifyContent".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ModifyContent 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.modifyContentDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async deleteContent(params){
            try{
                var path = "deleteContent".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','DeleteContent 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.deleteContentDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>