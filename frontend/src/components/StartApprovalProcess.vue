<template>

    <v-card outlined>
        <v-card-title>
            StartApprovalProcess
        </v-card-title>

        <v-card-text>
            <Number label="ContentId" v-model="value.contentId" :editMode="editMode"/>
            <Number label="RequestedBy" v-model="value.requestedBy" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="startApprovalProcess"
            >
                StartApprovalProcess
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
    name: 'StartApprovalProcessCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.contentId = 0;
        this.value.requestedBy = 0;
    },
    watch: {
    },
    methods: {
        startApprovalProcess() {
            this.$emit('startApprovalProcess', this.value);
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

