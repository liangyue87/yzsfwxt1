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
                <div v-if="XqslSt">
                    <XQSLST :xmid="this.xmid" :xqsl="this.xqsl"></XQSLST>
                </div>
                <div v-if="XklxSt">
                    <XKLXST :xmid="this.xmid" :kclx="this.kclx"></XKLXST>
                </div>
            </el-card>
        </el-col>
    </el-row>


</template>


<script>
    import {getXmlctDqjd} from '../../api/Xmlct';
    import XQSLST from '@/views/nav3/Xqsl_1.vue';
    import XKLXST from '@/views/nav3/Xklx_2.vue';
    export default {
        name: "Xmlct",
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
                this.XklxSt=false;
                this.XqslSt=true;
            },
            getKclx(){
                this.XklxSt=true;
                this.XqslSt=false;

             },
        },
        mounted() {
            this.getDqjd();//当前节点

        },
        components: {
            'XQSLST':XQSLST,
            'XKLXST':XKLXST,
        }


    }
</script>

<style>
    .lc {
        font-size: 30px;
    }
</style>
