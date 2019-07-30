<!--发起官网基本信息：模板界面-->
<template>
    <section>
        <el-row style="margin-top: 15px;">
            <el-col :span="24" >
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <!-- <span>项目基本信息</span> -->
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目基本信息</div> 
                    </div>
                    <div class="text item">
                        <el-form :model="xmxxForm" label-width="120px" ref="xmxxForm" :inline="true">
                            <el-form-item label="项目编号" style="width: 45%">
                                <el-input v-model="xmxxForm.xmbh" :readonly="true"size="small "></el-input>
                            </el-form-item>
                            <el-form-item label="安装类型" style="width: 45%">
                                <el-input v-model="xmxxForm.codename" :readonly="true" size="small "></el-input>
                            </el-form-item>
                            <el-form-item label="项目名称" style="width: 45%">
                                <el-input v-model="xmxxForm.xmmc" :readonly="true" size="small "></el-input>
                            </el-form-item>
                            <el-form-item label="报建单位" style="width: 45%">
                                <el-input v-model="xmxxForm.dwmc" :readonly="true" size="small "></el-input>
                            </el-form-item>
                            <el-form-item label="用户联系人" style="width: 45%">
                                <el-input v-model="xmxxForm.lxr" :readonly="true" size="small "></el-input>
                            </el-form-item>
                            <el-form-item label="用户联系电话" style="width: 45%">
                                <el-input v-model="xmxxForm.lxrsjh" :readonly="true" size="small "></el-input>
                            </el-form-item>
                         <!--   <el-form-item label="项目联系人" style="width: 45%">
                                <el-input v-model="xmxxForm.lxr" :readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="项目联系电话" style="width: 45%">
                                <el-input v-model="xmxxForm.lxrsgh" :readonly="true"></el-input>
                            </el-form-item>-->
                            <el-form-item label="项目地址" style="width: 45%">
                                <el-input v-model="xmxxForm.xmdz" :readonly="true" size="small "></el-input>
                            </el-form-item>
                            <el-form-item label="立项日期" style="width: 45%">
                                <el-input v-model="xmxxForm.lxsj_stt" :readonly="true" size="small "></el-input>
                            </el-form-item>

                        </el-form>
                    </div>
                </el-card>

            </el-col>
        </el-row>
    </section>
</template>

<script>

    import { findXmxx } from '../../api/sjst';
    export default {
        data() {
            return {
                xmxxForm: {
                    xmmc : '',//项目名称
                    codename : '',//安装类型
                    xmdz : '',//工程地质
                    dwmc : '',//报建单位
                    lxr : '',//联系人
                    lxrsjh : '',//联系电话
                    xmbh:'',
                    lxsj_stt:'',
                },
                xmid:this.$route.query.xmid
            }
        },

        methods: {
        },
        mounted() {
            let id=this.xmid;///针对会审结果录入
            if(typeof id === "undefined"){
                let xmid = localStorage.getItem("xmid");
                this.xmid=xmid;
            }
         //   let xmid= localStorage.getItem("xmid");
               let  params = {xmid:this.xmid};
            findXmxx(params).then((res) => {
                let obj = eval("("+ res.data.list +")");
               if(obj != null) {
                    for (var i in obj) {
                        if (obj[i] == 'null') {
                            obj[i] = '';
                        }
                    }
                    this.xmxxForm = obj;
                }
            });

        }
    }

</script>

<style scoped>

</style>