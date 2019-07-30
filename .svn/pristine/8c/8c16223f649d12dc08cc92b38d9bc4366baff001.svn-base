<template>
    <div>
        <h3>{{ post.title }}</h3>
        <button v-on:click="$emit('enlarge-text',0.1)">
            Enlarge text
        </button>
        <div v-html="post.content"></div>
    </div>

</template>

<script>
    export default {
        name: "BlogPost",
        props: {
            post:{
                type: Object,
                default: () => ({})
            }
        },
        methods: {
        },
        computed: {
        }
    }
</script>

<style scoped>

</style>
