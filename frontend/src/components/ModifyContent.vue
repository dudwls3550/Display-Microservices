<template>

    <v-card outlined>
        <v-card-title>
            ModifyContent
        </v-card-title>

        <v-card-text>
            <Number label="ContentId" v-model="value.contentId" :editMode="editMode"/>
            <ContentMetadata offline label="Metadata" v-model="value.metadata" :editMode="editMode" @change="change"/>
            <String label="FileUrl" v-model="value.fileUrl" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="modifyContent"
            >
                ModifyContent
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
    name: 'ModifyContentCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.contentId = 0;
        this.value.metadata = {};
        this.value.fileUrl = '';
    },
    watch: {
    },
    methods: {
        modifyContent() {
            this.$emit('modifyContent', this.value);
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

