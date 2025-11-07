<template>

    <v-card outlined>
        <v-card-title>
            SendExternalNotification
        </v-card-title>

        <v-card-text>
            <ContentId offline label="ContentId" v-model="value.contentId" :editMode="editMode" @change="change"/>
            <ExternalIntegrationClientId offline label="ExternalIntegrationClientId" v-model="value.externalIntegrationClientId" :editMode="editMode" @change="change"/>
            <NotificationChannel offline label="Channel" v-model="value.channel" :editMode="editMode" @change="change"/>
            <MetadataId offline label="MetadataId" v-model="value.metadataId" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="sendExternalNotification"
            >
                SendExternalNotification
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
    name: 'SendExternalNotificationCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.contentId = {};
        this.value.externalIntegrationClientId = {};
        this.value.channel = {};
        this.value.metadataId = {};
    },
    watch: {
    },
    methods: {
        sendExternalNotification() {
            this.$emit('sendExternalNotification', this.value);
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

