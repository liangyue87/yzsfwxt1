<template>
    <el-row style="text-align: center">
        <el-col :span="24">
            <el-card class="box-card" style="height: 420px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                        综合验收
                    </div>
                </div>
                <div class="text item flowDemo" id="flowDemo">

                </div>
            </el-card>
        </el-col>
    </el-row>


</template>


<script>
    import {jsPlumb} from 'jsplumb'
    import $ from 'jquery'
    import {getAllInstInfo} from '../../../api/work'
    let FloatPanel = require('../../../common/js/floatPanel');

    export default {
        data() {
            return {
                c_activityid : '8',
            }
        },
        props: ['xmid'],
        methods: {
            //勘察立项
            initView() {
                let html = "";
                html = '<div class="window rank_one line_four " id="80001">发起综合验收申请</div>' +
                    '<div class="window rank_two line_four " id="80002">下达综合验收单</div>' +
                 /*   '<div class="window rank_three line_one " id="80003">建设单位分派负责人</div>' +*/
                    '<div class="window rank_three line_two " id="80004">监理单位分派负责人</div>' +
                    '<div class="window rank_three line_three " id="80005">供水所分派负责人</div>'+
                    '<div class="window rank_three line_five " id="80006">信息规划部分派负责人</div>' +
                    '<div class="window rank_three line_six " id="80007">施工部门分派负责人</div>' +
                    '<div class="window rank_three line_seven " id="80008">工程管理部分派负责人</div>' +

                  /*  '<div class="window rank_four line_one " id="80009">建设单位反馈意见</div>'+*/
                    '<div class="window rank_four line_two " id="80010">监理单位反馈意见</div>'+
                    '<div class="window rank_four line_three " id="80011">供水所反馈意见</div>' +
                    '<div class="window rank_four line_five " id="80012">信息规划部反馈意见</div>' +
                    '<div class="window rank_four line_six " id="80013">施工部门反馈意见</div>' +
                    '<div class="window rank_four line_seven " id="80014">工程管理部反馈意见</div>' +
                    '<div class="window rank_five_ line_four " id="80015">项目经理反馈验收意见</div>' +
                    '<div class="window rank_five_ line_one " id="80016">施工整改</div>' ;

                document.getElementById("flowDemo").innerHTML = html;
                this.plumb.addEndpoint('80001', {uuid: 80001, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('80002', {uuid: 80002, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80002', {uuid: 800022, anchors: ['Right'], isTarget: true,maxConnections:6});
            /*    this.plumb.addEndpoint('80003', {uuid: 80003, anchors: ['Left'], isTarget: true});*/
         /*       this.plumb.addEndpoint('80003', {uuid: 800032, anchors: ['Right'], isTarget: true});*/
                this.plumb.addEndpoint('80004', {uuid: 80004, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80004', {uuid: 800042, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('80005', {uuid: 80005, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80005', {uuid: 800052, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('80006', {uuid: 80006, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80006', {uuid: 800062, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('80007', {uuid: 80007, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80007', {uuid: 800072, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('80008', {uuid: 80008, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80008', {uuid: 800082, anchors: ['Right'], isTarget: true});
             /*   this.plumb.addEndpoint('80009', {uuid: 80009, anchors: ['Left'], isTarget: true});*/
           /*     this.plumb.addEndpoint('80009', {uuid: 800092, anchors: ['Right'], isTarget: true,maxConnections:2});*/
                this.plumb.addEndpoint('80010', {uuid: 80010, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80010', {uuid: 800102, anchors: ['Right'], isTarget: true,maxConnections:2});
                this.plumb.addEndpoint('80011', {uuid: 80011, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80011', {uuid: 800112, anchors: ['Right'], isTarget: true,maxConnections:2});
                this.plumb.addEndpoint('80012', {uuid: 80012, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80012', {uuid: 800122, anchors: ['Right'], isTarget: true,maxConnections:2});
                this.plumb.addEndpoint('80013', {uuid: 80013, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80013', {uuid: 800132, anchors: ['Right'], isTarget: true,maxConnections:2});
                this.plumb.addEndpoint('80014', {uuid: 80014, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('80014', {uuid: 800142, anchors: ['Right'], isTarget: true,maxConnections:2});
                this.plumb.addEndpoint('80015', {uuid: 80015, anchors: ['Left'], isTarget: true,maxConnections:6});
                this.plumb.addEndpoint('80016', {uuid: 80016, anchors: ['Left'], isTarget: true,maxConnections:6});

                this.plumb.addEndpoint('80016', {uuid: 800162, anchors: ['Top'], isTarget: true});
                this.plumb.addEndpoint('80001', {uuid: 800012, anchors: ['Top'], isSource: true});

                this.plumb.connect({uuids: [80001, 80002],detachable: false});
            /*    this.plumb.connect({uuids: [800022, 80003],detachable: false});*/
                this.plumb.connect({uuids: [800022, 80004],detachable: false});
                this.plumb.connect({uuids: [800022, 80005],detachable: false});
                this.plumb.connect({uuids: [800022, 80006],detachable: false});
                this.plumb.connect({uuids: [800022, 80007],detachable: false});
                this.plumb.connect({uuids: [800022, 80008],detachable: false});

             /*   this.plumb.connect({uuids: [800032, 80009],detachable: false});*/
                this.plumb.connect({uuids: [800042, 80010],detachable: false});
                this.plumb.connect({uuids: [800052, 80011],detachable: false});
                this.plumb.connect({uuids: [800062, 80012],detachable: false});
                this.plumb.connect({uuids: [800072, 80013],detachable: false});
                this.plumb.connect({uuids: [800082, 80014],detachable: false});

              /*  this.plumb.connect({uuids: [800092, 80015],detachable: false});*/
                this.plumb.connect({uuids: [800102, 80015],detachable: false});
                this.plumb.connect({uuids: [800112, 80015],detachable: false});
                this.plumb.connect({uuids: [800122, 80015],detachable: false});
                this.plumb.connect({uuids: [800132, 80015],detachable: false});
                this.plumb.connect({uuids: [800142, 80015],detachable: false});

                this.plumb.connect({uuids: [800092, 80016],detachable: false});
                this.plumb.connect({uuids: [800102, 80016],detachable: false});
                this.plumb.connect({uuids: [800112, 80016],detachable: false});
                this.plumb.connect({uuids: [800122, 80016],detachable: false});
                this.plumb.connect({uuids: [800132, 80016],detachable: false});
                this.plumb.connect({uuids: [800142, 80016],detachable: false});

                this.plumb.connect({uuids: [800162, 800012],detachable: false});
            },
            initData(){
                $(".window").addClass("window_wbl"); //给所有节点设置未办理状态
                let params ={
                    xmid : this.xmid,
                    c_activityid : this.c_activityid
                }
                getAllInstInfo(params).then((res)=>{
                    if(res.data.list != undefined){
                        let list = res.data.list;
                        for(let i =0;i<list.length;i++){
                            let obj = list[i];
                            let id = "#"+obj.C_ACTIVITYID
                            const el = document.getElementById(obj.C_ACTIVITYID)
                            if(obj.RWBLR_STR != undefined){
                                let lxfs = obj.LXFS != undefined ? '('+obj.LXFS+')' : '';
                                el.setAttribute('rwblr',obj.RWBLR_STR + lxfs);
                            }else {
                                el.setAttribute('rwblr','');
                            }
                            if(obj.QSSJ_STR != undefined){
                                el.setAttribute('qssj',obj.QSSJ_STR);
                            }else {
                                el.setAttribute('qssj','');
                            }
                            if(obj.JSSJ_STR != undefined){
                                el.setAttribute('jssj',obj.JSSJ_STR);
                            }else {
                                el.setAttribute('jssj','');
                            }
                            if(obj.BJZT != undefined){
                                el.setAttribute('bjzt',obj.BJZT);
                            }else {
                                el.setAttribute('bjzt','');
                            }

                            if(obj.BJZT == '1'){ //任务已经完成 判断红黄绿灯情况
                                $(id).removeClass("window_wbl");
                                if(obj.SFHONGD == '1'){ //红灯
                                    $(id).addClass("window_ybl_red");
                                }else if(obj.SFHONGD == '0' && obj.SFHUANGD == '1'){ //黄灯
                                    $(id).addClass("window_ybl_yellow");
                                }else if(obj.SFHONGD == '0' && obj.SFHUANGD == '0'){ //绿灯
                                    $(id).addClass("window_ybl_green");
                                }
                            }else if(obj.BJZT == '0'){ //办理中
                                if(obj.SFHONGD == '1'){ //红灯
                                    $(id).addClass("window_ybl_red");
                                }else if(obj.SFHONGD == '0' && obj.SFHUANGD == '1'){ //黄灯
                                    $(id).addClass("window_ybl_yellow");
                                }else if(obj.SFHONGD == '0' && obj.SFHUANGD == '0'){ //绿灯
                                    $(id).addClass("window_ybl_green");
                                }
                                $(id).addClass("headerBox");
                            }
                        }
                    }
                })
            },
            initFloatPenal(){
                var panel = null;
                //创建panel框架
                panel = FloatPanel('panel').set({
                    header: {
                        content: this.content,
                    },
                    width:250,
                    floatHeightInvolve: true,
                    floatHeight: 20,
                    direction:'down',
                });
                $('.window').on('mouseenter', function (event) {
                    if(event.currentTarget.getAttribute("bjzt") == undefined){
                        panel.hide(event);
                        return;
                    }
                    let content = "<div style='height:60px' id='panel'>"
                        + "<span>办理人："+event.currentTarget.getAttribute("rwblr")+"</span>"
                        + "<br><span>开始时间："+event.currentTarget.getAttribute("qssj")+"</span>";
                    if(event.currentTarget.getAttribute("bjzt") == '1'){
                        content += "<br><span>结束时间："+event.currentTarget.getAttribute("jssj")+"</span>";
                    }
                    content += "</div>";
                    panel.set({
                        body: {
                            content: content
                        },
                    });
                    panel.show(event,function(){

                    });
                })
                $('.window').on('mouseout', function (event) {
                    panel.hide(event);
                })
                $('#panel').on('mouseleave', function (event) {
                    panel.hide(event);
                })
                $('#flowDemo').on('mouseleave', function (event) {
                    panel.hide(event);
                })
            }

        },
        mounted() {
            this.plumb = jsPlumb.getInstance({
                Endpoint: ["Dot", {radius: 1}], //这个是控制连线终端那个小点的半径
                //  EndpointStyle: {fill: 'green', outlineStroke: 'darkgray', outlineWidth: 2},//点的样式
                PaintStyle: {stroke: '#B8B8B8', strokeWidth: 2},  //线的样式
                Connector: ["Flowchart", {curviness: 70}],//直线
                ConnectionOverlays: [
                    ["Arrow", {width: 8, length: 8, location: 1}],//箭头样式
                ],
                HoverPaintStyle:{strokeStyle:'green'},
                Container: "flowDemo"
            });
            this.initView();
            this.initData();
            this.initFloatPenal();
        }

    }
</script>
<style>
    @import "./activity.css";
    @import "./floatPanel.css";
</style>

<style>
    .flowDemo {
        width: 100%;
        height: 320px;
        /*border: 1px solid #000;*/
        position: relative;
    }
    .flowDemo:after{
        content: "";
        display: inline-block;
        width: 100%;
        height: 0;
    }

    .line_one {
        top: 2em;
    }
    .line_two {
        top: 6em;
    }
    .line_three {
        top: 10em;
    }
    .line_four {
        top: 12em;
    }
    .line_five {
        top: 16em;
    }
    .line_six {
        top: 20em;
    }
    .line_seven {
        top: 24em;
    }
    .rank_five_ {
        left: 75em;
    }

</style>
