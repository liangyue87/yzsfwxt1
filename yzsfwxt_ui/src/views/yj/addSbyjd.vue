<template>
    <section>
        <el-row>
            <el-col :span="24">
                <div v-if="isTh=='1'">
                    <!--红黄绿灯时间-->
                    <hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
                </div>

                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">移交单信息</div>
                    </div>
                    <el-form>
                        <el-form-item>
                            <el-col :span="2" style="text-align: right">
                                <span>移交单编号：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="yjdbh" style="width: 240px" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>
                <SBYJGX ref="sbyj" :xmmc="this.xmmc" v-bind:xmbh="this.xmbh" v-bind:yjdbh="this.yjdbh" v-bind:gclx="this.gclx" v-bind:jsdw="jsdw"></SBYJGX>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import SBYJGX from '@/views/template/sbyjgx.vue';
    import hhldsx from '@/views/template/hhldsx.vue';
    import { queryYjdbh } from '../../api/sbyj';
    export default {
        name: "addSbyjd",
        components: {
            'hhldsx': hhldsx,
            'SBYJGX': SBYJGX,  //水表移交管理组件
        },
        data() {
            return {
                xmid:this.$route.query.xmid,//项目id
                xmbh:this.$route.query.xmbh,//项目编号
                xmmc:this.$route.query.xmmc,//项目名称
                zyjdbh:this.$route.query.zyjdbh,//返回传过来的移交单编号
                jsdw:this.$route.query.jsdw,//建设单位
                gclx:this.$route.query.gclx,//项目名称

                isEdit:this.$route.query.isEdit,//是否编辑
                yjzbid:this.$route.query.yjzbid,//移交主表ID
                isTh:this.$route.query.isTh,
                activityId:this.$route.query.activityId,

                yjdbh:'',//移交单编号
            }
        },
        mounted() {
            this.queryinit();
        },
        methods: {
            //初始化方法
            queryinit(){
                if(this.zyjdbh == undefined){
                    let params = {};
                    queryYjdbh(params).then(res=>{
                        this.yjdbh = res.data.rwbh;
                        setTimeout(() => {
                            this.$refs.sbyj.yjchildinit(this.yjdbh);
                        },0);
                    });
                }else{
                    this.yjdbh = this.zyjdbh;
                    setTimeout(() => {
                        this.$refs.sbyj.yjchildinit(this.yjdbh);
                    },0);
                }

            },
        }
    }
</script>

<style scoped>

</style>