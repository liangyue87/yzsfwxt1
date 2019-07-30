<template>

    <el-row style="text-align: center">
        <el-col :span="24">
            <el-card class="box-card" style="height: 380px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                        需求受理
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
    import {getAllInstInfo,getXqslByXmid} from '../../../api/work'
    let FloatPanel = require('../../../common/js/floatPanel');

    export default {
        data() {
            return {
                c_activityid : '1'
            }
        },
        props: ['xmid'],
        methods: {
            //勘察立项
            initView() {
                let html = "";
                html = '<div class="window rank_two line_one_0" id="10001">居民科填报客户资料</div>' +
                    '<div class="window rank_two line_three_0 " id="10002">工商科填报客户资料</div>' +
                    '<div class="window rank_two line_five_0 " id="10003">户改科填报客户资料</div>' +
                    '<div class="window rank_three line_one_0 " id="10004">居民科分派任务</div>' +
                    '<div class="window rank_three line_three_0 " id="10005">工商科分派任务</div>'+
                    '<div class="window rank_three line_five_0 " id="10006">户改科分派任务</div>' +
                    '<div class="window rank_four line_one_0 " id="10007">居民科签收任务</div>' +
                    '<div class="window rank_four line_three_0 " id="10008">工商科签收任务</div>' +
                    '<div class="window rank_four line_five_0 " id="10009">户改科签收任务</div>';

                document.getElementById("flowDemo").innerHTML = html;
                this.plumb.addEndpoint('10001', {uuid: 10001, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('10002', {uuid: 10002, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('10003', {uuid: 10003, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('10004', {uuid: 10004, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('10004', {uuid: 100042, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('10005', {uuid: 10005, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('10005', {uuid: 100052, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('10006', {uuid: 10006, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('10006', {uuid: 100062, anchors: ['Right'], isTarget: true});
                this.plumb.addEndpoint('10007', {uuid: 10007, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('10008', {uuid: 10008, anchors: ['Left'], isTarget: true});
                this.plumb.addEndpoint('10009', {uuid: 10009, anchors: ['Left'], isTarget: true});

                this.plumb.connect({uuids: [10001, 10004],detachable: false});
                this.plumb.connect({uuids: [100042, 10007],detachable: false});
                this.plumb.connect({uuids: [10002, 10005],detachable: false});
                this.plumb.connect({uuids: [100052, 10008],detachable: false});
                this.plumb.connect({uuids: [10003, 10006],detachable: false});
                this.plumb.connect({uuids: [100062, 10009],detachable: false});
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

                                if(id == '#10001' ||id == '#10002' ||id == '#10003'){
                                    $(id).addClass("pointer");
                                    $(id).click(function () {
                                        let params = {
                                            rwid:_this.$route.query.xmid
                                        };
                                        getXqslByXmid(params).then(res => {
                                            let rows = res.data.list[0];
                                            let para = Object.assign({rwid:_this.$route.query.xmid,ishx:'1'},rows);
                                            _this.$router.push({path: '/showxq', query: para});
                                        });
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
                $('.window').on('mouseleave', function (event) {
                    panel.hide(event);
                })
                $('#panel').on('mouseleave', function (event) {
                    panel.hide(event);
                })
            }

        },
        mounted() {
            this.plumb = jsPlumb.getInstance({
                Endpoint: ["Dot", {radius: 1}], //这个是控制连线终端那个小点的半径
                //  EndpointStyle: {fill: 'green', outlineStroke: 'darkgray', outlineWidth: 2},//点的样式
                PaintStyle: {stroke: '#B8B8B8', strokeWidth: 2},  //线的样式
                Connector: ["Straight", {curviness: 70}],//直线
                ConnectionOverlays: [
                    ["Arrow", {width: 8, length: 8, location: 1,
                        events:{
                            click:function() { alert("you clicked on the arrow overlay")}
                        }}],//箭头样式
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
    .line_one_0{
        top: 0em;
    }
    .line_three_0{
        top: 8em;
    }
    .line_five_0{
        top: 16em;
    }

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
</style>
