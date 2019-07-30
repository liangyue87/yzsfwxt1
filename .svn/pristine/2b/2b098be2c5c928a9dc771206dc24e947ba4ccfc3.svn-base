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
                <SGXDXDFW ref="xdfw"></SGXDXDFW>

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
                        <!--<el-form-item label="退回原因" v-if="showTh">-->
                            <!--<el-col :span="12">-->
                                <!--<el-input type="textarea" :autosize="{ minRows: 4, maxRows: 5}" v-model="form.thyy"></el-input>-->
                            <!--</el-col>-->
                        <!--</el-form-item>-->
                    </el-form>
                </el-card>
                <el-row style="text-align: center;margin-top: 15px">
                    <router-link :to="{path:'/Backlog'}">
                        <el-button size="mini" type="info" @click="toReturn">
                            <i class="iconfont icon-quxiao"></i>取消</el-button>
                    </router-link>
                    <router-link :to="tabUrl">
                        <el-button size="mini" type="info"  @click="htyyVisible = true">
                            <i class="iconfont icon-fanhui"></i>退回</el-button>
                    </router-link>
                    <router-link :to="tabUrl">
                        <el-button size="mini" type="primary" :loading="subLoading" @click="toSubmit">
                            <i class="iconfont icon-queren"></i>审核通过</el-button>
                    </router-link>
                    <el-button size="mini" type="primary" @click="goxmqj()" >
                        <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                    </el-button>
                </el-row>
            </el-col>
        </el-row>
        <el-dialog title="退回原因" :visible.sync = "htyyVisible" :close-on-click-modal="false">
            <el-input
                    type="textarea"
                    :rows="4"
                    placeholder="请输入内容"
                    v-model="thyy">
            </el-input>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="info" @click="htyyVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                <el-button type="primary" size="mini" :loading="thLoading" @click="toTh()"><i class="iconfont icon-queren"></i>确 定</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    import XMJBXX from '@/views/template/xmjbxx.vue';
    import ZLFLJXZ from '@/views/template/zlfljxz.vue';
    import SGXDXDFW from '@/views/template/sgxdXdfw.vue';
    import hhldsx from '@/views/template/hhldsx.vue';
    import {saveSgxd,showEditForm} from '../../api/sgjg'

    export default {
        name: "shSgxd",
        components: {
            'hhldsx': hhldsx,
            'XMJBXX': XMJBXX,  //项目基本信息组件
            'ZLFLJXZ':ZLFLJXZ, //资料分类与下载组件
            'SGXDXDFW':SGXDXDFW//施工下单范围组件
        },
        data(){
            return{
                thyy:'',
                htyyVisible:false,
                thLoading:false,
                subLoading:false,
                tabUrl:'',

                form:{
                    ty:'',bf:'',lg:'',dg:'',szgw:'',jlsb:'',desc:''
                },
                xmid:this.$route.query.xmid,//项目id
                isEdit:this.$route.query.isEdit,//是否编辑
                sgdid:this.$route.query.sgdid,//施工单ID
                activityId:this.$route.query.activityId,
                taskId:this.$route.query.taskId,
                showTh:true,
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
                this.tabUrl = "/shSgxd?xmid="+this.xmid+"&type=1&isEdit=2&sgdid="+this.sgdid
                    +"&activityId="+this.activityId+"&taskId="+this.taskId;
            },
            //返回上一界面
            toReturn(){
                this.$routerTab.close();
            },
            //退回
            toTh(){
                if(this.thyy==''||this.thyy==null){
                    this.$message.error('请填写退回原因！');
                    return;
                }
                this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = Object.assign({xmid:this.xmid,type:'4',sgdid:this.sgdid,thyy:this.thyy},
                        this.$refs.xdfw.xdform,this.form);
                    this.thLoading = true;debugger
                    this.$routerTab.close();
                    this.tabUrl = "/MyWork";
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
                    saveSgxd(params).then((res) => {
                        if(res.data == true){
                            this.$message({
                                message: '退回成功',
                                type: 'success'
                            });
                            this.thLoading = false;
                        }else{
                            this.$message.error('退回失败！');
                        }
                    });
                }).catch(() => {

                });
            },
            //审核通过
            toSubmit(){
                this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = Object.assign({xmid:this.xmid,type:'3',sgdid:this.sgdid,rwbh:this.rwbh},
                        this.$refs.xdfw.xdform,this.form);
                    this.subLoading = true;
                    this.$routerTab.close();
                    this.tabUrl = "/MyWork";
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
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