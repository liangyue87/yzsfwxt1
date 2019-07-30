<template>
    <el-row style="text-align: center">
        <el-col :span="24">
            <el-card class="box-card" style="height: 380px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                        收费开票
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
    import {getXmlctDqjd,querysfkp} from '../../../api/work'

    let FloatPanel = require('../../../common/js/floatPanel');

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
                html =
                    '<div class="window rank_three line_three " id="60002">收费开票</div>'
                ;
                document.getElementById("flowDemo").innerHTML = html;

            },
            initData(){

                $(".window").addClass("window_wbl"); //给所有节点设置未办理状态
                let params ={
                    xmid : this.xmid,
                    c_activityid : this.c_activityid
                }
                getXmlctDqjd(params).then((res)=>{
                    var _this = this;
                    let sfkplixt = eval(res.data.sfkplixt);//收费开票
                    if(sfkplixt.length!=0){
                        $("#60002").addClass("window_ybl_green");
                        const el = document.getElementById("60002");
                        el.setAttribute('rwblr',sfkplixt[0].zdr);
                   if(sfkplixt[0].username != undefined){
                            let lxfs = sfkplixt[0].lxfs != undefined ? '('+sfkplixt[0].lxfs+')' : '';
                            el.setAttribute('rwblr',sfkplixt[0].username+lxfs);
                        }else {
                            el.setAttribute('rwblr','');
                        }
                        if(sfkplixt[0].zdsj_str  != undefined){
                            el.setAttribute('qssj',sfkplixt[0].zdsj_str );
                        }else {
                            el.setAttribute('qssj','');
                        }
                        if(sfkplixt[0].zdsj_str  != undefined){
                            el.setAttribute('jssj',sfkplixt[0].zdsj_str );
                        }else {
                            el.setAttribute('jssj','');
                        }
                        $("#60002").addClass("pointer");
                        $("#60002").click(function () {
                            let param = {
                                xmid : _this.$route.query.xmid,
                                ishx : 1
                            };
                            _this.$router.push({path: '/Jftzd', query: param});
                            /*let params = {
                                xmid : _this.$route.query.xmid
                            };
                            querysfkp(params).then(res=>{
                                let para = {
                                    xmid : _this.$route.query.xmid,
                                    tzdid: res.sftzd.ID,
                                    azlx : res.xmxxList[0].azlx,
                                    sqlx : res.xmxxList[0].sqlx,
                                    lxr: res.xmxxList[0].lxr,
                                    lxrsjh:res.xmxxList[0].lxrsjh,
                                    ishx:'1'
                                };
                                _this.$router.push({path: '/editJftzd', query: para});
                            });*/
                        });
                    }
                  /*  if(res.data.list != undefined){
                        let list = res.data.list;
                        for(let i =0;i<list.length;i++){
                            let obj = list[i];
                            let id = "#"+obj.C_ACTIVITYID
                            const el = document.getElementById(obj.C_ACTIVITYID)
                            if(obj.RWBLR_STR != undefined){
                                el.setAttribute('rwblr',obj.RWBLR_STR);
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
                                $(id).removeClass("window_wbl").addClass("window_bl");
                            }
                        }
                    }*/
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
                    content += "<br><span>结束时间："+event.currentTarget.getAttribute("jssj")+"</span>";

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
                Connector: ["Straight", {curviness: 70}],//直线
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
