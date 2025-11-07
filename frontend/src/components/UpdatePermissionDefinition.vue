<template>

    <v-card outlined>
        <v-card-title>
            UpdatePermissionDefinition
        </v-card-title>

        <v-card-text>
            <PermissionType offline label="PermissionType" v-model="value.permissionType" :editMode="editMode" @change="change"/>
            <String label="NewDescription" v-model="value.newDescription" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updatePermissionDefinition"
            >
                UpdatePermissionDefinition
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
    name: 'UpdatePermissionDefinitionCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.permissionType = {};
        this.value.newDescription = '';
    },
    watch: {
    },
    methods: {
        updatePermissionDefinition() {
            this.$emit('updatePermissionDefinition', this.value);
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

