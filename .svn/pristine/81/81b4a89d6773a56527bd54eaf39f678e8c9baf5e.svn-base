<template>
    <section>
        <el-row>
            <el-col :span="24">
                <div v-if="isTh=='1'">
                    <!--红黄绿灯时间-->
                    <hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
                </div>
                <!--项目基本信息模板-->
                <XMJBXX></XMJBXX>
                <!--资料分类下载-->
                <ZLFLJXZ></ZLFLJXZ>
                <!--施工下单范围-->
                <SGXDXDFW ref="xdfw"></SGXDXDFW>

                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">监理范围</div>
                    </div>
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.ty">
                                    <el-checkbox label="庭院" name="ty"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.bf">
                                    <el-checkbox label="泵房" name="bf"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.lg">
                                    <el-checkbox label="立管" name="lg"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.dg">
                                    <el-checkbox label="吊管" name="dg"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.szgw">
                                    <el-checkbox label="市政管网" name="szgw"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                            <el-col :span="12">
                                <el-checkbox-group v-model="form.jlsb">
                                    <el-checkbox label="计量水表" name="jlsb"></el-checkbox>
                                </el-checkbox-group>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="其他">
                            <el-col :span="12">
                                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 5}" v-model="form.desc"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="退回原因" v-if="showTh">
                            <el-col :span="12">
                                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 5}" v-model="form.thyy"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>
                <el-row style="text-align: center;margin-top: 15px">
                    <div v-if="isTh==1">
                        <router-link :to="toUrl">
                            <el-button size="mini" type="info" @click="toReturn">
                                <i class="iconfont icon-quxiao"></i>取消</el-button>
                        </router-link>
                        <router-link :to="toUrl">
                            <el-button size="mini" type="primary" :loading="subLoading" @click="toSubmit">
                                <i class="iconfont icon-shenpitongguo"></i>发送科长审批</el-button>
                        </router-link>
                        <el-button size="mini" type="primary" @click="goxmqj()" >
                            <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                        </el-button>
                    </div>
                    <div v-else>
                        <router-link :to="toUrl">
                            <el-button size="mini" type="info" @click="toReturn">
                                <i class="iconfont icon-quxiao"></i>取消</el-button>
                        </router-link>
                        <router-link :to="{path:'/sggcdlb',query:{xmid:this.xmid}}">
                            <el-button size="mini" type="primary" :loading="subLoading" @click="toAdd">
                                <i class="iconfont icon-save"></i>保存</el-button>
                        </router-link>
                        <router-link :to="toUrl">
                            <el-button size="mini" type="primary" :loading="subLoading" @click="toSubmit">
                                <i class="iconfont icon-shenpitongguo"></i>发送科长审批</el-button>
                        </router-link>
                        <el-button size="mini" type="primary" @click="goxmqj()" >
                            <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                        </el-button>
                    </div>
                </el-row>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import XMJBXX from '@/views/template/xmjbxx.vue';
    import ZLFLJXZ from '@/views/template/zlfljxz.vue';
    import SGXDXDFW from '@/views/template/sgxdXdfw.vue';
    import hhldsx from '@/views/template/hhldsx.vue';
    import {saveSgxd,showEditForm} from '../../api/sgjg'

    export default {
        name: "editSgxd",
        components: {
            'hhldsx': hhldsx,
            'XMJBXX': XMJBXX,  //项目基本信息组件
            'ZLFLJXZ':ZLFLJXZ, //资料分类与下载组件
            'SGXDXDFW':SGXDXDFW//施工下单范围组件
        },
        data(){
            return{
                toUrl:'',
                subLoading:false,

                form:{
                    ty:'',bf:'',lg:'',dg:'',szgw:'',jlsb:'',desc:'',thyy:'',
                },
                xmid:this.$route.query.xmid,//项目id
                isEdit:this.$route.query.isEdit,//是否编辑
                sgdid:this.$route.query.sgdid,//施工单ID
                rwbh:this.$route.query.rwbh,//任务编号
                isTh:this.$route.query.isTh,//是否退回工程单
                activityId:this.$route.query.activityId,
                taskId:this.$route.query.taskId,
                showTh:false,
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
                },0);
                if(this.isTh == '1'){
                    this.toUrl = "/editSgxd?xmid="+this.xmid+"&type=1&isEdit=1&sgdid="+this.sgdid
                        +"&rwbh="+this.rwbh+"&isTh=1&activityId="+this.activityId+"&taskId="+this.taskId;
                }else{
                    this.toUrl = "/editSgxd?xmid="+this.xmid+"&type=1&isEdit=1&sgdid="+this.sgdid+"&rwbh="+this.rwbh;
                }
            },
            //返回上一界面
            toReturn(){
                if(this.isTh == '1'){
                    this.toUrl = '/Backlog';
                }else {
                    this.toUrl = '/sggcdlb?xmid='+this.xmid;
                }
                this.$routerTab.close();
            },
            //保存施工下单
            toAdd(){
                let params = Object.assign({xmid:this.xmid,type:'8',sgdid:this.sgdid,rwbh:this.rwbh},
                    this.$refs.xdfw.xdform,this.form);
                this.subLoading = true;
                this.$routerTab.close();
                this.$routerTab.refresh('/sggcdlb?xmid='+this.xmid);
                saveSgxd(params).then((res) => {
                    if(res.data == true){
                        this.$message({
                            message: '保存成功',
                            type: 'success'
                        });
                        this.subLoading = false;
                    }else{
                        this.$message.error('保存失败！');
                    }
                });
            },
            //提交给领导审核
            toSubmit(){
                this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let type = "2";
                    if(this.isTh == '1'){
                        type = "7";
                        this.toUrl = "/Backlog";
                        this.$routerTab.refresh('/Backlog');
                        this.$routerTab.refresh('/MyWork');
                    }else{
                        this.toUrl = "/sggcdlb?xmid="+this.xmid;
                        this.$routerTab.refresh('/sggcdlb?xmid='+this.xmid);
                    }
                    this.$routerTab.close();
                    let params = Object.assign({xmid:this.xmid,type:type,sgdid:this.sgdid,rwbh:this.rwbh},
                        this.$refs.xdfw.xdform,this.form);
                    this.subLoading = true;
                    saveSgxd(params).then((res) => {
                        if(res.data == true){
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.subLoading = false;
                        }else{
                            this.$message.error('提交失败！');
                        }
                    });
                }).catch(() => {

                });
            },
        }
    }
</script>

<style scoped>

</style>