<template>
    <el-row style="text-align: center">
        <el-col :span="24" style="margin-top: 30px">
            <div class="lc">
                <div style="display: inline">{{this.xmmc}}流程全景图</div>
                <el-tooltip class="item" effect="light" placement="bottom">
                    <div slot="content"><b>大环节说明</b><br>
                        <b>绿色：</b>已经执行过&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>蓝色：</b>正在执行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>灰色：</b>未执行<p></p>
                        <b>子环节说明</b><br>
                        <b>绿色：</b>该环节在绿灯办结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>绿色闪烁：</b>目前在办任务并且是绿灯范围<br>
                        <b>黄色：</b>该环节在黄灯办结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>黄色闪烁：</b>目前在办任务并且是黄灯范围<br>
                        <b>红色：</b>该环节在红灯办结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>红色闪烁：</b>目前在办任务并且是红灯范围<br>
                        <b>灰色：</b>未执行过该任务
                    </div>
                    <img style="height: 22px;width: 22px;" src="../../../static/img/help.png">
                </el-tooltip>
                <div style="font-size: 20px;margin-top: 10px;color: #4A4A4A">{{this.xmbh}}</div>
            </div>
            <div class="lcc" style="text-align: right;">
                <i class="el-icon-s-opportunity lignt_yellow" style="margin-right: 15px;font-size: 20px;">{{yellow}}</i>
                <i class="el-icon-s-opportunity lignt_red" style="margin-right: 25px;font-size: 20px;">{{red}}</i>
            </div>
            <!-- 步骤组件 -->
            <el-card class="box-card" style="margin-top: 10px;height: 140px" body-style="{}">
                <!--       <div slot="header" class="clearfix"  >
                          <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">地区</div>
                       </div>-->
                <div class="text item">
                    <el-row class="row" style="margin-top: 10px;">
                        <el-steps :space="150" class="step" :align-center=juzhong direction=horizontal
                                  :process-status=dqzt :finish-status=jszt>
                            <el-step id="aa" title="需求受理" :description="xqsl_num" :status=xqsl @click.native="getLcjd('1')"></el-step>
                            <el-step title="勘查立项" :description="kclx_num" :status=kclx @click.native="getLcjd('2')"></el-step>
                            <el-step title="设计审图" :description="sjst_num" :status=sjst @click.native="getLcjd('3')"></el-step>
                            <el-step title="委托预算" :description="wtys_num" :status=wtys @click.native="getLcjd('4')"></el-step>
                            <el-step title="合同签订" :description="htqd_num" :status=htqd @click.native="getLcjd('5')"></el-step>
                            <el-step title="收费开票" :description="sfkp_num" :status=sfkp @click.native="getLcjd('6')"></el-step>
                            <el-step title="施工竣工" :description="sgjg_num" :status=sgjg @click.native="getLcjd('7')"></el-step>
                            <el-step title="综合验收" :description="zhys_num" :status=zhys @click.native="getLcjd('8')"></el-step>
                            <el-step title="移交" :description="yj_num" :status=yj @click.native="getLcjd('9')"></el-step>
                            <el-step title="工程评定" :description="gcpd_num" :status=gcpd @click.native="getLcjd('A')"></el-step>
                      <!--      <el-step title="资料归档" description="" :status=zlgd @click.native="getLcjd('B')"></el-step>-->
                            <el-step title="结算" :description="js_num" :status=js @click.native="getLcjd('C')"></el-step>
                        </el-steps>
                    </el-row>
                </div>
            </el-card>
            <div style="margin-top: 10px"></div>
                <div v-if="type=='1'">
                    <XQSL :xmid="this.xmid"></XQSL>
                </div>
                <div v-if="type=='2'">
                    <XKYLX :xmid="this.xmid"></XKYLX>
                </div>
                <div v-if="type=='3'">
                    <SJST :xmid="this.xmid"></SJST>
                </div>
                <div v-if="type=='4'">
                    <WTYS :xmid="this.xmid" ></WTYS>
                </div>
                <div v-if="type=='5'">
                    <HTQD :xmid="this.xmid"></HTQD>
                </div>
                <div v-if="type=='6'">
                    <SFKP :xmid="this.xmid"></SFKP>
                </div>
                <div v-if="type=='7'">
                    <SGJG :xmid="this.xmid"></SGJG>
                </div>
                <div v-if="type=='8'">
                    <ZHYS :xmid="this.xmid"></ZHYS>
                </div>
                <div v-if="type=='9'">
                    <YJ :xmid="this.xmid"></YJ>
                </div>
                <div v-if="type=='A'">
                    <GCPD :xmid="this.xmid"></GCPD>
                </div>
                <div v-if="type=='B'">
                    <ZLGD :xmid="this.xmid"></ZLGD>
                </div>
                <div v-if="type=='C'">
                    <JS :xmid="this.xmid"></JS>
                </div>
        </el-col>
    </el-row>


