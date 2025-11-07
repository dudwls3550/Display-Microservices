<template>
    <div>
        <Number
            label="MetadataId"
            v-model="value.metadataId"
            :editMode="editMode"
        />
        <Number
            label="ContentId"
            v-model="value.contentId"
            :editMode="editMode"
        />
        <String
            label="Title"
            v-model="value.title"
            :editMode="editMode"
        />
        <String
            label="Description"
            v-model="value.description"
            :editMode="editMode"
        />
        <div variant="outlined" class="my-2">
            <Tags v-model="tagsInput"
                offline
                label="Tags"
                :editMode="editMode"
                @change="change"
            />
            <v-row class="ma-0 pa-0 mt-2">
                <v-spacer></v-spacer>
                <v-btn @click="addTagsList">Tags Add</v-btn>
            </v-row>
            <v-card v-if="value.tags.length > 0" variant="outlined" class="pa-4 mt-2">
                <v-card-sub-title>
                    Tags List
                </v-card-sub-title>
                <li v-for="(id, index) in value.tags" :key="index">
                    {{ id && id. ? id. : '' }}
                </li>
            </v-card>
        </div>
        <ContentCategory
            offline
            label="Category"
            v-model="value.category"
            :editMode="editMode"
            @change="change"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Metadata',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "metadata",
        tagsInput: null,
        value: {
            tags: [],
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
        addTagsList() {
            if (this.tagsInput !== null && this.tagsInput !== '') {
                this.value.tags.push(this.tagsInput);
                this.tagsInput = null; // null로 초기화
            }
        },
    },
}
</script>
