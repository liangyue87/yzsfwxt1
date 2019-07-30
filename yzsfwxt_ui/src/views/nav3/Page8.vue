<template>
    <div :style="{ fontSize: postFontSize + 'em'}">
        <BlogPost v-for="post in posts" :post="post" :key="post.id" v-on:enlarge-text="postFontSize += $event"></BlogPost>
    </div>

</template>


<script>
    export default {
        data(){
            return {
                posts:  [
                    { id: 1, title: 'My journey with Vue', content: "caicaikan" },
                    { id: 2, title: 'Blogging with Vue', content: "caicaikan" },
                    { id: 3, title: 'Why Vue is so fun', content: "caicaikan" }
                ],
                postFontSize: 1
            }
        },
        methods:{
            onEnlargeText: function (enlargeAmount) {
                this.postFontSize += enlargeAmount
            }
        }
    }
</script>
