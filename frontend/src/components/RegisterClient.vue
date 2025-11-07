<template>

    <v-card outlined>
        <v-card-title>
            RegisterClient
        </v-card-title>

        <v-card-text>
            <String label="ClientName" v-model="value.clientName" :editMode="editMode"/>
            <ClientType offline label="Type" v-model="value.type" :editMode="editMode" @change="change"/>
            <AuthToken offline label="AuthToken" v-model="value.authToken" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="registerClient"
            >
                RegisterClient
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
    name: 'RegisterClientCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.clientName = '';
        this.value.type = {};
        this.value.authToken = {};
    },
    watch: {
    },
    methods: {
        registerClient() {
            this.$emit('registerClient', this.value);
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

