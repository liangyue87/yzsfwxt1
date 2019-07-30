<template>
    <section class="chart-container">
        <el-row>
            <el-col :span="24" >
                <el-form style="margin-top: 10px" size="small">
                    <el-form-item>
                        <el-col :span="3">
                            <el-radio-group v-model="resource">
                                <el-radio-button label="图表"></el-radio-button>
                                <el-radio-button label="表格" @click.native="pushZyzbtjfx"></el-radio-button>
                            </el-radio-group>
                        </el-col>
                        <el-col :span="3">
                            <el-date-picker type="month" placeholder="选择时间" v-model="jssj" value-format="yyyy-MM" style="width: 100%;">
                            </el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: center">
                            <el-button size="mini" type="primary" @click="query_vue()"><i class="iconfont icon-chaxun"></i>查询</el-button>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">本期受理</div>
                    </div>
                    <div style="height: 200px">
                        <div id="bqsl" style="width:100%; height:200px;"></div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">勘查完成</div>
                    </div>
                    <div style="height: 200px">
                        <div id="kcwc" style="width:100%; height:200px;"></div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目立项</div>
                    </div>
                    <div style="height: 200px">
                        <div id="xmlx" style="width:100%; height:200px;"></div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">预算完成</div>
                    </div>
                    <div style="height: 200px">
                        <div id="yswc" style="width:100%; height:200px;"></div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">设计完成</div>
                    </div>
                    <div style="height: 200px">
                        <div id="sjwc" style="width:100%; height:200px;"></div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">合同签订</div>
                    </div>
                    <div style="height: 200px">
                        <div id="htqd" style="width:100%; height:200px;"></div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">费用收取</div>
                    </div>
                    <div style="height: 200px">
                        <div id="fysq" style="width:100%; height:200px;"></div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">施工下单</div>
                    </div>
                    <div style="height: 200px">
                        <div id="sgxd" style="width:100%; height:200px;"></div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">用户结算</div>
                    </div>
                    <div style="height: 200px">
                        <div id="yhjs" style="width:100%; height:200px;"></div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import echarts from 'echarts'
    export default {
        name: "zyzb",
        data(){
            return{
                resource:'图表',
                jssj:'',

                bqsl:null,
                kcwc:null,
                xmlx:null,
                yswc:null,
                sjwc:null,
                htqd:null,
                fysq:null,
                sgxd:null,
                yhjs:null,
            }
        },
        mounted(){
            this.drawCharts();
        },
        updated: function () {
            this.drawCharts();
        },
        methods:{
            pushZyzbtjfx(){
                this.$router.push({path: '/zyzbtjfx', query: {}});
            },
            drawbqsl(){
                this.bqsl = echarts.init(document.getElementById('bqsl'));
                this.bqsl.setOption({
                    tooltip : {'trigger':'axis'},
                    legend : {
                        x:'left',
                        data:['受理数','户数'],
                    },
                    xAxis : [{
                        type:'category',
                        axisLabel:{'interval':0},
                        data:['工商类','居民类','户改类']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'受理数（个）',
                        },
                        {
                            type:'value',
                            name:'户数（户）'
                        }
                    ],
                    series : [
                        {
                            name:'受理数',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                    },
                                },
                            barWidth:20,
                            data: [12,3,14]
                        },
                        {
                            name:'户数',yAxisIndex:1,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:20,
                            data: [14,5,16]
                        }
                    ]
                });
            },
            drawkcwc(){
                this.kcwc = echarts.init(document.getElementById('kcwc'));
                this.kcwc.setOption({
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        x:'left',
                        data:['完成数']
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : ['工商类','居民类','户改类']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            name:'完成数（个）'
                        }
                    ],
                    series : [
                        {
                            name:'完成数',
                            type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:20,
                            data:[12,45,34],
                        },
                    ]
                });
            },
            drawxmlx(){
                this.xmlx = echarts.init(document.getElementById('xmlx'));
                this.xmlx.setOption({
                    tooltip : {'trigger':'axis'},
                    legend : {
                        x:'left',
                        data:['项目数','户数'],
                    },
                    xAxis : [{
                        type:'category',
                        axisLabel:{'interval':0},
                        data:['工商类','居民类','户改类']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'项目数（个）',
                        },
                        {
                            type:'value',
                            name:'户数（户）'
                        }
                    ],
                    series : [
                        {
                            name:'项目数',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:20,
                            data: [23,11,15]
                        },
                        {
                            name:'户数',yAxisIndex:1,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:20,
                            data: [20,9,13]
                        }
                    ]
                });
            },
            drawyswc(){
                this.yswc = echarts.init(document.getElementById('yswc'));
                this.yswc.setOption({
                    tooltip : {'trigger':'axis'},
                    legend : {
                        x:'left',
                        data:['数量','金额'],
                    },
                    xAxis : [{
                        type:'category',
                        axisLabel:{'interval':0},
                        data:['工商类','居民类','户改类']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'数量（笔）',
                        },
                        {
                            type:'value',
                            name:'金额（万元）'
                        }
                    ],
                    series : [
                        {
                            name:'数量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:20,
                            data: [33,25,37]
                        },
                        {
                            name:'金额',yAxisIndex:1,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:20,
                            data: [130,176,163]
                        }
                    ]
                });
            },
            drawsjwc(){
                this.sjwc = echarts.init(document.getElementById('sjwc'));
                this.sjwc.setOption({
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        x:'left',
                        data:['完成数']
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : ['工商类','居民类','户改类']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            name:'完成数（个）'
                        }
                    ],
                    series : [
                        {
                            name:'完成数',
                            type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:20,
                            data:[32,20,45],
                        },
                    ]
                });
            },
            drawhtqd(){
                this.htqd = echarts.init(document.getElementById('htqd'));
                this.htqd.setOption({
                    tooltip : {'trigger':'axis'},
                    legend : {
                        x:'left',
                        data:['签订数','金额'],
                    },
                    xAxis : [{
                        type:'category',
                        axisLabel:{'interval':0},
                        data:['工商类','居民类','户改类']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'签订数（个）',
                        },
                        {
                            type:'value',
                            name:'金额（万元）'
                        }
                    ],
                    series : [
                        {
                            name:'签订数',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:20,
                            data: [23,14,46]
                        },
                        {
                            name:'金额',yAxisIndex:1,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:20,
                            data: [178,387,432]
                        }
                    ]
                });
            },
            drawfysq(){
                this.fysq = echarts.init(document.getElementById('fysq'));
                this.fysq.setOption({
                    tooltip : {'trigger':'axis'},
                    legend : {
                        x:'left',
                        data:['数量','金额'],
                    },
                    xAxis : [{
                        type:'category',
                        axisLabel:{'interval':0},
                        data:['工商类','居民类','户改类']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'数量（笔）',
                        },
                        {
                            type:'value',
                            name:'金额（万元）'
                        }
                    ],
                    series : [
                        {
                            name:'数量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:20,
                            data: [77,54,87]
                        },
                        {
                            name:'金额',yAxisIndex:1,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:20,
                            data: [567,467,168]
                        }
                    ]
                });
            },
            drawsgxd(){
                this.sgxd = echarts.init(document.getElementById('sgxd'));
                this.sgxd.setOption({
                    tooltip : {'trigger':'axis'},
                    legend : {
                        x:'left',
                        data:['项目数','金额'],
                    },
                    xAxis : [{
                        type:'category',
                        axisLabel:{'interval':0},
                        data:['工商类','居民类','户改类']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'项目数（个）',
                        },
                        {
                            type:'value',
                            name:'金额（万元）'
                        }
                    ],
                    series : [
                        {
                            name:'项目数',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:20,
                            data: [26,49,36]
                        },
                        {
                            name:'金额',yAxisIndex:1,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:20,
                            data: [349,267,489]
                        }
                    ]
                });
            },
            drawyhjs(){
                this.yhjs = echarts.init(document.getElementById('yhjs'));
                this.yhjs.setOption({
                    tooltip : {'trigger':'axis'},
                    legend : {
                        x:'left',
                        data:['数量','金额'],
                    },
                    xAxis : [{
                        type:'category',
                        axisLabel:{'interval':0},
                        data:['工商类','居民类','户改类']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'数量（笔）',
                        },
                        {
                            type:'value',
                            name:'金额（万元）'
                        }
                    ],
                    series : [
                        {
                            name:'数量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:20,
                            data: [22,15,35]
                        },
                        {
                            name:'金额',yAxisIndex:1,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:20,
                            data: [140,50,160]
                        }
                    ]
                });
            },
            drawCharts(){
                this.drawbqsl();
                this.drawkcwc();
                this.drawxmlx();
                this.drawyswc();
                this.drawsjwc();
                this.drawhtqd();
                this.drawfysq();
                this.drawsgxd();
                this.drawyhjs();
            },
            query_vue(){

            }
        }
    }
</script>

<style scoped>

</style>