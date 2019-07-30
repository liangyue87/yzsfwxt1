<template>
    <!--<a href="../../common/components/form/doccamera/Cream.html">试试看</a>-->
        <!--<doc-camera-demo></doc-camera-demo>-->
        <p>
            <!--<html-panel :url="url1">高拍仪</html-panel>-->
            <!--<html-panel :url.asyc="url2">IE</html-panel>-->
<!--            <doc-camera-demo></doc-camera-demo>-->
            <doc-camera></doc-camera>
            <!--<a :href="url1" >试试看</a>-->
        </p>
</template>

<script>
    import HtmlPanel from '../../common/utils/HtmlPanel'
    import DocCameraDemo from '../../common/components/form/doccamera/DocCameraDemo'
    import DocCamera from '../template/DocCamera'
    export default {
        name: "Page10",
        components:{
            DocCamera,
            HtmlPanel,
            DocCameraDemo
        },
        data(){
            return{
                url1: '',
                url2: ''
            }
        },
        methods:{
            start(el)
            {

                if(el=='bStartRec(d:\\test.avi)')
                {
                    timer = window.setInterval("TimeGo()",1000);
                }
                if(el=='bStopRec')
                {
                    window.clearInterval(timer);
                    TotalTimelenth=0;
                    alert("录屏结束");
                }
                webSocket.send(el);
                return false;
            }
        },
        mounted(){
            this.url1 = './static/templates/Cream.html'
            this.url2 = './static/templates/HtmlDemo3.html'
        }
    }
</script>

<style scoped>

</style>
