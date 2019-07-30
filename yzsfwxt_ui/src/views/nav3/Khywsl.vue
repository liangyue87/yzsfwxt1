<template>
    <el-row style="text-align: center">
        <el-col :span="24" style="margin-top: 40px">
            <div class="lc">供水新装业务报建协同系统主流程</div>
            <!-- 步骤组件 -->
            <el-card class="box-card" style="margin-top: 50px;height: 200px" body-style="{}">
                <!--       <div slot="header" class="clearfix"  >
                          <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">地区</div>
                       </div>-->
                <div class="text item">
                    <el-row class="row" style="margin-top: 50px;">
                        <el-steps :space="150" class="step" :align-center=juzhong direction=horizontal
                                  :process-status=dqzt :finish-status=jszt>
                            <el-step title="需求受理" description="" :status=xqsl @click.native="getXqsl()"></el-step>
                            <el-step title="勘查立项" description="" :status=kclx @click.native="getKclx()"></el-step>
                            <el-step title="设计审图" description="" :status=sjst></el-step>
                            <el-step title="委托预算" description="" :status=wtys></el-step>
                            <el-step title="合同签订" description="" :status=htqd></el-step>
                            <el-step title="收费开票" description="" :status=sfkp></el-step>
                            <el-step title="施工竣工" description="" :status=sgjg></el-step>
                            <el-step title="综合验收" description="" :status=zhys></el-step>
                            <el-step title="移交" description="" :status=yj></el-step>
                            <el-step title="工程评定" description="" :status=gcpd></el-step>
                            <el-step title="资料归档" description="" :status=zlgd></el-step>
                            <el-step title="结算" description="" :status=js></el-step>
                        </el-steps>
                    </el-row>
                </div>
            </el-card>
            <el-card class="box-card" style="margin-top: 30px; height: 380px" body-style="{}">
                <div slot="header" class="clearfix">
                    <!-- <span >地区</span> -->
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;text-align: left">
                        {{title}}
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

            </el-card>
        </el-col>
    </el-row>


</template>


