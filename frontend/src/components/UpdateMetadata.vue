<template>

    <v-card outlined>
        <v-card-title>
            UpdateMetadata
        </v-card-title>

        <v-card-text>
            <Number label="MetadataId" v-model="value.metadataId" :editMode="editMode"/>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <String label="Description" v-model="value.description" :editMode="editMode"/>
            <ContentCategory offline label="Category" v-model="value.category" :editMode="editMode" @change="change"/>
            <TagList offline label="Tags" v-model="value.tags" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updateMetadata"
            >
                UpdateMetadata
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
    name: 'UpdateMetadataCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.metadataId = 0;
        this.value.title = '';
        this.value.description = '';
        this.value.category = {};
        this.value.tags = [];
    },
    watch: {
    },
    methods: {
        updateMetadata() {
            this.$emit('updateMetadata', this.value);
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

