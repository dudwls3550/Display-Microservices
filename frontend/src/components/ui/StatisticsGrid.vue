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
                <v-btn style="margin-left: 5px;" @click="generateStatisticsDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>통계 생성 요청
                </v-btn>
                <v-dialog v-model="generateStatisticsDialog" width="500">
                    <GenerateStatistics
                        @closeDialog="generateStatisticsDialog = false"
                        @generateStatistics="generateStatistics"
                    ></GenerateStatistics>
                </v-dialog>
            </div>
            <OperationalStatistics @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></OperationalStatistics>
            <StatisticsSummary @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></StatisticsSummary>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>GeneratedAt</th>
                        <th>StatisticsType</th>
                        <th>AggregationPeriod</th>
                        <th>Data</th>
                        <th>ContentId</th>
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
                            <td class="whitespace-nowrap" label="GeneratedAt">{{ val.generatedAt }}</td>
                            <td class="whitespace-nowrap" label="StatisticsType">{{ val.statisticsType }}</td>
                            <td class="whitespace-nowrap" label="AggregationPeriod">{{ val.aggregationPeriod }}</td>
                            <td class="whitespace-nowrap" label="Data">{{ val.data }}</td>
                            <td class="whitespace-nowrap" label="ContentId">{{ val.contentId }}</td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Statistics 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Statistics :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Statistics 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="StatisticsId" v-model="selectedRow.statisticsId" :editMode="true"/>
                            <Date label="GeneratedAt" v-model="selectedRow.generatedAt" :editMode="true"/>
                            <String label="AggregationPeriod" v-model="selectedRow.aggregationPeriod" :editMode="true"/>
                            <String label="Data" v-model="selectedRow.data" :editMode="true"/>
                            <StatisticsType offline label="StatisticsType" v-model="selectedRow.statisticsType" :editMode="true"/>
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
    name: 'statisticsGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'statistics',
        generateStatisticsDialog: false,
    }),
    watch: {
    },
    methods:{
        async generateStatistics(params){
            try{
                var path = "generateStatistics".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','GenerateStatistics 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.generateStatisticsDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>