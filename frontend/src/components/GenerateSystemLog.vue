<template>

    <v-card outlined>
        <v-card-title>
            GenerateSystemLog
        </v-card-title>

        <v-card-text>
            <LogType offline label="EventType" v-model="value.eventType" :editMode="editMode" @change="change"/>
            <Date label="EventTimestamp" v-model="value.eventTimestamp" :editMode="editMode"/>
            <UserId offline label="UserId" v-model="value.userId" :editMode="editMode" @change="change"/>
            <Number label="Level" v-model="value.level" :editMode="editMode"/>
            <String label="Description" v-model="value.description" :editMode="editMode"/>
            <ContentId offline label="ContentId" v-model="value.contentId" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="generateSystemLog"
            >
                GenerateSystemLog
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
    name: 'GenerateSystemLogCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.eventType = {};
        this.value.eventTimestamp = '2025-11-07';
        this.value.userId = {};
        this.value.level = 0;
        this.value.description = '';
        this.value.contentId = {};
    },
    watch: {
    },
    methods: {
        generateSystemLog() {
            this.$emit('generateSystemLog', this.value);
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

