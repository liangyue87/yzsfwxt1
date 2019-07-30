<template>
    <section>
        <div slot="header" class="clearfix">
            <!--  <span >地区</span>-->
            <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                勘查立项
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
    export default {
        name: "Xklx_2",
        props:{
            xmid:{
                type: String,
                required: true
            },
            xqsl:{
                type:String,
                required:true
            },
            data() {
                return {}
            }
        },
        methods: {
            getKclx(){
                let html = "";
                html =
                    '<div class="window" id="flowchartWindow22">联合勘查创建</div>' +
                    '<div class="window" id="flowchartWindow3">设计院指派人员</div>' +
                    '<div class="window" id="flowchartWindow33">施工单位指派人员</div>' +
                    '<div class="window" id="flowchartWindow333">区所指派人员</div>' +
                    '<div class="window" id="flowchartWindow3333">预算科指派人员</div>' +
                    '<div class="window" id="flowchartWindow4">设计院反馈结果</div>' +
                    '<div class="window" id="flowchartWindow44">施工单位反馈结果</div>' +
                    '<div class="window" id="flowchartWindow444">区所反馈结果</div>' +
                    '<div class="window" id="flowchartWindow4444">预算科反馈结果</div>'+
                    '<div class="window" id="flowchartWindow55">联合勘查评价</div>'+
                    '<div class="window" id="flowchartWindow66">用户确认反馈结果</div>'+
                    '<div class="window" id="flowchartWindow77">项目立项</div>';
                document.getElementById("flowchart-demo").innerHTML = html;

                this.plumb.addEndpoint('flowchartWindow22', {uuid:22, anchors: ['Right'], isSource: true,maxConnections: -1});
                this.plumb.addEndpoint('flowchartWindow3', {uuid:3, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('flowchartWindow3', {uuid:3.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [22, 3],detachable: false});
                this.plumb.addEndpoint('flowchartWindow4', {uuid:4, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('flowchartWindow4', {uuid:4.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [3.1, 4],detachable: false});
                this.plumb.addEndpoint('flowchartWindow33', {uuid:33, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('flowchartWindow33', {uuid:33.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [22, 33],detachable: false});
                this.plumb.addEndpoint('flowchartWindow44', {uuid:44, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('flowchartWindow44', {uuid:44.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [33.1, 44],detachable: false});
                this.plumb.addEndpoint('flowchartWindow333', {uuid:333, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('flowchartWindow333', {uuid:333.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [22, 333],detachable: false});
                this.plumb.addEndpoint('flowchartWindow444', {uuid:444, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('flowchartWindow444', {uuid:444.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [333.1, 444],detachable: false});
                this.plumb.addEndpoint('flowchartWindow3333', {uuid:3333, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('flowchartWindow3333', {uuid:3333.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [22, 3333],detachable: false});
                this.plumb.addEndpoint('flowchartWindow4444', {uuid:4444, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('flowchartWindow4444', {uuid:4444.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [3333.1, 4444],detachable: false});
                this.plumb.addEndpoint('flowchartWindow55', {uuid:55, anchors: ['Left'], isSource: true,maxConnections: -1});
                this.plumb.addEndpoint('flowchartWindow55', {uuid:55.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [4.1, 55],detachable: false});
                this.plumb.connect({uuids: [44.1, 55],detachable: false});
                this.plumb.connect({uuids: [444.1, 55],detachable: false});
                this.plumb.connect({uuids: [4444.1, 55],detachable: false});
                this.plumb.addEndpoint('flowchartWindow66', {uuid:66, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('flowchartWindow66', {uuid:66.1, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [55.1, 66],detachable: false});
                this.plumb.addEndpoint('flowchartWindow77', {uuid:77, anchors: ['Left'], isSource: true});
                this.plumb.connect({uuids: [66.1, 77],detachable: false});
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
            this.getKclx();
        },

    }
</script>

<style scoped>

</style>