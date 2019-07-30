<template>
    <el-row style="text-align: center">
        <el-col :span="24">
            <el-card class="box-card" style="height: 380px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                        现勘与立项
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
    let FloatPanel = require('../../../common/js/floatPanel');
    import {getAllInstInfo} from '../../../api/work'

    export default {
        name: "Khywsl",
        data() {
            return {
                c_activityid : '2',
            }
        },
        props: ['xmid'],
        methods: {
            //勘察立项
            initView() {
                let html = "";
                html = '<div class="window rank_one line_three " id="20001">创建勘查任务</div>' +
                    '<div class="window rank_two_ line_one " id="20021">设计院分派勘查人员</div>' +
                    '<div class="window rank_two_ line_two " id="20022">施工单位分派勘查人员</div>' +
                    '<div class="window rank_two_ line_four " id="20023">区所分派勘查人员</div>' +
                    '<div class="window rank_two_ line_five " id="20024">预算科分派勘查人员</div>'+
                    '<div class="window rank_three_ line_one " id="20031">设计院反馈勘查结果</div>' +
                    '<div class="window rank_three_ line_two " id="20032">施工单位反馈勘查结果</div>' +
                    '<div class="window rank_three_ line_four " id="20033">区所反馈勘查结果</div>' +
                    '<div class="window rank_three_ line_five " id="20034">预算科反馈勘查结果</div>'+
                    '<div class="window rank_five line_three " id="20006">勘查反馈评价</div>'+
                    '<div class="window rank_six line_three " id="20007">客户确认勘查结果</div>'+
                    '<div class="window rank_one line_six " id="20008">立项</div>'
                ;
                document.getElementById("flowDemo").innerHTML = html;
                this.plumb.addEndpoint('20001', {uuid: 20001, anchors: ['Right'], isSource: true,maxConnections: 4});
                this.plumb.addEndpoint('20021', {uuid: 20021, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('20022', {uuid: 20022, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('20023', {uuid: 20023, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('20024', {uuid: 20024, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('20021', {uuid: 200211, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('20022', {uuid: 200221, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('20023', {uuid: 200231, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('20024', {uuid:200241, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('20031', {uuid:20031, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('20032', {uuid:20032, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('20033', {uuid:20033, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('20034', {uuid:20034, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('20031', {uuid:200311, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('20032', {uuid:200321, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('20033', {uuid:200331, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('20034', {uuid:200341, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('20006', {uuid:20006, anchors: ['Left'], isTarget: true,maxConnections: 4});
                this.plumb.addEndpoint('20006', {uuid:200061, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('20007', {uuid:20007, anchors: ['Left'], isTarget: true});
                this.plumb.connect({uuids: [20001, 20021],detachable: false});
                this.plumb.connect({uuids: [20001, 20022],detachable: false});
                this.plumb.connect({uuids: [20001, 20023],detachable: false});
                this.plumb.connect({uuids: [20001, 20024],detachable: false});
                this.plumb.connect({uuids: [200211, 20031],detachable: false});
                this.plumb.connect({uuids: [200221, 20032],detachable: false});
                this.plumb.connect({uuids: [200231, 20033],detachable: false});
                this.plumb.connect({uuids: [200241, 20034],detachable: false});
                this.plumb.connect({uuids: [200311, 20006],detachable: false});
                this.plumb.connect({uuids: [200321, 20006],detachable: false});
                this.plumb.connect({uuids: [200331, 20006],detachable: false});
                this.plumb.connect({uuids: [200341, 20006],detachable: false});
                this.plumb.connect({uuids: [200061, 20007],detachable: false});
            },
            initData(){
                $(".window").addClass("window_wbl"); //给所有节点设置未办理状态
                let params ={
                    xmid : this.xmid,
                    c_activityid : this.c_activityid
                }
                getAllInstInfo(params).then((res)=>{
                    var _this = this;
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
                                if(id == '#20031' ||id == '#20032' ||id == '#20033' ||id == '#20034'){
                                    $(id).addClass("pointer");
                                    $(id).click(function () {
                                        _this.$router.push({path: '/Zckfk', query: {xmid: _this.$route.query.xmid}});
                                    });
                                }else if(id == '#20006'){
                                    $(id).addClass("pointer");
                                    $(id).click(function () {
                                        _this.$router.push({path: '/Zckfk', query: {xmid: _this.$route.query.xmid,type: '5'}});
                                    });
                                }else if(id == '#20007'){
                                    $(id).addClass("pointer");
                                    $(id).click(function () {
                                        _this.$router.push({path: '/Zckfk', query: {xmid: _this.$route.query.xmid,type: '6'}});
                                    });
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
                                // $(id).removeClass("window_wbl").addClass("window_bl");
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
                        try {
                            panel.hide(event);
                        } catch (e) {
                        }
                        return;
                    }
                    let content = "<div style='height:60px'  id='panel'>"
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
                    try {
                        panel.hide(event);
                    } catch (e) {
                    }
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
