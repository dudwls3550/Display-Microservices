<template>

    <v-card outlined>
        <v-card-title>
            DefinePermission
        </v-card-title>

        <v-card-text>
            <PermissionType offline label="PermissionType" v-model="value.permissionType" :editMode="editMode" @change="change"/>
            <String label="Description" v-model="value.description" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="definePermission"
            >
                DefinePermission
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
    name: 'DefinePermissionCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.permissionType = {};
        this.value.description = '';
    },
    watch: {
    },
    methods: {
        definePermission() {
            this.$emit('definePermission', this.value);
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

