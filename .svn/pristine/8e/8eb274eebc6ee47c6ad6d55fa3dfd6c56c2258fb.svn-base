<template>
    <div>
        <script id="editor" type="text/plain"></script>
    </div>
</template>
<script>
    export default {
        name: 'UE',
        data () {
            return {
                editor: null
            }
        },
        props: {
            defaultMsg: {
                type: String
            },
            config: {
                type: Object
            }
        },
        mounted() {
            const _this = this;
            this.editor = UE.getEditor('editor', this.config); // 初始化UE
            this.editor.addListener("ready", function () {
                _this.editor.setContent(_this.defaultMsg); // 确保UE加载完成后，放入内容。
            });
        },
        methods: {
            getUEContent() { // 获取内容方法
                return this.editor.getContent()
            },
            getUMContent: function(){// 获取内容方法
                let self = this,html = '';
                this.editor.ready(function (){
                    html = self.editor.getContent();
                });
                return html;
            },
            setUMContent: function(msg){//重写内容方法
                let self = this,setResult = null;
                this.editor.ready(function (){
                    setResult = self.editor.setContent(msg);
                });
                return setResult;
            }
        },
        destroyed() {
            this.editor.destroy();
        }
    }
</script>