<template>
    <el-row style="text-align: center">
        <el-col :span="24">
            <el-card class="box-card" style="height: 440px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                        设计审图
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
                c_activityid: '3',
                activityId: '30006',
            }
        },
        props: ['xmid'],
        zxmid: '',
        methods: {
            //设计审图
            initView() {
                let html = "";
                html =
                    '<div class="window rank_two line_two " id="30001" >委托设计</div>' +
                    '<div class="window rank_three line_two " id="30002" >分派设计专人</div>' +
                    '<div class="window rank_four line_two " id="30003">设计成果上传</div>' +
                    '<div class="window rank_two line_four " id="30005" >发起管网信息调查</div>' +
                    '<div class="window rank_three line_four " id="30006">管网信息反馈</div>' +
                    '<div class="window rank_two line_six " id="30020">会审结果录入</div>'
                ;
                document.getElementById("flowDemo").innerHTML = html;
                this.plumb.addEndpoint('30001', {uuid: 30001, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('30002', {uuid: 30002, anchors: ['Left'], isSource: true});
                this.plumb.addEndpoint('30002', {uuid: 300021, anchors: ['Right'], isSource: true});
                //this.plumb.addEndpoint('30002', {uuid: 300022, anchors: ['Bottom'], isSource: true});
                this.plumb.addEndpoint('30003', {uuid: 30003, anchors: ['Left'], isSource: true});
                //this.plumb.addEndpoint('30003', {uuid: 300031, anchors: ['Bottom'], isSource: true});
                this.plumb.connect({uuids: [30001, 30002], detachable: false});
                this.plumb.connect({uuids: [300021, 30003], detachable: false});
                //this.plumb.connect({uuids: [300031, 300022], detachable: false});
                this.plumb.addEndpoint('30005', {uuid: 30005, anchors: ['Right'], isSource: true});
                this.plumb.addEndpoint('30006', {uuid: 30006, anchors: ['Left'], isSource: true});
                //this.plumb.addEndpoint('30005', {uuid: 300051, anchors: ['Bottom'], isSource: true});
                //this.plumb.addEndpoint('30006', {uuid: 300061, anchors: ['Bottom'], isSource: true});
                this.plumb.connect({uuids: [30005, 30006], detachable: false});
                //this.plumb.connect({uuids: [300061, 300051], detachable: false});
            },
            initData() {
                $(".window").addClass("window_wbl"); //给所有节点设置未办理状态
                let params = {
                    xmid: this.xmid,
                    c_activityid: this.c_activityid
                };
                getAllInstInfo(params).then((res) => {
                    var _this = this;
                    if (res.data.list != undefined) {
                        let list = res.data.list;
                        for (let i = 0; i < list.length; i++) {
                            let obj = list[i];
                            let id = "#" + obj.C_ACTIVITYID;
                            const el = document.getElementById(obj.C_ACTIVITYID)
                            if (obj.RWBLR_STR != undefined) {
                                let lxfs = obj.LXFS != undefined ? '(' + obj.LXFS + ')' : '';
                                el.setAttribute('rwblr', obj.RWBLR_STR + lxfs);
                            } else {
                                el.setAttribute('rwblr', '');
                            }
                            if (obj.QSSJ_STR != undefined) {
                                el.setAttribute('qssj', obj.QSSJ_STR);
                            } else {
                                el.setAttribute('qssj', '');
                            }
                            if (obj.JSSJ_STR != undefined) {
                                el.setAttribute('jssj', obj.JSSJ_STR);
                            } else {
                                el.setAttribute('jssj', '');
                            }
                            if (obj.BJZT != undefined) {
                                el.setAttribute('bjzt', obj.BJZT);
                            } else {
                                el.setAttribute('bjzt', '');
                            }

                            if (obj.BJZT == '1') { //任务已经完成 判断红黄绿灯情况
                                $(id).removeClass("window_wbl");
                                var xm = this.xmid;
                                $(id).click(function () {//Gwxxfqlbsj
                                    $(id).addClass("pointer");
                                    if (id == "#30020") {
                                        _this.$router.push({path: '/Ckhsjglr', query: {xmid: xm}});
                                    }
                                    if (id == "#30006") {
                                        _this.$router.push({path: '/Ckgwxxfk', query: {xmid: xm,lctbh:'gwfksj'}});
                                    }
                                    if(id=="#30003"){
                                        _this.$router.push({path: '/lookscsjcg', query: {xmid: xm,activityId: '30003', sjstrzwid: obj.REFID, isxmtc: "2"}})
                                    }
                                });
                                if (obj.SFHONGD == '1') { //红灯
                                    $(id).addClass("window_ybl_red");
                                } else if (obj.SFHONGD == '0' && obj.SFHUANGD == '1') { //黄灯
                                    $(id).addClass("window_ybl_yellow");
                                } else if (obj.SFHONGD == '0' && obj.SFHUANGD == '0') { //绿灯
                                    $(id).addClass("window_ybl_green");
                                }

                            } else if (obj.BJZT == '0') { //办理中
                                if (obj.SFHONGD == '1') { //红灯
                                    $(id).addClass("window_ybl_red");
                                } else if (obj.SFHONGD == '0' && obj.SFHUANGD == '1') { //黄灯
                                    $(id).addClass("window_ybl_yellow");
                                } else if (obj.SFHONGD == '0' && obj.SFHUANGD == '0') { //绿灯
                                    $(id).addClass("window_ybl_green");
                                }
                                $(id).addClass("headerBox");
                            }
                        }
                    }
                })
            },
            initFloatPenal() {
                var panel = null;
                //创建panel框架
                panel = FloatPanel('panel').set({
                    header: {enabled: false},
                    footer: {enabled: false},
                    width: 250,
                    floatHeightInvolve: true,
                    floatHeight: 20,
                    direction: 'up',
                });
                $('.window').on('mouseenter', function (event) {
                    if (event.currentTarget.getAttribute("bjzt") == undefined) {
                        panel.hide(event);
                        return;
                    }
                    let content = "<div style='height:60px' id='panel'>"
                        + "<span>办理人：" + event.currentTarget.getAttribute("rwblr") + "</span>"
                        + "<br><span>开始时间：" + event.currentTarget.getAttribute("qssj") + "</span>";
                    if (event.currentTarget.getAttribute("bjzt") == '1') {
                        content += "<br><span>结束时间：" + event.currentTarget.getAttribute("jssj") + "</span>";
                    }
                    content += "</div>";
                    panel.set({
                        body: {
                            content: content
                        },
                    });
                    panel.show(event, function () {

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
            },
            /*  getLcjd(type) {
                  alert(type);
                  if (type == 30005) {
                      this.$router.push({path: '/Fqgwxxdc', query: {}});
                  }
              },*/

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
