<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--红黄绿灯时间-->
                <hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
                <!--项目基本信息模板-->
                <XMJBXX></XMJBXX>
                <!--资料分类下载-->
                <ZLFLJXZ></ZLFLJXZ>
                <!--施工下单范围-->
                <SGXDXDFW ref="xdfw" @sbdy_vue="sbdy_vue"></SGXDXDFW>

                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">监理范围</div>
                    </div>
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.ty" :disabled="true">
                                    <el-checkbox label="庭院" name="ty"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.bf" :disabled="true">
                                    <el-checkbox label="泵房" name="bf"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.lg" :disabled="true">
                                    <el-checkbox label="立管" name="lg"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.dg" :disabled="true">
                                    <el-checkbox label="吊管" name="dg"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.szgw" :disabled="true">
                                    <el-checkbox label="市政管网" name="szgw"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.jlsb" :disabled="true">
                                    <el-checkbox label="计量水表" name="jlsb"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="其他">
                            <el-col :span="12">
                                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 5}" v-model="form.desc" :disabled="true"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>
                <el-row style="text-align: center;margin-top: 15px">
                    <router-link :to="{path:'/Backlog'}">
                        <el-button size="mini" type="info" @click="toReturn">
                            <i class="iconfont icon-quxiao"></i>取消</el-button>
                    </router-link>
                    <el-button size="mini" type="success" @click="toDy">
                        <i class="iconfont icon-dayin_f"></i>打印</el-button>
                    <router-link :to="tabUrl">
                        <el-button size="mini" type="primary" :loading="subLoading" @click="toSubmit">
                            <i class="iconfont icon-queren"></i>提交</el-button>
                    </router-link>
                    <el-button size="mini" type="primary" @click="goxmqj()" >
                        <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                    </el-button>
                </el-row>
            </el-col>
            <el-dialog title="" :visible.sync ="printFormVisible">
                <div id="printTest">
                    <table class="border-table">
                        <caption align="top"><span style="font-size: 20px;font-family: 黑体;">工程任务单</span></caption>
                        <tr>
                            <th colspan="2" align="left">工程编码：{{dy_gcbm}}</th>
                            <th colspan="2" align="left">日期：{{dy_rq}}</th>
                        </tr>
                        <tr>
                            <td height="40px" align="center">工程名称</td>
                            <td height="40px" colspan="3">{{dy_gcmc}}</td>
                        </tr>
                        <tr>
                            <td height="40px" align="center">工程地址</td>
                            <td height="40px" colspan="3">{{dy_gcdz}}</td>
                        </tr>
                        <tr>
                            <td height="40px" align="center">工程概况</td>
                            <td height="40px" colspan="3">
                                <div style="text-align:left;">
                                    <span>一、工程类型</span><br>
                                    <span>
                                        居民客户科&nbsp;{{dy_jmk}}&nbsp;&nbsp;
                                        工商客户科&nbsp;{{dy_gsk}}&nbsp;&nbsp;
                                        出户改造&nbsp;{{dy_hgk}}&nbsp;&nbsp;
                                    </span>
                                        安装类型：<span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;{{dy_azlx}}&nbsp;&nbsp;&nbsp;</span>
                                    <br>
                                    <span>一、建设模式：
                                        <div v-if="jmxs">
                                            统建统管&nbsp;{{dy_tjtg}}&nbsp;&nbsp;&nbsp;自建&nbsp;{{dy_zj}}
                                        </div>
                                        <div v-else-if="gsxs">
                                            供水管网代建&nbsp;{{dy_gwdj}}&nbsp;&nbsp;&nbsp;供水管道迁改&nbsp;{{dy_gdqy}}&nbsp;&nbsp;&nbsp;工商户零星接水&nbsp;{{dy_js}}
                                        </div>
                                        <div v-else>
                                            整体出户改造&nbsp;{{dy_ztch}}&nbsp;&nbsp;&nbsp;零星出户改造&nbsp;{{dy_lxch}}
                                        </div>
                                    </span><br>
                                    二、工程内容<br>
                                    庭院：<span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;{{dy_ty}}&nbsp;&nbsp;&nbsp;</span>
                                    泵房：<span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;{{dy_bf}}&nbsp;&nbsp;&nbsp;</span><br>
                                    立管：<span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;{{dy_lg}}&nbsp;&nbsp;&nbsp;</span>
                                    吊管：<span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;{{dy_dg}}&nbsp;&nbsp;&nbsp;</span><br>
                                    工商户表：<span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;{{dy_gsb}}&nbsp;&nbsp;&nbsp;</span><br>
                                    居民户表：<span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;{{dy_jmb}}&nbsp;&nbsp;&nbsp;</span><br>
                                    管网：<span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;{{dy_gw}}&nbsp;&nbsp;&nbsp;</span><br>
                                    其他：<span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;{{dy_qt}}&nbsp;&nbsp;&nbsp;</span><br>
                                    四、监理范围：<br>
                                    三、庭院&nbsp;{{dy_jlty}}&nbsp;&nbsp;&nbsp;立管&nbsp;{{dy_jllg}}&nbsp;&nbsp;&nbsp;
                                    泵房&nbsp;{{dy_jlbf}}&nbsp;&nbsp;&nbsp;吊管&nbsp;{{dy_jldg}}&nbsp;&nbsp;&nbsp;
                                    水表&nbsp;{{dy_jlsb}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <span style="text-decoration-line: underline;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{dy_jlqt}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>客发项目负责人</td>
                            <td>{{dy_fzr}}</td>
                            <td>联系电话</td>
                            <td>{{dy_lxfs}}</td>
                        </tr>
                        <tr>
                            <td>建设单位</td>
                            <td>{{dy_jsdw}}</td>
                            <td>联系人及电话</td>
                            <td>{{dy_jsdh}}</td>
                        </tr>
                        <tr>
                            <td>合同工期</td>
                            <td>{{dy_htgq}}</td>
                            <td>工程管理人员及电话</td>
                            <td>{{dy_gcdh}}</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" align="left"><div style="text-align:left;">{{dy_bz}}</div></td>
                        </tr>
                        <tr>
                            <th align="left">附：资料清单</th>
                        </tr>
                    </table>
                </div>
                <div style="text-align: center;margin-top: 10px">
                    <el-button size="mini" style="margin-bottom: 15px" type="success" v-print="'#printTest'">
                        <i class="iconfont icon-dayin_f"></i>打印
                    </el-button>
                </div>
            </el-dialog>
        </el-row>
    </section>
</template>

<script>
    import hhldsx from '@/views/template/hhldsx.vue';
    import XMJBXX from '@/views/template/xmjbxx.vue';
    import ZLFLJXZ from '@/views/template/zlfljxz.vue';
    import SGXDXDFW from '@/views/template/sgxdXdfw.vue';
    import {saveSgxd,showEditForm,getDycontent} from '../../api/sgjg'

    export default {
        name: "xdSgxd",
        components: {
            'hhldsx': hhldsx,
            'XMJBXX': XMJBXX,  //项目基本信息组件
            'ZLFLJXZ':ZLFLJXZ, //资料分类与下载组件
            'SGXDXDFW':SGXDXDFW//施工下单范围组件
        },
        data(){
            return{
                tabUrl:'',
                jmxs:false,
                gsxs:false,
                subLoading:false,
                form:{
                    ty:'',bf:'',lg:'',dg:'',szgw:'',jlsb:'',desc:'',thyy:'',
                },
                xmid:this.$route.query.xmid,//项目id
                isEdit:this.$route.query.isEdit,//是否编辑
                sgdid:this.$route.query.sgdid,//施工单ID
                activityId:this.$route.query.activityId,
                taskId:this.$route.query.taskId,
                xmjl:this.$route.query.xmjl,//项目推进科人员

                printFormVisible:false,//打印界面是否显示
                dy_gcmc:'',
                dy_gcdz:'',dy_gcbm:'',dy_rq:'',
                dy_tjtg:'☐',dy_zj:'☐',dy_gwdj:'☐',dy_gdqy:'☐',dy_js:'☐',dy_ztch:'☐',dy_lxch:'☐',
                dy_azlx:'',dy_jmk:'☐',dy_gsk:'☐',dy_hgk:'☐',
                dy_ty:'',dy_bf:'',dy_lg:'',dy_dg:'',dy_gsb:'',dy_jmb:'',dy_qt:'',dy_gw:"",
                dy_fzr:'',dy_lxfs:'',dy_jsdw:'',dy_jsdh:'',dy_htgq:'',dy_gcdh:'',dy_bz:'',
                dy_jlty:'☐',dy_jllg:'☐',dy_jlbf:'☐',dy_jldg:'☐',dy_jlsb:'☐',dy_jlqt:'/',
            }
        },
        mounted() {
            this.queryinit();
        },
        methods:{
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
            },
            //初始化方法
            queryinit(){
                setTimeout(() => {
                    //施工下单页面初始化方法
                    this.$refs.xdfw.childinit();
                    //监理范围card
                    let params = {xmid:this.xmid,sgdid:this.sgdid,type:'2'};
                    showEditForm(params).then((res)=>{
                        let list = res.data.jlfwList[0];
                        this.form.ty = list.TYJL == 0  ? false : list.TYJL == 1 ? true : false;
                        this.form.bf = list.BFJL == 0  ? false : list.BFJL == 1 ? true : false;
                        this.form.lg = list.LGJL == 0  ? false : list.LGJL == 1 ? true : false;
                        this.form.dg = list.DGJL == 0  ? false : list.DGJL == 1 ? true : false;
                        this.form.szgw = list.SZGWJL == 0  ? false : list.SZGWJL == 1 ? true : false;
                        this.form.jlsb = list.JLSBJL == 0  ? false : list.JLSBJL == 1 ? true : false;
                        this.form.desc = list.QT;
                    });
                },0)
                this.tabUrl = "/xdSgxd?xmid="+this.xmid+"&type=1&isEdit=2&sgdid="+this.sgdid
                    +"&xmjl="+this.xmjl+"&activityId="+this.activityId+"&taskId="+this.taskId;
            },
            //返回上一界面
            toReturn(){
                this.$routerTab.close();
            },
            //审核通过
            toSubmit(){
                this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = Object.assign({xmid:this.xmid,type:'6',sgdid:this.sgdid,xmjl:this.xmjl},
                        this.$refs.xdfw.xdform,this.form);
                    this.subLoading = true;
                    this.$routerTab.close();
                    this.tabUrl = "/MyWork";
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
                    saveSgxd(params).then((res) => {
                        if(res.data == true){
                            this.$message({
                                message: '下单成功',
                                type: 'success'
                            });
                            this.subLoading = false;
                        }else{
                            this.$message.error('下单失败！');
                        }
                    });
                }).catch(() => {

                });
            },
            toDy(){
                if(this.form.ty == true){
                    this.dy_jlty = "√";
                }
                if(this.form.dg == true){
                    this.dy_jldg = "√";
                }
                if(this.form.bf == true){
                    this.dy_jlbf = "√";
                }
                if(this.form.lg == true){
                    this.dy_jllg = "√";
                }
                if(this.form.jlsb == true){
                    this.dy_jlsb = "√";
                }
                if(this.form.desc != ''){
                    this.dy_jlqt = this.form.desc;
                }
                let para = {xmid:this.xmid,xmjl:this.xmjl};
                getDycontent(para).then(res=>{
                    let list = res.data.list[0];
                    this.dy_gcmc = list.xmmc;
                    this.dy_gcdz = list.xmdz;
                    this.dy_fzr = list.xmfzrname;
                    this.dy_lxfs = list.xmfzrsjh;
                    this.dy_jsdw = list.dwmc;
                    this.dy_jsdh = list.lxr + "【"+list.lxrsjh+"】";
                    this.dy_azlx = list.azlxname;
                    if(list.sqlx == '10'){
                        this.dy_jmk = "√";
                        this.jmxs = true;
                        if(list.xmlb == 'T'){
                            this.dy_tjtg = "√";
                        }else{
                            this.dy_zj = "√";
                        }
                    }else if(list.sqlx == '20'){
                        this.dy_gsk = "√";
                        this.gsxs = true;
                        if(list.xmlb == 'D'){
                            this.dy_gwdj = "√";
                        }else if(list.xmlb == 'G'){
                            this.dy_gdqy = "√";
                        }else{
                            this.dy_js = "√";
                        }
                    }else{
                        this.dy_hgk = "√";
                        if(list.xmlb == 'Q'){
                            this.dy_ztch = "√";
                        }else{
                            this.dy_lxch = "√";
                        }
                    }
                    let htList = res.data.htList[0];
                    if(htList != '' && htList != undefined){
                        this.dy_htgq = htList.gqzrlts;
                    }
                    let userList = res.data.userList[0];
                    this.dy_gcdh = userList.username + "【"+userList.mobilephone+"】";
                    this.dy_gcbm = list.xmbh;
                    const now = new Date();
                    let month = now.getMonth() + 1;
                    let day = now.getDate();
                    this.dy_rq = now.getFullYear().toString() + "年" + month.toString() + "月" + day.toString() + "日";
                    this.printFormVisible = true;
                });
            },
            sbdy_vue(gsxx,jmxx,qtxx,gwxx,tyxx,lgxx,bfxx,dgxx,bzxx){
                this.dy_gsb = gsxx;
                this.dy_jmb = jmxx;
                this.dy_qt = qtxx;
                this.dy_ty = tyxx;
                this.dy_gw = gwxx;
                this.dy_lg = lgxx;
                this.dy_bf = bfxx;
                this.dy_dg = dgxx;
                this.dy_bz = bzxx
            },
        }
    }
</script>

<style scoped>
    .border-table {
        border-collapse: collapse;
        border: none;
        width: 100%;
    }
    .border-table td {
        border: solid #000 1px;
    }
</style>