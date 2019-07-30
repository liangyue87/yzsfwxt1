<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--项目基本信息模板-->
                <GWXXSJ></GWXXSJ>
                <!--配合联合勘查的部门-->
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <span>附件资料</span>
                    </div>
                    <div class="text item">
                        <el-row class="row">
                            <el-col :span="1">&nbsp;</el-col>
                            <el-col :span="4">勘查部门</el-col>
                            <el-col :span="4">是否要求到场</el-col>

                        </el-row>
                        <el-row class="row">
                            <el-col :span="1">&nbsp;</el-col>
                            <el-col :span="4" >设计院</el-col>
                            <el-col :span="4"><el-switch v-model="check_value.sjy_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>

                        </el-row>
                        <el-row class="row">
                            <el-col :span="1">&nbsp;</el-col>
                            <el-col :span="4">施工部</el-col>
                            <el-col :span="4"><el-switch v-model="check_value.sgdw_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>

                        </el-row>
                        <el-row class="row">
                            <el-col :span="1">&nbsp;</el-col>
                            <el-col :span="4">供水所</el-col>
                            <el-col :span="4"><el-switch v-model="check_value.gss_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>

                        </el-row>
                        <el-row class="row">
                            <el-col :span="1">&nbsp;</el-col>
                            <el-col :span="4">预算部门</el-col>
                            <el-col :span="4"><el-switch v-model="check_value.ysk_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>

                        </el-row>
                        <el-row style="margin-top: 20px;">
                            <el-col :span="12">&nbsp;</el-col>
                            <el-col :span="12">
                                <el-button type="primary" @click="saveXckcTask">提交</el-button>
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
    .row{
        margin-top: 14px;
    }
    .row .el-col{
        text-align: center;
    }
</style>
<script>
    import GWXXSJ from '@/views/template/gwjbxx.vue';
    import util from '../../common/js/util'

    import { saveXckcRwzb } from '../../api/xkylx';
    export default {
        data() {
            return {

                check_value :{
                    sjy_value: true,
                    sgdw_value: true,
                    gss_value: true,
                    ysk_value: true
                }
            }
        },
        methods: {
            toXckcrwcj(){//跳转页面Xckcrwcj
                this.$router.push({path: '/Xckcrwcj', query: {}});
            },
            saveXckcTask(){//发起联合勘查
                let params = Object.assign( this.check_value)
                saveXckcRwzb(params).then((res) => {
                    toXckcrwcj();
                });
            }
        },
        mounted() {

        },
        components: {
            'GWXXSJ': GWXXSJ  //将别名demo 变成 组件 Demo
        }

    }

</script>

<style scoped>

</style>