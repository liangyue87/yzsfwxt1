<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <el-row>
            <el-col :span="24">
                 <span v-if="htyy">
                <hhldsx :xmid="this.xmid" :activityid="this.activityId"></hhldsx>
               </span>
                <!--项目基本信息模板-->
                <GWJBXX></GWJBXX>
                <!--发起官网信息调查事件-->
                <el-card class="box-card" style="margin-top: 10px;" body-style="{}">
                    <div slot="header" class="clearfix">
                        <!-- <span >地区</span> -->
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">区所</div>
                    </div>

                    <div class="text item">
                        <el-row class="row" style="margin-top: 20px;">
                            <el-col :span="1">&nbsp;</el-col>
                            <el-col :span="4">
                                <el-radio v-model="qs" label="7010" border>庐阳所</el-radio>
                            </el-col>
                            <el-col :span="4">
                                <el-radio v-model="qs" label="7020" border>瑶海所</el-radio>
                            </el-col>
                            <el-col :span="4">
                                <el-radio v-model="qs" label="7030" border>蜀山所</el-radio>
                            </el-col>
                            <el-col :span="4">
                                <el-radio v-model="qs" label="7040" border>包河所</el-radio>
                            </el-col>
                        </el-row>
                        <el-row class="row" style="margin-top: 20px;">
                            <el-col :span="1">&nbsp;</el-col>
                            <el-col :span="4">
                                <el-radio v-model="qs" label="7050" border>经开所</el-radio>
                            </el-col>
                            <el-col :span="4">
                                <el-radio v-model="qs" label="7060" border>北城所</el-radio>
                            </el-col>
                            <el-col :span="4">
                                <el-radio v-model="qs" label="7070" border>滨湖所</el-radio>
                            </el-col>
                        </el-row>
                        <el-row style="margin-top: 80px;">
                            <el-col :span="10">&nbsp;</el-col>
                            <el-col :span="10">
                                <router-link :to="tabUrl">
                                    <el-button size="mini" type="info" @click="cancel()"><i
                                            class="iconfont icon-quxiao"></i>取消
                                    </el-button>
                                </router-link>
                                    <el-button size="mini" type="primary" @click="saveFqgwxxTask"
                                               :loading="sumbitLoading"><i class="iconfont icon-queren"></i>提交
                                    </el-button>

                                <el-button size="mini" type="primary" @click="goxmqj()" :loading="sumbitLoading"><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
                            </el-col>
                        </el-row>
                        <el-row class="row"></el-row>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="24" style="text-align: center;margin-top: 35px;">

            </el-col>
        </el-row>

    </section>
</template>
<style lang="css">
    .row {
        margin-top: 14px;
    }

    .row .el-col {
        text-align: center;
    }
</style>
<script>
    import GWJBXX from '@/views/template/gwjbxx.vue';
    import hhldsx from '@/views/template/hhldsx.vue';
    import util from '../../common/js/util'

    import {saveFqgwxxTask, selectXzqy} from '../../api/sjst';

    export default {
        data() {
            return {
                qs: '',//区所
                xmid: this.$route.query.xmid,
                activityId: '30005',
                bmbm_Qs: this.$route.query.bmbm_Qs,
                htyy: '',
                sumbitLoading: false,
                tabUrl: '',
            }
        },
        methods: {
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})

            },
            cancel() {
                this.tabUrl='/fqgwxxdc'
                this.$routerTab.close();
                this.$routerTab.refresh('/fqgwxxdc');
            },
            saveFqgwxxTask() {//发起
                //   let xmid = localStorage.getItem("xmid");
                //   console.log(this.xmid+"-----30006");
                //  console.log(this.qs+"---qs-------");
                let params = Object.assign({xmid: this.xmid, qs: this.qs});
                this.sumbitLoading = true;
           /*     this.zpurl = '/Backlog';
                this.$routerTab.close();*/
                this.tabUrl='/Fqgwxxdc';
                this.$routerTab.close();
                this.$routerTab.refresh('/Fqgwxxdc');
                saveFqgwxxTask(params).then((res) => {
                    this.sumbitLoading = false;
                    // console.log(res.result+"--")
                    if (res.result == 'success') {
                        this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                    } else {
                        this.$message.error('提交失败！');
                    }
                });
            },

            selectXzqy() {
                this.qs = this.bmbm_Qs;
                let params = Object.assign({xmid: this.xmid});
                selectXzqy(params).then((res) => {
                    if (res.htyyStr == "err") {
                        this.htyy = false;
                    } else {
                        this.htyy = true;
                    }
                });
            }
            //  let params = Object.assign({xmdz_Xzqybh: this.xmdz_Xzqybh});
            //   selectXzqy(params).then((res) => {
            //   this.qs=res.xzqy;
            //   });
            //  }
        },
        mounted() {
            this.selectXzqy();//获取区所
        },
        components: {
            'GWJBXX': GWJBXX,  //将别名demo 变成 组件 Demo
            'hhldsx': hhldsx,
        }

    }

</script>

<style>

</style>