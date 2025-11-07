<template>

    <v-card outlined>
        <v-card-title>
            RecordAbnormalEvent
        </v-card-title>

        <v-card-text>
            <SystemLogId offline label="SystemLogId" v-model="value.systemLogId" :editMode="editMode" @change="change"/>
            <String label="Symptom" v-model="value.symptom" :editMode="editMode"/>
            <EventSeverity offline label="Severity" v-model="value.severity" :editMode="editMode" @change="change"/>
            <UserId offline label="UserId" v-model="value.userId" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="recordAbnormalEvent"
            >
                RecordAbnormalEvent
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
    name: 'RecordAbnormalEventCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.systemLogId = {};
        this.value.symptom = '';
        this.value.severity = {};
        this.value.userId = {};
    },
    watch: {
    },
    methods: {
        recordAbnormalEvent() {
            this.$emit('recordAbnormalEvent', this.value);
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

