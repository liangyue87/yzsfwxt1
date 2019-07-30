<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--红黄绿灯时间-->
                <hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>

                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <span>移交单信息</span>
                    </div>
                    <el-form>
                        <el-form-item>
                            <el-col :span="2" style="text-align: right">
                                <span>移交单编号：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="yjdbh"  style="width: 240px" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>
                <SBYJSH ref="sbyj"></SBYJSH>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import SBYJSH from '@/views/template/sbyjsh.vue';
    import hhldsx from '@/views/template/hhldsx.vue';
    import { queryYjdbh } from '../../api/sbyj';
    export default {
        name: "addSbyjd",
        components: {
            'hhldsx': hhldsx,
            'SBYJSH': SBYJSH,  //水表移交管理组件
        },
        data() {
            return {
                xmid:this.$route.query.xmid,//项目id
                yjdbh:this.$route.query.rwbh,//移交单编号
                activityId:this.$route.query.activityId,

            }
        },
        mounted() {
            this.queryinit();
        },
        methods: {
            //初始化方法
            queryinit(){
                setTimeout(() => {
                    this.$refs.sbyj.yjchildinit(this.yjdbh);
                },0);
            },
        }
    }
</script>

<style scoped>

</style>