<template>

    <v-card outlined>
        <v-card-title>
            ResolveAbnormalEvent
        </v-card-title>

        <v-card-text>
            <Number label="AbnormalEventId" v-model="value.abnormalEventId" :editMode="editMode"/>
            <String label="ResolutionNotes" v-model="value.resolutionNotes" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="resolveAbnormalEvent"
            >
                ResolveAbnormalEvent
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
    name: 'ResolveAbnormalEventCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.abnormalEventId = 0;
        this.value.resolutionNotes = '';
    },
    watch: {
    },
    methods: {
        resolveAbnormalEvent() {
            this.$emit('resolveAbnormalEvent', this.value);
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

