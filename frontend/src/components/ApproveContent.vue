<template>

    <v-card outlined>
        <v-card-title>
            ApproveContent
        </v-card-title>

        <v-card-text>
            <Number label="ApprovalId" v-model="value.approvalId" :editMode="editMode"/>
            <Number label="ApprovedBy" v-model="value.approvedBy" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="approveContent"
            >
                ApproveContent
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
    name: 'ApproveContentCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.approvalId = 0;
        this.value.approvedBy = 0;
    },
    watch: {
    },
    methods: {
        approveContent() {
            this.$emit('approveContent', this.value);
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

