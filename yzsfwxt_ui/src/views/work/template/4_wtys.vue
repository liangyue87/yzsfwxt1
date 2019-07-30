<template>
    <el-row style="text-align: center">
        <el-col :span="24">
            <el-card class="box-card" style="height: 380px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                        委托预算
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
    import { getXxzl } from '../../../api/Yswt';
    import {queryPara} from '../../../api/yszjqt'
    let FloatPanel = require('../../../common/js/floatPanel');
    export default {
        name: "Khywsl",
        data() {
            return {
                c_activityid: '4',
                ysbz:[],
            }
        },
        props: ['xmid'],
        methods: {
            //委托预算
            initView() {
                let html = "";
                html = '<div class="window rank_one line_two " id="40001">预算委托</div>' +
                    '<div class="window rank_two line_two " id="40002">预算科分派人</div>' +
                    '<div class="window rank_three line_two " id="40003">预算编制</div>' +
                    '<div class="window rank_four line_two " id="40008">领导审批</div>'+
                    '<div class="window rank_five line_two " id="40081">签收</div>'+

                '<div class="window rank_one line_five " id="40010">发起造价洽谈</div>'+
                '<div class="window rank_two line_five " id="40011">工程造价洽谈</div>'+
               '<div class="window rank_three line_five " id="40012">用户确认</div>'
                ;
                document.getElementById("flowDemo").innerHTML = html;
                this.plumb.addEndpoint('40001', {uuid: 40001, anchors: ['Right'], isSource: true});
                //this.plumb.addEndpoint('40001', {uuid: 400011, anchors: ['Bottom'], isSource: true});
                this.plumb.addEndpoint('40002', {uuid: 40002, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('40002', {uuid: 400021, anchors: ['Right'], isSource: true});
                //this.plumb.addEndpoint('40002', {uuid: 400022, anchors: ['Bottom'], isSource: true});
                this.plumb.connect({uuids: [40001, 40002],detachable: false});
                //this.plumb.connect({uuids: [400022, 400011],detachable: false});
                this.plumb.addEndpoint('40003', {uuid: 40003, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('40003', {uuid: 400031, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [400021, 40003],detachable: false});
                this.plumb.addEndpoint('40008', {uuid: 40008, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('40008', {uuid: 400811, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [400031, 40008],detachable: false});
                this.plumb.addEndpoint('40081', {uuid: 40081, anchors: ['Left'], isSource: true});
                this.plumb.connect({uuids: [400811, 40081],detachable: false});
                this.plumb.addEndpoint('40010', {uuid: 40010, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('40011', {uuid: 40011, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('40011', {uuid: 400111, anchors: ['Right'], isSource: true});
                this.plumb.connect({uuids: [40010, 40011],detachable: false});
                this.plumb.addEndpoint('40012', {uuid: 40012, anchors: ['Left'], isSource: true});
                this.plumb.connect({uuids: [400111, 40012],detachable: false});
            },
            initData() {
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
                                var xm = this.xmid;
                                if(obj.SFHONGD == '1'){ //红灯
                                    $(id).addClass("window_ybl_red");
                                    $(id).click(function () {
                                        if (id == "#40003") {
                                            let params = {
                                                xmid: xm,//项目id
                                            }
                                            getXxzl(params).then(res => {
                                                this.ysbz = res.getXxzl;
                                                _this.$router.push({path: '/YSbzxq_qjt',query:{ tow1:this.ysbz,xmid:xm,type:'1' }})
                                            });
                                        }
                                    });
                                }else if(obj.SFHONGD == '0' && obj.SFHUANGD == '1'){ //黄灯
                                    $(id).addClass("window_ybl_yellow");
                                    $(id).click(function () {
                                        if (id == "#40003") {
                                            let params = {
                                                xmid: xm,//项目id
                                            }
                                            getXxzl(params).then(res => {
                                                this.ysbz = res.getXxzl;
                                                _this.$router.push({path: '/YSbzxq_qjt',query:{ tow1:this.ysbz,xmid:xm,type:'1' }})
                                            });
                                        }
                                    });
                                }else if(obj.SFHONGD == '0' && obj.SFHUANGD == '0'){ //绿灯
                                    $(id).addClass("window_ybl_green");
                                    $(id).click(function () {
                                        if (id == "#40003") {
                                            let params = {
                                                xmid: xm,//项目id
                                            }
                                            getXxzl(params).then(res => {
                                                this.ysbz = res.getXxzl;
                                                _this.$router.push({path: '/YSbzxq_qjt',query:{ tow1:this.ysbz,xmid:xm,type:'1' }})
                                            });
                                        }
                                    });
                                }
                                if(id == '#40011'){
                                    $(id).addClass("pointer");
                                    $(id).click(function () {
                                        let para = {xmid:xm};
                                        queryPara(para).then(res=>{
                                            _this.$router.push({path: '/YSZjqtCk', query: {xmid: xm,refid:res.data.list[0],
                                                    activityId : '40011',type:'2',qtjg:res.data.qtjgList[0]}});
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
                HoverPaintStyle: {strokeStyle: 'green'},
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

    .flowDemo:after {
        content: "";
        display: inline-block;
        width: 100%;
        height: 0;
    }

    .lc {
        font-size: 30px;
    }
</style>
