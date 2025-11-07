<template>

    <v-card outlined>
        <v-card-title>
            GenerateStatistics
        </v-card-title>

        <v-card-text>
            <StatisticsType offline label="StatisticsType" v-model="value.statisticsType" :editMode="editMode" @change="change"/>
            <String label="AggregationPeriod" v-model="value.aggregationPeriod" :editMode="editMode"/>
            <ContentId offline label="ContentId" v-model="value.contentId" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="generateStatistics"
            >
                GenerateStatistics
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>

export default {
    name: 'GenerateStatisticsCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.statisticsType = {};
        this.value.aggregationPeriod = '';
        this.value.contentId = {};
    },
    watch: {
    },
    methods: {
        generateStatistics() {
            this.$emit('generateStatistics', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

