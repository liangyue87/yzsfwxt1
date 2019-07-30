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
                    </el-form>
                </el-card>

                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <span>指派人员</span>
                    </div>
                    <div class="text item">
                        <el-row>
                            <el-form label-width="120px" :inline="true">
                                <el-radio-group v-model="resource" style="width: 100%;">
                                    <el-col :span="6" v-model="data" v-for="(item,index) in data" class="col" :key="index">
                                        <el-form-item style="width: 45%">
                                            <el-radio v-model="radio" :label="item.USERID">{{item.USERNAME}}（在建项目{{item.NUM}}个）</el-radio>
                                        </el-form-item>
                                    </el-col>
                                </el-radio-group>
                            </el-form>
                        </el-row>
                    </div>
                </el-card>
                <el-row style="text-align: center;margin-top: 15px">
                    <router-link :to="{path:'/Backlog'}">
                        <el-button size="mini" type="info" @click="toReturn">
                            <i class="iconfont icon-quxiao"></i>取消</el-button>
                    </router-link>
                    <router-link :to="zpurl">
                        <el-button size="mini" type="primary" :loading="subLoading" @click="toSubmit">
                            <i class="iconfont icon-shenhetongguorenyuan"></i>指派</el-button>
                    </router-link>
                    <el-button size="mini" type="primary" @click="goxmqj()" >
                        <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                    </el-button>
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
    import {saveSgxd,showEditForm,getZpry} from '../../api/sgjg'

    export default {
        name: "zpSgxd",
        components: {
            'hhldsx': hhldsx,
            'XMJBXX': XMJBXX,  //项目基本信息组件
            'ZLFLJXZ':ZLFLJXZ, //资料分类与下载组件
            'SGXDXDFW':SGXDXDFW//施工下单范围组件
        },
        data(){
            return{
                zpurl:'',
                subLoading:false,
                form:{
                    ty:'',bf:'',lg:'',dg:'',szgw:'',jlsb:'',desc:'',thyy:'',
                },
                xmid:this.$route.query.xmid,//项目id
                isEdit:this.$route.query.isEdit,//是否编辑
                sgdid:this.$route.query.sgdid,//施工单ID
                activityId:this.$route.query.activityId,
                taskId:this.$route.query.taskId,
                resource:'',
                radio : '',//选中的指派人员
                data :[],
            }
        },
        mounted() {
            this.getZpry();
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
            },
            //返回上一界面
            toReturn(){
                this.$routerTab.close();
            },
            //获取指派人员名单
            getZpry(){
                let params = {activityId:this.activityId};
                getZpry(params).then((res) => {
                    this.data = res.data.zpry;
                });
            },
            //指派人员
            toSubmit(){
                if(this.resource == ''){
                    this.$message({
                        message: '请选择指派人员！',
                        type: 'warning'
                    });
                    this.zpurl = "/zpSgxd?xmid="+this.xmid+"&type=1&isEdit=2&sgdid="+this.sgdid+
                        "&activityId="+this.activityId+"&taskId="+this.taskId;
                }else{
                    let params = Object.assign({xmid:this.xmid,type:'5',sgdid:this.sgdid,name: this.resource},
                        this.$refs.xdfw.xdform,this.form);
                    this.subLoading = true;
                    this.zpurl = "/MyWork";
                    this.$routerTab.close();
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
                    saveSgxd(params).then((res) => {
                        if(res.data == true){
                            this.$message({
                                message: '指派成功',
                                type: 'success'
                            });
                            this.subLoading = false;
                        }else{
                            this.$message.error('指派失败！');
                        }
                    });
                }
            },
        }
    }
</script>

<style scoped>

</style>