<template>

    <v-card outlined>
        <v-card-title>
            RejectContent
        </v-card-title>

        <v-card-text>
            <Number label="ApprovalId" v-model="value.approvalId" :editMode="editMode"/>
            <Number label="RejectedBy" v-model="value.rejectedBy" :editMode="editMode"/>
            <String label="RejectionReason" v-model="value.rejectionReason" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="rejectContent"
            >
                RejectContent
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
    name: 'RejectContentCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.approvalId = 0;
        this.value.rejectedBy = 0;
        this.value.rejectionReason = '';
    },
    watch: {
    },
    methods: {
        rejectContent() {
            this.$emit('rejectContent', this.value);
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

