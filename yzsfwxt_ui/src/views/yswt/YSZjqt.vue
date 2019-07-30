<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--项目基本信息模板-->
                <XMJBXX></XMJBXX>
                <!--发起造价洽谈需填写内容-->
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">选择参与造价洽谈部门和人员</div>
                    </div>
                    <div class="text item" id="row">
                        <el-row>
                            <el-col :span="4">洽谈部门</el-col>
                            <el-col :span="4">是否短信通知</el-col>
                            <el-col :span="4">人员</el-col>
                            <el-col :span="4">联系电话</el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4">设计部门</el-col>
                            <el-col :span="4"><el-switch v-model="check_value.sjy_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>
                            <el-col :span="4"><el-input v-model="sjry" size="small" :readonly="true" style="width: 50%;"></el-input></el-col>
                            <el-col :span="4"><el-input v-model="sjrysjh" size="small" style="width: 60%;"></el-input></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4">预算部门</el-col>
                            <el-col :span="4"><el-switch v-model="check_value.sgdw_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>
                            <el-col :span="4"><el-input v-model="ysry" size="small" :readonly="true" style="width: 50%;"></el-input></el-col>
                            <el-col :span="4"><el-input v-model="ysrysjh" size="small" style="width: 60%;"></el-input></el-col>
                        </el-row>

                        <el-row>
                            <el-col :span="1">
                                备注：
                            </el-col>
                            <el-col :span="11">
                                <el-input v-model="bz" :readonly="sfzd"></el-input>
                            </el-col>
                            <el-col :span="12">
                                洽谈时间：<el-date-picker
                                                     v-model="qtsj"
                                                     type="datetime"
                                                     placeholder="选择日期时间"
                                                     format="yyyy-MM-dd HH:mm"
                                                     value-format="yyyy-MM-dd HH:mm"
                                                     :readonly="sfzd"
                                                        >
                            </el-date-picker>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>
                <el-row style="text-align: center;margin-top: 25px;">
                    <div v-if="type == '1'">
                        <router-link :to="'/YSZjqtList'">
                            <el-button size="mini" type="info" @click="toXckcrwcj">
                                <i class="iconfont icon-quxiao"></i>取消</el-button>
                        </router-link>
                    </div>
                    <div v-else>
                        <router-link :to="'/YSZjqtList'">
                            <el-button size="mini" type="info" @click="toXckcrwcj">
                                <i class="iconfont icon-quxiao"></i>取消</el-button>
                        </router-link>
                        <el-button size="mini" type="primary" :loading="submitLoading" @click="saveXckcTask">
                            <i class="iconfont icon-queren"></i>提交</el-button>
                        <el-button size="mini" type="primary" style="margin-left: 2px" @click="goxmqj()" >
                            <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                        </el-button>
                    </div>
                </el-row>
            </el-col>

            <el-col :span="24" style="text-align: center;margin-top: 35px;">

            </el-col>
        </el-row>

    </section>
</template>

<script>
    import XMJBXX from '@/views/template/xmjbxx.vue';
    import util from '../../common/js/util'
    import {saveYsZjqt,queryBmById} from '../../api/YsZjqt'
    export default {
        name: "YSZjqt",
        data(){
            return{
                tabUrl:'',
                submitLoading:false,
                qtsj : "",//预约洽谈时间
                fqr: '',
                bz: '',
                sjry:'',
                sjrysjh:'',
                ysry:'',
                ysrysjh:'',
                sjryId:'',
                ysryId:'',
                xmid:this.$route.query.xmid,
                check_value :{
                    sjy_value: true,
                    sgdw_value: true
                },
                type:this.$route.query.type,
                pqtsj:this.$route.query.qtsj,
                pbz:this.$route.query.bz,
                sfzd:false
            }
        },
        methods: {
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
            },
            queryinit(){
                if(this.type == '1'){
                    this.qtsj = this.pqtsj;
                    this.bz = this.pbz;
                    this.sfzd = true;
                }
                let params = {xmid:this.xmid};
                queryBmById(params).then((res)=>{
                    if(res.data.list != ''){
                      this.sjry = res.data.list[0].SJRY;
                      this.sjrysjh = res.data.list[0].SJSJH;
                      this.ysry = res.data.list[0].YSRY;
                      this.ysrysjh = res.data.list[0].YSSJH;
                      this.sjryId = res.data.list[0].SJRYID;
                      this.ysryId = res.data.list[0].YSRYID;
                    }
                });
            },
            toXckcrwcj(){//跳转页面Xckcrwcj
                this.$routerTab.close();
                this.$routerTab.refresh('/YSZjqtList');
            },
            saveXckcTask(){//发起造价洽谈
                this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //洽谈时间不为空时，才执行
                    if (this.qtsj != "" && this.qtsj != null && typeof this.qtsj != "undefined" ){
                        this.submitLoading = true;
                        let params = Object.assign({ qtsj: this.qtsj,  bz:this.bz, xmid : this.xmid,sjry:this.sjryId,
                            sjrysjh: this.sjrysjh,ysry:this.ysryId,ysrysjh:this.ysrysjh}, this.check_value);
                        saveYsZjqt(params).then((res) => {
                            if(res.flag == true){
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.submitLoading = false;
                            }else{
                                this.$message.error('提交失败！');
                            }
                        });
                        this.toXckcrwcj();
                    }else {
                        this.$message({
                            message: '洽谈时间不能为空！',
                            type: 'warning'
                        });
                        this.queryinit();
                    }
                }).catch(() => {

                });
            }
        },
        mounted() {
            this.queryinit();
        },
        components: {
            'XMJBXX': XMJBXX  //将别名demo 变成 组件 Demo
        }
    }
</script>

<style scoped>

</style>
