<template>
    <section>
        <el-row>
            <el-col>
                <el-card class="box-card" style="margin-top: 10px">
                    <div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
                        <span>预算汇总表</span>
                       <!-- <el-button style="float: right; padding: 3px 0" type="text" @click="openYsxx">选择预算项</el-button>-->
                    </div>
                    <div class="text item">
                        <el-row>
                            <el-col :span="24">
                                <el-card class="box-card" style="width: 98%;">
                                    <div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;"><span>预算范围</span></div>
                                    <div class="text item">
                                        <el-col :span="6" style="text-align: center;">
                                            <span><b>预算分类</b></span>
                                        </el-col>
                                        <el-col :span="3" style="text-align: center;">
                                            <span><b>金额</b></span>
                                        </el-col>
                                        <el-col :span="9" style="text-align: center;">
                                            <span><b>备注</b></span>
                                        </el-col>
                                        <el-form ref="form" :model="formData" label-width="80px">
                                            <el-form-item
                                                    v-for="(a, index) in formData.domains"
                                                    :label="a.mc"
                                                    label-width="200px"
                                                    :key="index"
                                                    :prop="'domains.' + index + '.ysje'"
                                            >
                                                <el-input
                                                        v-if="a.mc != null"
                                                        v-model="a.ysje"
                                                        placeholder="预算金额"
                                                        class="cd"
                                                        :disabled="true"
                                                        id="n1"
                                                        :min = 0
                                                        type="number"
                                                ></el-input>
                                                <el-input class="cd" v-if="a.mc != null" v-model="a.bz" :disabled="true" placeholder="预算范围说明"></el-input>
                                            </el-form-item>
                                            <JEJS :value="zje"></JEJS>
                                        </el-form>
                                    </div>
                                </el-card>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="24" style="text-align: center;margin-top: 0px;"></el-col>
        </el-row>
<!--        <el-row style="margin-top: 15px;">
            <el-col :span="24" >
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">预算成果表</div>

                    </div>
                    <div class="text item" id="row">
                        <el-row>
                            <el-col :span="4" style="text-align: center">预算分类</el-col>
                            <el-col :span="4" style="text-align: center">金额</el-col>
                            <el-col :span="4" style="text-align: center">备注</el-col>
                        </el-row>
                        <el-row style="margin-top: 20px">
                            <el-col :span="4" style="text-align: center">庭院给水官网</el-col>
                            <el-col :span="4" style="text-align: center">{{ty_je}}</el-col>
                            <el-col :span="4" style="text-align: center">{{ty_bz}}</el-col>
                        </el-row>
                        <el-row style="margin-top: 10px">
                            <el-col :span="4" style="text-align: center">生活泵房</el-col>
                            <el-col :span="4" style="text-align: center">{{bf_je}}</el-col>
                            <el-col :span="4" style="text-align: center">{{bf_bz}}</el-col>
                        </el-row>
                        <el-row style="margin-top: 10px">
                            <el-col :span="4" style="text-align: center">户表部分</el-col>
                            <el-col :span="4" style="text-align: center">{{hb_je}}</el-col>
                            <el-col :span="4" style="text-align: center">{{hb_bz}}</el-col>
                        </el-row>
                        <el-row style="margin-top: 10px">
                            <el-col :span="4" style="text-align: center">吊管部分</el-col>
                            <el-col :span="4" style="text-align: center">{{dg_je}}</el-col>
                            <el-col :span="4" style="text-align: center">{{dg_bz}}</el-col>
                        </el-row>
                        <el-row style="margin-top: 10px">
                            <el-col :span="4" style="text-align: center">工商表</el-col>
                            <el-col :span="4" style="text-align: center">{{gsb_je}}</el-col>
                            <el-col :span="4" style="text-align: center">{{gsb_bz}}</el-col>
                        </el-row>
                        <el-row style="margin-top: 10px">
                            <el-col :span="4" style="text-align: center">工程设计费</el-col>
                            <el-col :span="4" style="text-align: center">{{gcsjf_je}}</el-col>
                            <el-col :span="4" style="text-align: center">{{gcsjf_bz}}</el-col>
                        </el-row>
                        <el-row style="margin-top: 10px">
                            <el-col :span="4" style="text-align: center">工程监理费</el-col>
                            <el-col :span="4" style="text-align: center">{{gcjlf_je}}</el-col>
                            <el-col :span="4" style="text-align: center">{{gcjlf_bz}}</el-col>
                        </el-row>
                        <el-row style="margin-top: 10px">
                            <el-col :span="4" style="text-align: center">其他费用</el-col>
                            <el-col :span="4" style="text-align: center">{{qtfy_je}}</el-col>
                            <el-col :span="4" style="text-align: center"></el-col>
                            <el-col :span="4" style="text-align: center">{{qtfy_bz}}</el-col>
                        </el-row>
                        <el-row style="margin-top: 10px">
                            <el-col :span="4" style="text-align: center">总计：</el-col>
                            <el-col :span="4" style="text-align: center">{{zj_je}}</el-col>
                        </el-row>
                        <el-row style="margin-top: 10px">
                            <el-col :span="4" style="text-align: center">大写：</el-col>
                            <el-col :span="4" style="text-align: center">{{zj_je | toChies}}</el-col>
                        </el-row>

                    </div>
                    </el-card>
            </el-col>
         </el-row>-->
    </section>
</template>

<script>
    import {SelectYshz,yszbid} from '../../api/Js'
    import JEJS from '../../components/Jejs.vue';
    export default {
        name: "yshzb.vue",
        data() {
            return {
                formData: {
                    domains: [{}]
                },
                zje:'',
                checkList:[],

                xmid: this.$route.query.xmid,
            }
        },
        mounted() {
            this.rwcbsj();
        },
        components:{
            JEJS,
        },
        methods:{
            rwcbsj(){//查询任务从表中数据
                let params = {
                    xmid:this.xmid,
                };
                yszbid(params).then(res => {

                    this.ysRwcb = res.data.ysrwcb;

                    for (let i = 0; i < this.ysRwcb.length; i++) {
                        this.checkList[i]=this.ysRwcb[i].ysfl;
                        this.formData.domains.push(this.ysRwcb[i]);
                    }
                    this.getZje();
                });
            },
            //得到总金额
            getZje(res) {
                let data = this.formData.domains;
                this.zje = 0;
                data.forEach((a, index) => {
                    if (a.ysje != '' && index != 0) {
                        let num = Number(a.ysje);
                        if(  isNaN(num)){
                            num = 0;
                        }
                        if (this.zje == 0) {
                            this.zje = num;
                        } else {
                            this.zje = num + Number(this.zje);
                        }
                    }
                    this.zje = parseFloat(this.zje).toFixed(2);
                });

            },

        },
    }
</script>

<style>
</style>