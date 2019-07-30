<template>
    <section>
        <div slot="header" class="clearfix">
            <!--  <span >地区</span>-->
            <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                需求受理
            </div>
        </div>
        <div id="labelManage">
            <div id="main">
                <div class="flowchart-demo" id="flowchart-demo">
                    <!-- <div v-if="XqslSt">
                         <div class="window" id="flowchartWindow33">{{xqsl_tx}}</div>
                         <div class="window" id="flowchartWindow44">{{xqsl_fp}}</div>
                         <div class="window" id="flowchartWindow55">{{xqsl_qs}}</div>
                     </div>
                     <div v-if="XklxSt">
                         <div class="window" id="flowchartWindow1">1
                         </div>
                         <div class="window" id="flowchartWindow2">2
                         </div>
                         <div class="window" id="flowchartWindow3">3
                         </div>
                     </div>-->
                </div>
            </div>

        </div>
    </section>
</template>

<script>
    import {jsPlumb} from 'jsplumb'
    import {selectXqsl} from '../../api/Xmlct';
    export default {
        name: "Xqsl_1",
        props:{
            xmid:{
                type: String,
                required: true
            },
            xqsl:{
                type:String,
                required:true
            }
        },
        data() {
            return {

            }
        },
        methods: {
            getXqsl(){

                let params = Object.assign({xmid: this.xmid});
                selectXqsl(params).then((res) => {
                    let taskDq = eval(res.data.taskDq);//当前节点
                    let taskLs = eval(res.data.taskLs);//历史完成节点
                    if(taskDq!=""){
                        let actid= taskDq[0].c_activityid.substr(4,1);
                        if(actid=="1"||actid=="4"||actid=="7") {//居民
                            let html =
                                html='<div class="window rank_one line_three" name="10001" id="flowchartWindow33">居民科填报资料</div>'+
                                    '<div class="window rank_two line_three" name="10004" id="flowchartWindow44">居民科分派任务</div>' +
                                    '<div class="window rank_three line_three" name="10007" id="flowchartWindow55">居民科签收任务</div>';
                            document.getElementById("flowchart-demo").innerHTML = html;
                            let tb=   document.getElementById("flowchartWindow33").getAttribute("name");
                            let fp=   document.getElementById("flowchartWindow44").getAttribute("name");
                            let qs=   document.getElementById("flowchartWindow55").getAttribute("name");
                            if(tb==taskDq[0].c_activityid){
                                document.getElementById("flowchartWindow33").style.background="green";
                            }
                            if(fp==taskDq[0].c_activityid){
                                document.getElementById("flowchartWindow44").style.background="green";
                            }
                            if(qs==taskDq[0].c_activityid){
                                document.getElementById("flowchartWindow55").style.background="green";
                            }

                        }
                        this.plumb.addEndpoint('flowchartWindow33', {uuid: 33, anchors: ['Right'], isSource: true});
                        this.plumb.addEndpoint('flowchartWindow44', {uuid: 44, anchors: ['Left'], isTarget: true});
                        this.plumb.connect({uuids: [33, 44],detachable: false});
                        this.plumb.addEndpoint('flowchartWindow44', {uuid: 44.1, anchors: ['Right'], isTarget: true});
                        this.plumb.addEndpoint('flowchartWindow55', {uuid: 55, anchors: ['Left'], isTarget: true});
                        this.plumb.connect({uuids: [44.1, 55],detachable: false});
                    }
                });
            }
        },
        mounted() {
            this.plumb = jsPlumb.getInstance({
                Endpoint: ["Dot", {radius: 5}], //这个是控制连线终端那个小点的半径
                //  EndpointStyle: {fill: 'green', outlineStroke: 'darkgray', outlineWidth: 2},//点的样式
                PaintStyle: {stroke: 'green', strokeWidth: 3},  //线的样式
                Connector: ["Straight", {curviness: 70}],//直线
                ConnectionOverlays: [
                    ["Arrow", {width: 12, length: 12, location: 1}],//箭头样式
                ],
                Container: "flowchart-demo"
            });
            this.getXqsl();
        },
    }
</script>

<style>
    .flowDemo {
        width: 100%;
        height: 300px;
        /*border: 1px solid #000;*/
        position: relative;
    }
    .flowDemo:after{
        content: "";
        display: inline-block;
        width: 100%;
        height: 0;
    }
    .line_one{
        top: 0em;
    }
    .line_two{
        top: 4em;
    }
    .line_three{
        top: 8em;
    }
    .line_four{
        top: 12em;
    }
    .line_five{
        top: 16em;
    }
    .rank_one{
        left: 3em;
    }
    .rank_two{
        left: 18em;
    }
    .rank_three{
        left: 34em;
    }
    .rank_four{
        left: 50em;
    }
    .rank_five{
        left: 66em;
    }

    .flowDemo .window {
        /*border: 1px solid #346789;*/
        /*box-shadow: 2px 2px 19px #aaa;*/
        /*-o-box-shadow: 2px 2px 19px #aaa;*/
        /*-webkit-box-shadow: 2px 2px 19px #aaa;*/
        /*-moz-box-shadow: 2px 2px 19px #aaa;*/
        /*-moz-border-radius: 0.5em;*/
        border-radius: 0.1em;
        opacity: 0.9;
        filter: alpha(opacity=80);
        text-align: center;
        position: absolute;
        /*background-color: #eeeeef;*/
        color: #ffffff;
        font-family: helvetica;
        font-size: 0.9em;
        line-height: 35px;
        width: 135px;
        height: 35px;
    }

    .flowDemo .window:hover {
        box-shadow: 2px 2px 19px #444;
        -o-box-shadow: 2px 2px 19px #444;
        -webkit-box-shadow: 2px 2px 19px #444;
        -moz-box-shadow: 2px 2px 19px #444;
        opacity: 0.6;
        filter: alpha(opacity=60);
    }
    .lc {
        font-size: 30px;
    }
    .flowDemo .window_wbl {
        background-color: #B8B8B8;
    }
    .flowDemo .window_ybl {
        background-color: #26BD5D;
    }
    .flowDemo .window_bl {
        background-color: #0093E6;
        box-shadow: 2px 2px 19px #B8B8B8;
        -o-box-shadow: 2px 2px 19px #B8B8B8;
        -webkit-box-shadow: 2px 2px 19px #B8B8B8;
        -moz-box-shadow: 2px 2px 19px #B8B8B8;
        -moz-border-radius: 0.5em;
    }
</style>