<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24">
                <!--红黄绿灯时间-->
                <hhldsx :xmid="rwid" :activityid="activityId"></hhldsx>
                <el-card class="box-card" style="margin-top: 15px;" body-style="{}">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">基本信息</div>
                    </div>
                    <div class="text item">
                        <el-form :model="jbxxForm" label-width="120px" ref="xmxxForm" :inline="true">
                            <el-form-item label="工程名称" style="width: 45%">
                                <el-input v-model="jbxxForm.gcmc" :readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="安裝信息" style="width: 45%">
                                <el-input v-model="jbxxForm.azxx" :readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="工程地址" style="width: 45%">
                                <el-input v-model="jbxxForm.gcdz" :readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="报建单位" style="width: 45%">
                                <el-input v-model="jbxxForm.bjdw" :readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="联系人" style="width: 45%">
                                <el-input v-model="jbxxForm.lxr" :readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="联系电话" style="width: 45%">
                                <el-input v-model="jbxxForm.lxdh" :readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="受理日期" style="width: 45%">
                                <el-input v-model="jbxxForm.slrq" :readonly="true"></el-input>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-card>

                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">任务流转</div>
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

                <el-row style="text-align: center;margin-top: 25px;">
                    <router-link :to="zpurl">
                        <el-button size="mini" type="primary" :loading="submitLoading" @click="handleAdd">
                            <i class="iconfont icon-queren"></i>提交
                        </el-button>
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
    import hhldsx from '@/views/template/hhldsx.vue';
    import { toZpry,getZpry,queryXqByid,updateCheckTask } from '../../api/xqsl';
    export default {
        name: "zpry",
        components: {
            'hhldsx': hhldsx,
        },
        data() {
            return {
                zpurl:'',
                para:'',
                activityId:this.$route.query.activityId,
                rwid:this.$route.query.rwid,
                type:this.$route.query.type,
                taskId:this.$route.query.taskId,
                submitLoading:false,
                jbxxForm: {
                    gcmc : '',//工程名稱
                    azxx : '',//安裝信息
                    gcdz : '',//工程地址
                    bjdw : '',//報建單位
                    lxr : '',//聯係人
                    lxdh : '',//聯係電話
                    slrq : ''//受理日期
                },
                resource:'',
                radio : '',//选中的指派人员
                data :[],
            }
        },
        methods:{
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.rwid  }})
            },
            queryinit:function(){
                let para = {rwid:this.rwid};
                queryXqByid(para).then(res=>{
                    let list = res.data.list[0];
                    this.jbxxForm.gcmc = list.xmmc;
                    this.jbxxForm.azxx = list.azlxname;
                    this.jbxxForm.gcdz = list.xmdz;
                    this.jbxxForm.bjdw = list.dwmc;
                    this.jbxxForm.lxr = list.lxr;
                    this.jbxxForm.lxdh = list.lxrsjh;
                    this.jbxxForm.slrq = list.slrq;
                });
            },
            getZpry(){//获取指派人员名单
                let params = {type : this.type};
                getZpry(params).then((res) => {
                    this.data = res.data.zpry;
                });
            },
            handleAdd: function(){
                if(this.resource == ''){
                    this.$message({
                        message: '请选择指派人员！',
                        type: 'warning'
                    });
                    this.zpurl = '/zpry?rwid='+this.rwid+'&type='+this.type+'&activityId='+this.activityId
                        +'&taskId='+this.taskId;
                }else{
                    let params= {
                        rwid: this.rwid,
                        name: this.resource,
                        type: this.type
                    };
                    this.submitLoading = true;
                    this.zpurl='/Backlog';
                    this.$routerTab.close();
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
                    toZpry(params).then((res) => {
                        if(res.data == true){
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                        }else{
                            this.$message.error('提交失败！');
                            this.$routerTab.close();
                        }
                        this.resource = "";
                        this.submitLoading = false;
                    });
                }
            },
            //流程改变签收状态
            changeQszt(){
                if(this.taskId != '' && this.taskId != undefined){
                    let para = {taskId: this.taskId};
                    updateCheckTask(para).then(res=>{

                    });
                }
            },
        },
        mounted() {
            this.queryinit();
            this.getZpry();
            this.changeQszt();
        },
    }
</script>

<style scoped>

</style>