<script>
    import {jsPlumb} from 'jsplumb'
    import {getXmlctDqjd, selectXqsl} from '../../api/Xmlct';

    export default {
        name: "Khywsl",
        data() {
            return {
                //  lcjd:'',//完成状态
                juzhong: true, //样式居中
                dqzt: '',//完成状态样式
                jszt: '',//结束状态 success，finish
                xmid: '4c67a9da482641f08131e652eda3f7ec',//4c67a9da482641f08131e652eda3f7ec   7035f0ed9615491998a4cf12f139ccd1
                plumb: '',
                title: '',
                //success 绿色表示完成，finish 蓝色表示真在进行，wait 灰色表示还未开始
                xqsl: 'wait', kclx: 'wait', sjst: 'wait', wtys: 'wait', htqd: 'wait', sfkp: 'wait',
                sgjg: 'wait', zhys: 'wait', yj: 'wait', gcpd: 'wait', zlgd: 'wait', js: 'wait',

               /* xqsl_tx: '', xqsl_fp: '', xqsl_qs: '',*/
                XqslSt: false, XklxSt: false,
            }
        },
        methods: {
            //综合模块
            getDqjd() {
                let params = Object.assign({xmid: this.xmid});
                getXmlctDqjd(params).then((res) => {
                    let taskDq = eval(res.data.taskDq);//当前节点
                    let taskLs = eval(res.data.taskLs);//历史完成节点
                    if (taskLs != null) {//历史完成节点 显示绿色背景已完成
                        //   this.lcjd=taskLs[0].c_activityid.substr(0,1);
                        for (var a in taskLs) {
                            let Lsid = taskLs[a].c_activityid;
                            let newlsid = Lsid.substr(0, 1);
                            if (newlsid == '1') {
                                this.xqsl = 'success';
                            } else if (newlsid == '2') {
                                this.kclx = 'success';
                            } else if (newlsid == '3') {
                                this.sjst = 'success';
                            } else if (newlsid == '4') {
                                this.wtys = 'success';
                            } else if (newlsid == '5') {
                                this.htqd = 'success';
                            } else if (newlsid == '6') {
                                this.sfkp = 'success';
                            } else if (newlsid == '7') {
                                this.sgjg = 'success';
                            } else if (newlsid == '8') {
                                this.zhys = 'success';
                            } else if (newlsid == '9') {
                                this.yj = 'success';
                            } else if (newlsid == 'A') {
                                this.gcpd = 'success';
                            } else if (newlsid == 'B') {
                                this.zlgd = 'success';
                            } else if (newlsid == 'C') {
                                this.js = 'success';
                            } else {
                            }
                        }
                    }
                    if (taskDq != null) {//当前节点 显示蓝色背景真在进行
                        for (var i in taskDq) {     //
                            let Dqid = taskDq[i].c_activityid;
                            let newlcid = Dqid.substr(0, 1);
                            if (newlcid == '1') {
                                this.xqsl = 'finish';
                            } else if (newlcid == '2') {
                                this.kclx = 'finish';
                            } else if (newlcid == '3') {
                                this.sjst = 'finish';
                            } else if (newlcid == '4') {
                                this.wtys = 'finish';
                            } else if (newlcid == '5') {
                                this.htqd = 'finish';
                            } else if (newlcid == '6') {
                                this.sfkp = 'finish';
                            } else if (newlcid == '7') {
                                this.sgjg = 'finish';
                            } else if (newlcid == '8') {
                                this.zhys = 'finish';
                            } else if (newlcid == '9') {
                                this.yj = 'finish';
                            } else if (newlcid == 'A') {
                                this.gcpd = 'finish';
                            } else if (newlcid == 'B') {
                                this.zlgd = 'finish';
                            } else if (newlcid == 'C') {
                                this.js = 'finish';
                            } else {
                            }
                        }
                    } else {
                    }

                });
            },

            getXqsl(){
                this.title = "需求受理";
                let params = Object.assign({xmid: this.xmid});
                selectXqsl(params).then((res) => {
                    let taskDq = eval(res.data.taskDq);//当前节点
                    let taskLs = eval(res.data.taskLs);//历史完成节点
                    if(taskDq!=""){
                        let actid= taskDq[0].c_activityid.substr(4,1);
                        if(actid=="1"||actid=="4"||actid=="7") {//居民
                            let html =
                            html='<div class="window" name="10001" id="flowchartWindow33">居民科填报资料</div>'+
                                '<div class="window" name="10004" id="flowchartWindow44">居民科分派任务</div>' +
                                '<div class="window" name="10007" id="flowchartWindow55">居民科签收任务</div>';
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
            //需求受理 模块
            /*           getXqsl() {
                if (this.xqsl == 'wait') {//灰色
                    return;
                }
                this.XklxSt = false;
                this.XqslSt = true;
                this.title = "需求受理";
                let params = Object.assign({xmid: this.xmid});
                selectXqsl(params).then((res) => {
                    let taskDq = eval(res.data.taskDq);//当前节点
                    let taskLs = eval(res.data.taskLs);//历史完成节点
                    if (taskLs != "") {
                        this.xqsl_tx = taskLs[0].c_activityid;
                        this.xqsl_fp = taskLs[1].c_activityid;
                        this.xqsl_qs = taskLs[2].c_activityid;
                    }
                    if (taskDq != "") {
                        let actid = taskDq[0].c_activityid.substr(4, 1);
                        if (actid == "1" || actid == "2" || actid == "3") {
                            if(actid == "1"){
                                this.xqsl_tx = "居民科填报资料";
                                this.xqsl_fp = "居民科分派任务";
                                this.xqsl_qs = "居民科签收任务";
                            }
                            if(actid == "2"){
                                this.xqsl_tx = "工商科填报资料";
                                this.xqsl_fp = "工商科分派任务";
                                this.xqsl_qs = "工商科签收任务";
                            }
                            if(actid == "3"){
                                this.xqsl_tx = "户改科填报资料";
                                this.xqsl_fp = "户改科分派任务";
                                this.xqsl_qs = "户改科签收任务";
                            }
                            document.getElementById("flowchartWindow33").style.background="green";
                        }
                        if (actid == "4" || actid == "5" || actid == "6") {
                            if(actid == "4"){
                                this.xqsl_tx = "居民科填报资料";
                                this.xqsl_fp = "居民科分派任务";
                                this.xqsl_qs = "居民科签收任务";
                            }
                            if(actid == "5"){
                                this.xqsl_tx = "工商科填报资料";
                                this.xqsl_fp = "工商科分派任务";
                                this.xqsl_qs = "工商科签收任务";
                            }
                            if(actid == "6"){
                                this.xqsl_tx = "户改科填报资料";
                                this.xqsl_fp = "户改科分派任务";
                                this.xqsl_qs = "户改科签收任务";
                            }
                            document.getElementById("flowchartWindow44").style.background="green";
                        }
                        if (actid == "7" || actid == "8" || actid == "9") {
                            if(actid == "7"){
                                this.xqsl_tx = "居民科填报资料";
                                this.xqsl_fp = "居民科分派任务";
                                this.xqsl_qs = "居民科签收任务";
                            }
                            if(actid == "8"){
                                this.xqsl_tx = "工商科填报资料";
                                this.xqsl_fp = "工商科分派任务";
                                this.xqsl_qs = "工商科签收任务";
                            }
                            if(actid == "9"){
                                this.xqsl_tx = "户改科填报资料";
                                this.xqsl_fp = "户改科分派任务";
                                this.xqsl_qs = "户改科签收任务";
                            }
                            document.getElementById("flowchartWindow55").style.background="green";
                        }
                    }
                    this.plumb.addEndpoint('flowchartWindow33', {uuid: 3, anchors: ['Right'], isSource: true});
                    this.plumb.addEndpoint('flowchartWindow44', {uuid: 4, anchors: ['Left'], isTarget: true});
                    this.plumb.connect({uuids: [3, 4], detachable: false});
                    this.plumb.addEndpoint('flowchartWindow44', {uuid: 4.1, anchors: ['Right'], isTarget: true});
                    this.plumb.addEndpoint('flowchartWindow55', {uuid: 5, anchors: ['Left'], isTarget: true});
                    this.plumb.connect({uuids: [4.1, 5], detachable: false});
                });
            },
            getKclx() {
                this.XqslSt = false;
                this.XklxSt = true;

            },*/
        /*     getXqsl() {
                  if(this.xqsl=='wait'){//灰色 未
                      return;
                  }
                  this.title = "需求受理";
                      let params = Object.assign({xmid: this.xmid});
                      selectXqsl(params).then((res) => {
                          let taskDq = eval(res.data.taskDq);//当前节点
                          let taskLs = eval(res.data.taskLs);//历史完成节点
                          if(taskLs!=""){
                              let html =
                                  '<div class="window" id="flowchartWindow33">'+taskLs[0].c_activityid+'</div>' +
                                  '<div class="window" id="flowchartWindow44">'+taskLs[1].c_activityid+'</div>' +
                                  '<div class="window" id="flowchartWindow55">'+taskLs[2].c_activityid+'</div>';
                              document.getElementById("flowchart-demo").innerHTML = html;
                          }
                          if(taskDq!=""){
                             let actid= taskDq[0].c_activityid.substr(4,1);
                              if(actid=="1"||actid=="4"||actid=="7"){//居民
                                  let html ="";
                                  if(actid=="1"){
                                      html='<div class="window" id="flowchartWindow33" style="background: #00a65a">居民科填报资料</div>'+
                                          '<div class="window" id="flowchartWindow44">居民科分派任务</div>' +
                                          '<div class="window" id="flowchartWindow55">居民科签收任务</div>';
                                  }
                                  if(actid=="4"){
                                      html='<div class="window" id="flowchartWindow33">居民科填报资料</div>'+
                                          '<div class="window" id="flowchartWindow44" style="background: #00a65a">居民科分派任务</div>' +
                                          '<div class="window" id="flowchartWindow55">居民科签收任务</div>';
                                  }
                                  if(actid=="7"){
                                      html='<div class="window" id="flowchartWindow33">居民科填报资料</div>'+
                                          '<div class="window" id="flowchartWindow44">居民科分派任务</div>' +
                                          '<div class="window" id="flowchartWindow55" style="background: #00a65a">居民科签收任务</div>';
                                  }
                                  document.getElementById("flowchart-demo").innerHTML = html;
                              }
                             if(actid=="2"||actid=="5"||actid=="8"){
                                 let html ="";
                                 if(actid=="2"){
                                     html='<div class="window" id="flowchartWindow33" style="background: #00a65a">工商科填报资料</div>'+
                                         '<div class="window" id="flowchartWindow44">工商科分派任务</div>' +
                                         '<div class="window" id="flowchartWindow55">工商科签收任务</div>';
                                 }
                                 if(actid=="5"){
                                     html='<div class="window" id="flowchartWindow33">工商科填报资料</div>'+
                                         '<div class="window" id="flowchartWindow44" style="background: #00a65a">工商科分派任务</div>' +
                                         '<div class="window" id="flowchartWindow55">工商科签收任务</div>';
                                 }
                                 if(actid=="8"){
                                     html='<div class="window" id="flowchartWindow33">工商科填报资料</div>'+
                                         '<div class="window" id="flowchartWindow44">工商科分派任务</div>' +
                                         '<div class="window" id="flowchartWindow55" style="background: #00a65a">工商科签收任务</div>';
                                 }
                              }
                              if(actid=="3"||actid=="6"||actid=="9"){
                                  let html ="";
                                  if(actid=="3"){
                                      html='<div class="window" id="flowchartWindow33" style="background: #00a65a">工商科填报资料</div>'+
                                          '<div class="window" id="flowchartWindow44">工商科分派任务</div>' +
                                          '<div class="window" id="flowchartWindow55">工商科签收任务</div>';
                                  }
                                  if(actid=="6"){
                                      html='<div class="window" id="flowchartWindow33">工商科填报资料</div>'+
                                          '<div class="window" id="flowchartWindow44" style="background: #00a65a">工商科分派任务</div>' +
                                          '<div class="window" id="flowchartWindow55">工商科签收任务</div>';
                                  }
                                  if(actid=="9"){
                                      html='<div class="window" id="flowchartWindow33">工商科填报资料</div>'+
                                          '<div class="window" id="flowchartWindow44">工商科分派任务</div>' +
                                          '<div class="window" id="flowchartWindow55" style="background: #00a65a">工商科签收任务</div>';
                                  }
                              }
                          }
                          this.plumb.addEndpoint('flowchartWindow33', {uuid: 33, anchors: ['Right'], isSource: true});
                          this.plumb.addEndpoint('flowchartWindow44', {uuid: 44, anchors: ['Left'], isTarget: true});
                          this.plumb.connect({uuids: [33, 44],detachable: false});
                          this.plumb.addEndpoint('flowchartWindow44', {uuid: 44.1, anchors: ['Right'], isTarget: true});
                          this.plumb.addEndpoint('flowchartWindow55', {uuid: 55, anchors: ['Left'], isTarget: true});
                          this.plumb.connect({uuids: [44.1, 55],detachable: false});
                      });

              },*/
            //勘察立项
           /*     getKclx() {
            /!*        if (this.kclx == 'wait') {
                        return;
                    }*!/
                    this.title = "勘察立项";
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
                }*/

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
            this.getDqjd();//当前节点
        }

    }
</script>

<style>
    .flowchart-demo {
        width: 800px;
        height: 350px;
        /*border: 1px solid #000;*/
        position: relative;
    }

    .flowchart-demo .window {
        border: 1px solid #346789;
        box-shadow: 2px 2px 19px #aaa;
        -o-box-shadow: 2px 2px 19px #aaa;
        -webkit-box-shadow: 2px 2px 19px #aaa;
        -moz-box-shadow: 2px 2px 19px #aaa;
        -moz-border-radius: 0.5em;
        border-radius: 0.5em;
        opacity: 0.8;
        filter: alpha(opacity=80);
        text-align: center;
        position: absolute;
        background-color: #eeeeef;
        color: black;
        font-family: helvetica;
        font-size: 0.9em;
        line-height: 35px;
        width: 110px;
        height: 35px;
    }

    .flowchart-demo .window:hover {
        box-shadow: 2px 2px 19px #444;
        -o-box-shadow: 2px 2px 19px #444;
        -webkit-box-shadow: 2px 2px 19px #444;
        -moz-box-shadow: 2px 2px 19px #444;
        opacity: 0.6;
        filter: alpha(opacity=60);
    }

    .flowchart-demo .active {
        border: 1px dotted green;
    }

    .flowchart-demo .hover {
        border: 1px dotted red;
    }

    #flowchartWindow1 {
        top: 2em;
        left: 1em;
    }

    #flowchartWindow11 {
        top: 7em;
        left: 1em;
    }

    #flowchartWindow111 {
        top: 12em;
        left: 1em;
    }

    #flowchartWindow1111 {
        top: 17em;
        left: 1em;
    }

    #flowchartWindow2 { /*12*/
        top: 2em;
        left: 14em;
    }

    #flowchartWindow22 { /*12*/
        top: 7em;
        left: 14em;
    }

    #flowchartWindow222 { /*12*/
        top: 12em;
        left: 14em;
    }

    #flowchartWindow2222 { /*12*/
        top: 17em;
        left: 14em;
    }

    #flowchartWindow3 {
        top: 2em;
        left: 28em;
    }

    #flowchartWindow33 {
        top: 7em;
        left: 28em;
    }

    #flowchartWindow333 {
        top: 12em;
        left: 28em;
    }

    #flowchartWindow3333 {
        top: 17em;
        left: 28em;
    }

    #flowchartWindow4 {
        top: 2em;
        left: 42em;
    }

    #flowchartWindow44 {
        top: 7em;
        left: 42em;
    }

    #flowchartWindow444 {
        top: 12em;
        left: 42em;
    }

    #flowchartWindow4444 {
        top: 17em;
        left: 42em;
    }

    #flowchartWindow5 {
        top: 2em;
        left: 56em;
    }

    #flowchartWindow55 {
        top: 7em;
        left: 56em;
    }

    #flowchartWindow555 {
        top: 12em;
        left: 56em;
    }

    #flowchartWindow5555 {
        top: 17em;
        left: 56em;
    }

    #flowchartWindow6 {
        top: 2em;
        left: 70em;
    }

    #flowchartWindow66 {
        top: 7em;
        left: 70em;
    }

    #flowchartWindow666 {
        top: 12em;
        left: 70em;
    }

    #flowchartWindow6666 {
        top: 17em;
        left: 70em;
    }

    #flowchartWindow7 {
        top: 2em;
        left: 84em;
    }

    #flowchartWindow77 {
        top: 7em;
        left: 84em;
    }

    #flowchartWindow777 {
        top: 12em;
        left: 84em;
    }

    #flowchartWindow7777 {
        top: 17em;
        left: 84em;
    }

    #flowchartWindow8 {
        top: 2em;
        left: 98em;
    }

    #flowchartWindow88 {
        top: 7em;
        left: 98em;
    }

    #flowchartWindow888 {
        top: 12em;
        left: 98em;
    }

    #flowchartWindow8888 {
        top: 17em;
        left: 98em;
    }
    .lc {
        font-size: 30px;
    }
</style>
