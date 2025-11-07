<template>

    <v-card outlined>
        <v-card-title>
            UpdateClientDetails
        </v-card-title>

        <v-card-text>
            <Number label="ClientId" v-model="value.clientId" :editMode="editMode"/>
            <String label="ClientName" v-model="value.clientName" :editMode="editMode"/>
            <ClientType offline label="Type" v-model="value.type" :editMode="editMode" @change="change"/>
            <AuthToken offline label="NewAuthToken" v-model="value.newAuthToken" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updateClientDetails"
            >
                UpdateClientDetails
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
    name: 'UpdateClientDetailsCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.clientId = 0;
        this.value.clientName = '';
        this.value.type = {};
        this.value.newAuthToken = {};
    },
    watch: {
    },
    methods: {
        updateClientDetails() {
            this.$emit('updateClientDetails', this.value);
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

