<template>

    <v-card outlined>
        <v-card-title>
            DeactivateClient
        </v-card-title>

        <v-card-text>
            <Number label="ClientId" v-model="value.clientId" :editMode="editMode"/>
            <String label="Reason" v-model="value.reason" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="deactivateClient"
            >
                DeactivateClient
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
    name: 'DeactivateClientCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.clientId = 0;
        this.value.reason = '';
    },
    watch: {
    },
    methods: {
        deactivateClient() {
            this.$emit('deactivateClient', this.value);
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

