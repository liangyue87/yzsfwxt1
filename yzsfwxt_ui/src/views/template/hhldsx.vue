<template>
    <section>
        <el-card class="box-card" style="margin-top: 15px;">
            <div slot="header" class="clearfix">
                <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">红黄绿灯时限

                    <el-button v-if="type == '1'" style="float: right; padding: 3px 0" type="text" @click="getXmqjt()">项目全景图</el-button>
                </div>
            </div>
            <div class="text item">
                <el-row v-if="finishLimited==1||finishLimited=='1'" style="margin: auto 0">
                    <el-col :span="8" class="rightborder">
                        <table class="table">
                            <tr>
                                <td class="lightTd">
                                    <i class="el-icon-s-opportunity light" v-if="!isGreen"></i>
                                    <i class="el-icon-s-opportunity light lignt_green "  v-if="isGreen"></i>
                                </td>
                                <td>
                                    <div class="wordTd">
                                        <div style="margin-bottom: 8px">
                                            <label class="startTime">绿灯开启时间</label>
                                            <label class="sjc sjc_green" v-if="isGreen">距离黄灯时间剩余{{sjc}}</label>
                                            <label class="sjc " v-if="!isGreen"></label>
                                        </div>

                                        <label class="time" v-if="isGreen">{{greenLight}}</label>
                                        <label class="time" v-if="!isGreen">{{greenLight}}</label>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </el-col>
                    <el-col :span="8" class="rightborder">
                        <table class="table">
                            <tr>
                                <td  class="lightTd">
                                    <i class="el-icon-s-opportunity light" v-if="!isYellow"></i>
                                    <i class="el-icon-s-opportunity light lignt_yellow" v-if="isYellow"></i>
                                </td>
                                <td>
                                    <div  class="wordTd">
                                        <div style="margin-bottom: 8px">
                                            <label class="startTime">黄灯开启时间</label>
                                            <label class="sjc sjc_yellow" v-if="isYellow">距离红灯时间剩余{{sjc}}</label>
                                            <label class="sjc" v-if="!isYellow"></label>
                                        </div>
                                        <label class="time" v-if="isYellow">{{yellowLight}}</label>
                                        <label class="time" v-if="!isYellow">{{yellowLight}}</label>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </el-col>
                    <el-col :span="8">
                        <table class="table">
                            <tr>
                                <td class="lightTd">
                                    <i class="el-icon-s-opportunity light" v-if="!isRed"></i>
                                    <i class="el-icon-s-opportunity light lignt_red"  v-if="isRed"></i>
                                </td>
                                <td>
                                    <div  class="wordTd">
                                        <div style="margin-bottom: 8px">
                                            <label class="startTime">红灯开启时间</label>
                                            <label class="sjc sjc_red" v-if="isRed">已超红灯时间{{sjc}}</label>
                                            <label class="sjc " v-if="!isRed"></label>
                                        </div>
                                        <label class="time" v-if="isRed">{{redLight}}</label>
                                        <label class="time" v-if="!isRed">{{redLight}}</label>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </el-col>
                </el-row>
                <el-row v-else style="text-align: center">
                    不限制红黄绿灯
                </el-row>
            </div>
        </el-card>
    </section>
</template>
<script>
    import {initHhld} from '../../api/sjst';
    export default {
        props:{
            xmid:{
                type: String,
                required: true
            },
            activityid:{
                type:String,
                required:true
            },
            type:{
                required:false
            }
        },
        data(){
            return{
                //xmid:"",
                //activityId:"",
                yellowLight:"",
                greenLight:"",
                redLight:"",
                isGreen:false,
                isYellow:false,
                isRed:false,
                finishLimited:false,
                sjc:""
            }
        },
        methods:{
            getXmqjt(){
                this.$router.push({path: '/Xmlct',query: {xmid:this.xmid}});
            },
            initHhld_vue(){
                let params={
                    xmid:this.xmid,
                    activityid: this.activityid
                }
                initHhld(params).then((res)=>{
                    if(res.data!=null){
                        this.greenLight = res.data.row[0].greenLight;
                        this.redLight = res.data.row[0].redLight;
                        this.yellowLight = res.data.row[0].yellowLight;
                        this.isGreen = res.data.isGreen;
                        this.isYellow = res.data.isYellow;
                        this.isRed = res.data.isRed;
                        this.sjc = res.data.sjc;
                        if(this.redLight.indexOf('2099')>=0){
                            this.finishLimited=0
                        }else{
                            this.finishLimited = res.data.row[0].finish_limited;
                        }
                    }
                })
            }
        },
        mounted(){
            this.initHhld_vue();
        }
    }
</script>
<style>
    .light{
        font-size: 30px;
        color: #7e7e7e;
    }
    .lignt_green{
        color:#67c23a
    }
    .lignt_yellow{
        color:#ff9f00
    }
    .lignt_red{
        color:#f56c6c
    }
    .time{
        font-size: 29px;
        color: #606266;
    }
    .startTime{
        font-size: 12px;
        display: inline-block;
        width: 75px;
    }
    .sjc{
        font-size: 11px;
        color: white;
        padding: 5px;
        border-radius: 4px;
        display: inline-block;
        width: 185px;
    }
    .sjc_green{
        background-color: #67c23a;
    }
    .sjc_yellow{
        background-color: #ff9f00;
    }
    .sjc_red{
         background-color: #f56c6c;
     }
    .rightborder{
        border-right: 1px solid #e5e5e5;
    }
    .table{
        width: 100%;
    }
    .lightTd{
        text-align: right;
        width: 50px;
    }
    .wordTd{
        /*text-align: center;*/
        margin-left: 20px;
    }
</style>