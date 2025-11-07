<template>

    <v-card outlined>
        <v-card-title>
            RegisterContent
        </v-card-title>

        <v-card-text>
            <Number label="OwnerId" v-model="value.ownerId" :editMode="editMode"/>
            <ContentMetadata offline label="Metadata" v-model="value.metadata" :editMode="editMode" @change="change"/>
            <String label="FileUrl" v-model="value.fileUrl" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="registerContent"
            >
                RegisterContent
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
    name: 'RegisterContentCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.ownerId = 0;
        this.value.metadata = {};
        this.value.fileUrl = '';
    },
    watch: {
    },
    methods: {
        registerContent() {
            this.$emit('registerContent', this.value);
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

