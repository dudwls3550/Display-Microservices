<template>
    <div>
        <Number
            label="StatisticsId"
            v-model="value.statisticsId"
            :editMode="editMode"
        />
        <Date
            label="GeneratedAt"
            v-model="value.generatedAt"
            :editMode="editMode"
        />
        <String
            label="AggregationPeriod"
            v-model="value.aggregationPeriod"
            :editMode="editMode"
        />
        <String
            label="Data"
            v-model="value.data"
            :editMode="editMode"
        />
        <StatisticsType
            offline
            label="StatisticsType"
            v-model="value.statisticsType"
            :editMode="editMode"
            @change="change"
        />
        <ContentId
            offline
            label="ContentId"
            v-model="value.contentId"
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
    name: 'Statistics',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "statistics",
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