</template>


<script>
    import {getXmlctDqjd,getAllInstInfoNum,getXmmcByid} from '../../api/work';
    let FloatPanel = require('../../common/js/floatPanel');
    import XQSL from '@/views/work/template/1_xqsl.vue';
    import XKYLX from '@/views/work/template/2_xkylx.vue';
    import SJST from '@/views/work/template/3_sjst.vue';
    import WTYS from '@/views/work/template/4_wtys.vue';
    import HTQD from '@/views/work/template/5_htqd.vue';
    import SFKP from '@/views/work/template/6_sfkp.vue';
    import SGJG from '@/views/work/template/7_sgjg.vue';
    import ZHYS from '@/views/work/template/8_zhys.vue';
    import YJ from '@/views/work/template/9_yj.vue';
    import GCPD from '@/views/work/template/10_gcpd.vue';
    import ZLGD from '@/views/work/template/11_zlgd.vue';
    import JS from '@/views/work/template/12_js.vue';

    export default {
        name: "Xmlct",
        data() {
            return {
                //  lcjd:'',//完成状态
                juzhong: true, //样式居中
                dqzt: '',//完成状态样式
                jszt: '',//结束状态 success，finish
                xmid:this.$route.query.xmid,
                xmmc : this.$route.query.xmmc,
                xmbh : '',
              //  type:this.$route.query.type,
                   //  e7a14bfa648d43a3acc863633743443c
                plumb: '',
                title: '',
                //success 绿色表示完成，finish 蓝色表示真在进行，wait 灰色表示还未开始
                xqsl: 'wait', kclx: 'wait', sjst: 'wait', wtys: 'wait', htqd: 'wait', sfkp: 'wait',
                sgjg: 'wait', zhys: 'wait', yj: 'wait', gcpd: 'wait', zlgd: 'wait', js: 'wait',
                xqsl_num:'',kclx_num: '', sjst_num: '', wtys_num: '', htqd_num: '', sfkp_num: '',
                sgjg_num: '', zhys_num: '', yj_num: '', gcpd_num: '', zlgd_num: '', js_num: '',
                type:'',
                titleCount:'',yellow:0,red:0
            }
        },
        methods: {
            //综合模块
            getDqjd() {

                let params = Object.assign({xmid: this.xmid});
                getXmlctDqjd(params).then((res) => {
                    let taskDq = eval(res.data.taskDq);//当前节点
                    let taskLs = eval(res.data.taskLs);//历史完成节点
                    let sfkplixt = eval(res.data.sfkplixt);//收费开票
                    console.log(sfkplixt+"-----------------")
                    if(sfkplixt.length!=0){
                        this.sfkp='success';
                    }
                    if (taskLs != null) {//历史完成节点 显示绿色背景已完成
                        for (var a in taskLs) {
                            let Lsid = taskLs[a].c_activityid;
                            let newlsid = Lsid.substr(0, 1);
                            this.type=newlsid
                            if (newlsid == '1') {
                                this.xqsl = 'success';
                            } else if (newlsid == '2') {
                                this.kclx = 'success';
                                this.xqsl = 'success';
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
                            this.type=newlcid
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
                    }
                });

            },
            getInstNum(){
                getAllInstInfoNum({xmid:this.xmid}).then((res) => {
                    let list = res.data.list;
                    for(let i=0;i<list.length;i++){
                        switch (list[i].ACT) {
                            case '1':this.xqsl_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case '2':this.kclx_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case '3':this.sjst_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case '4':this.wtys_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case '5':this.htqd_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case '6':this.sfkp_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case '7':this.sgjg_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case '8':this.zhys_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case '9':this.yj_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case 'A':this.gcpd_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                            case 'C':this.js_num = "(黄:"+list[i].YELLOW+"  红:"+list[i].RED+")";this.yellow+=list[i].YELLOW;this.red+=list[i].RED;break;
                        }
                    }
                })
            },
            //模块跳转
            getLcjd(type){
                this.type=type;
            },
            getXmmcByid(){
                let params = Object.assign({xmid: this.xmid});
                getXmmcByid(params).then((res) => {
                    this.xmmc = res.getXmmcByid.XMMC;
                    this.xmbh = "(项目编号:"+res.getXmmcByid.XMBH+")";
                });
            },
        },
        mounted() {
            this.getDqjd();//当前节点
            this.getInstNum();
            this.getXmmcByid();

            //this.getLcjd('3');
        },
        components: {
            'XQSL':XQSL,
            'XKYLX':XKYLX,
            'SJST':SJST,
            'WTYS':WTYS,
            'HTQD':HTQD,
            'SFKP':SFKP,
            'SGJG':SGJG,
            'ZHYS':ZHYS,
            'YJ':YJ,
            'GCPD':GCPD,
            'ZLGD':ZLGD,
            'JS':JS,
        }


    }
</script>

<style>
    .lc {
        font-size: 30px;
    }
</style>
