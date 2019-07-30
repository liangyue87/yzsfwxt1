<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--项目基本信息模板-->
                <XMJBXX></XMJBXX>
                <!--资料分类下载-->
                <ZLFLJXZ></ZLFLJXZ>
                <!--施工下单范围-->
                <SGXDXDFW ref="xdfw"></SGXDXDFW>

                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">监理范围</div>
                    </div><el-form ref="form" :model="form" label-width="80px">
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
                </el-form>

                </el-card>
                <el-row style="text-align: center;margin-top: 15px">
                    <router-link :to="{path:'/sggcdlb',query:{xmid:this.xmid}}">
                        <el-button size="mini" type="info" @click="toReturn">
                            <i class="iconfont icon-quxiao"></i>取消</el-button>
                    </router-link>
                    <el-button size="mini" type="primary" :loading="addLoading" @click="toAdd">
                        <i class="iconfont icon-save"></i>保存</el-button>
                    <router-link :to="tabUrl">
                        <el-button size="mini" type="primary" :loading="spLoading" @click="toSubmit">
                            <i class="iconfont icon-shenpitongguo"></i>发送科长审批</el-button>
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
    import {saveSgxd,delSgdid} from '../../api/sgjg'

    export default {
        name: "addSgxd",
        components: {
            'XMJBXX': XMJBXX,  //项目基本信息组件
            'ZLFLJXZ':ZLFLJXZ, //资料分类与下载组件
            'SGXDXDFW':SGXDXDFW//施工下单范围组件
        },
        data(){
            return{
                tabUrl:'',
                addLoading:false,
                spLoading:false,

                form:{
                    ty:'',bf:'',lg:'',dg:'',szgw:'',jlsb:'',desc:'',
                },
                xmid:this.$route.query.xmid,//项目id
                sgdid:this.$route.query.sgdid,//施工单ID
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
            //初始加载方法
            queryinit(){
                this.tabUrl = "/addSgxd?xmid="+this.xmid+"&type=1&isEdit=0&sgdid="+this.sgdid;
                let para = {xmid:this.xmid};
                delSgdid(para).then(res=>{

                })
            },
            //返回上一界面
            toReturn(){
                this.$routerTab.close();
            },
            //打印
            toDy(){

            },
            //保存施工下单
            toAdd(){
                // setTimeout(() => {
                let params = Object.assign({xmid:this.xmid,type:'1',sgdid:this.sgdid},
                    this.$refs.xdfw.xdform,this.form);
                this.addLoading = true;
                this.$routerTab.close();
                this.tabUrl = '/sggcdlb?xmid='+this.xmid;
                this.$routerTab.refresh('/sggcdlb?xmid='+this.xmid);
                saveSgxd(params).then((res) => {
                    if(res.data != ''){
                        this.$message({
                            message: '保存成功',
                            type: 'success'
                        });
                    }else{
                        this.$message.error('保存失败！');
                    }
                    this.addLoading = false;
                });
                // },0);
            },
            //发送科长审批
            toSubmit(){
                // setTimeout(() => {
                this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = Object.assign({xmid:this.xmid,type:'2',sgdid:this.sgdid},
                        this.$refs.xdfw.xdform,this.form);
                    this.spLoading = true;
                    this.$routerTab.close();
                    this.tabUrl = '/sggcdlb?xmid='+this.xmid;
                    this.$routerTab.refresh('/sggcdlb?xmid='+this.xmid);
                    saveSgxd(params).then((res) => {
                        if(res.data == true){
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.spLoading = false;
                        }else{
                            this.$message.error('提交失败！');
                        }
                    });
                }).catch(() => {

                });
                // },0);
            },
        }
    }
</script>

<style scoped>

</style>