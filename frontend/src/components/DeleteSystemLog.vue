<template>

    <v-card outlined>
        <v-card-title>
            DeleteSystemLog
        </v-card-title>

        <v-card-text>
            <Number label="LogId" v-model="value.logId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="deleteSystemLog"
            >
                DeleteSystemLog
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
    name: 'DeleteSystemLogCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.logId = 0;
    },
    watch: {
    },
    methods: {
        deleteSystemLog() {
            this.$emit('deleteSystemLog', this.value);
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

