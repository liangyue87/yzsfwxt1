<template>
    <el-row style="text-align: center">
        <el-col :span="24">
            <el-card class="box-card" style="height: 440px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                        移交
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
        name: "Khywsl",
        data() {
            return {
                c_activityid : '9'
            }
        },
        props: ['xmid'],
        methods: {
            //移交
            initView() {
                let html = "";
                html =
                    '<div class="window rank_one line_two " id="90001">施工单位发起移交单</div>' +
                    '<div class="window rank_two line_two " id="90003">分派专人审核</div>'+

                    '<div class="window rank_three line_one " id="90004">区域抄表员编辑手册</div>'+

                    '<div class="window rank_four line_one " id="90006">立册退回</div>'+

                    '<div class="window rank_three line_three " id="90005">分派专人审核不通过</div>'+

                    '<div class="window rank_one line_six " id="90009">表位移交至区所</div>' +
                    '<div class="window rank_two line_six " id="90010">区所确认表位移交</div>'
                ;
                document.getElementById("flowDemo").innerHTML = html;
                this.plumb.addEndpoint('90001', {uuid: 90001, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('90003', {uuid: 90003, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('90003', {uuid: 900031, anchors: ['Right'], isSource: true,maxConnections: 2});
                this.plumb.addEndpoint('90003', {uuid: 900032, anchors: ['Top'], isSource: true});

                this.plumb.connect({uuids: [90001, 90003],detachable: false});
                this.plumb.connect({uuids: [900021, 90003],detachable: false});
                this.plumb.addEndpoint('90004', {uuid: 90004, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('90004', {uuid: 900041, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('90005', {uuid: 90005, anchors: ['Left'], isSource: true});
                this.plumb.connect({uuids: [900031, 90004],detachable: false});
                this.plumb.connect({uuids: [900031, 90005],detachable: false});
                this.plumb.addEndpoint('90005', {uuid: 900051, anchors: ['Bottom'], isSource: true});
                this.plumb.addEndpoint('90001', {uuid: 900011, anchors: ['Bottom'], isSource: true});
                this.plumb.addEndpoint('90006', {uuid: 90006, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('90006', {uuid: 900061, anchors: ['Top'], isSource: true});
                this.plumb.connect({uuids: [900041, 90006],detachable: false});
                this.plumb.connect({uuids: [900061, 900032],detachable: false});
                this.plumb.connect({uuids: [900051, 900011],detachable: false,});
                this.plumb.addEndpoint('90009', {uuid: 90009, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('90010', {uuid: 90010, anchors: ['Left'], isSource: true});
                this.plumb.connect({uuids: [90009, 90010],detachable: false,});
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
                            let id = "#"+obj.C_ACTIVITYID;
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
                    header: {enabled: false},
                    footer: {enabled: false},
                    width:250,
                    floatHeightInvolve: true,
                    floatHeight: 20,
                    direction:'up',
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
    @import "./activity_kz.css";
    @import "./floatPanel.css";

</style>

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
    .lc {
        font-size: 30px;
    }
</style>
