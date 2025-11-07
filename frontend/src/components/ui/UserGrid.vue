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
                <v-btn style="margin-left: 5px;" @click="registerUserDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 등록
                </v-btn>
                <v-dialog v-model="registerUserDialog" width="500">
                    <RegisterUser
                        @closeDialog="registerUserDialog = false"
                        @registerUser="registerUser"
                    ></RegisterUser>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="grantUserPermissionDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 권한 부여
                </v-btn>
                <v-dialog v-model="grantUserPermissionDialog" width="500">
                    <GrantUserPermission
                        @closeDialog="grantUserPermissionDialog = false"
                        @grantUserPermission="grantUserPermission"
                    ></GrantUserPermission>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="updateUserDetailsDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 상세 정보 수정
                </v-btn>
                <v-dialog v-model="updateUserDetailsDialog" width="500">
                    <UpdateUserDetails
                        @closeDialog="updateUserDetailsDialog = false"
                        @updateUserDetails="updateUserDetails"
                    ></UpdateUserDetails>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="activateUserDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 활성화
                </v-btn>
                <v-dialog v-model="activateUserDialog" width="500">
                    <ActivateUser
                        @closeDialog="activateUserDialog = false"
                        @activateUser="activateUser"
                    ></ActivateUser>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="deactivateUserDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 비활성화
                </v-btn>
                <v-dialog v-model="deactivateUserDialog" width="500">
                    <DeactivateUser
                        @closeDialog="deactivateUserDialog = false"
                        @deactivateUser="deactivateUser"
                    ></DeactivateUser>
                </v-dialog>
            </div>
            <UserList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></UserList>
            <UserAccessProfile @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></UserAccessProfile>
            <UserDetail @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></UserDetail>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>IsActive</th>
                        <th>CreatedAt</th>
                        <th>UpdatedAt</th>
                        <th>Credential</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="Name">{{ val.name }}</td>
                            <td class="whitespace-nowrap" label="Email">{{ val.email }}</td>
                            <td class="whitespace-nowrap" label="Role">{{ val.role }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="IsActive">{{ val.isActive }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="UpdatedAt">{{ val.updatedAt }}</td>
                            <td class="whitespace-nowrap" label="Credential">{{ val.credential }}</td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">User 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <User :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">User 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="UserId" v-model="selectedRow.userId" :editMode="true"/>
                            <String label="Name" v-model="selectedRow.name" :editMode="true"/>
                            <String label="Email" v-model="selectedRow.email" :editMode="true"/>
                            <Boolean label="IsActive" v-model="selectedRow.isActive" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="UpdatedAt" v-model="selectedRow.updatedAt" :editMode="true"/>
                            <UserRole offline label="Role" v-model="selectedRow.role" :editMode="true"/>
                            <UserStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
                            <UserCredential offline label="Credential" v-model="selectedRow.credential" :editMode="true"/>
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
    name: 'userGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'users',
        registerUserDialog: false,
        grantUserPermissionDialog: false,
        updateUserDetailsDialog: false,
        activateUserDialog: false,
        deactivateUserDialog: false,
    }),
    watch: {
    },
    methods:{
        async registerUser(params){
            try{
                var path = "registerUser".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RegisterUser 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.registerUserDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async grantUserPermission(params){
            try{
                var path = "grantUserPermission".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','GrantUserPermission 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.grantUserPermissionDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async updateUserDetails(params){
            try{
                var path = "updateUserDetails".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UpdateUserDetails 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.updateUserDetailsDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async activateUser(params){
            try{
                var path = "activateUser".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ActivateUser 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.activateUserDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async deactivateUser(params){
            try{
                var path = "deactivateUser".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','DeactivateUser 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.deactivateUserDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>