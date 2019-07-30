<template>
    <section class="chart-container">
        <el-row>
            <el-col :span="24" >
                <el-form :model="filters" style="margin-top: 10px" size="small">
                    <el-form-item>
                        <el-col :span="3">
                            <el-date-picker type="month" placeholder="选择开始时间" v-model="filters.kssj" value-format="yyyy-MM" style="width: 100%;">
                            </el-date-picker>
                        </el-col>
                        <el-col class="line" :span="1" style="text-align: center">-</el-col>
                        <el-col :span="3">
                            <el-date-picker type="month" placeholder="选择结束时间" v-model="filters.jssj" value-format="yyyy-MM" style="width: 100%;">
                            </el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: center">
                            <el-button size="mini" type="primary" @click="query_vue()"><i class="iconfont icon-chaxun"></i>查询</el-button>
                        </el-col>
                    </el-form-item>
                </el-form>
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">水表下单移交跟踪图表</div>
                    </div>
                    <div style="height: 300px">
                        <div id="chartLine" style="width:100%; height:300px;"></div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-card class="box-card"  style="margin-top: 15px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">五级计量表</div>
                </div>
                <div style="height: 300px">
                    <div id="wbchartLine" style="width:100%; height:300px;"></div>
                </div>
            </el-card>
        </el-row>
        <el-row>
            <el-card class="box-card"  style="margin-top: 15px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">工商表</div>
                </div>
                <div style="height: 300px">
                    <div id="gsbchartLine" style="width:100%; height:300px;"></div>
                </div>
            </el-card>
        </el-row>
        <el-row>
            <el-card class="box-card"  style="margin-top: 15px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">居民表</div>
                </div>
                <div style="height: 300px">
                    <div id="jmbchartLine" style="width:100%; height:300px;"></div>
                </div>
            </el-card>
        </el-row>
    </section>
</template>

<script>
    import echarts from 'echarts'
    export default {
        name: "sbjs",
        data(){
            return{
                filters:{
                    kssj:'',
                    jssj:'',
                },
                chartLine:null,
                wbchartLine:null,
                gsbchartLine:null,
                jmbchartLine:null
            }
        },
        mounted(){
            this.drawCharts();
        },
        updated: function () {
            this.drawCharts();
        },
        methods:{
            drawLineChart() {
                this.chartLine = echarts.init(document.getElementById('chartLine'));
                this.chartLine.setOption({
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['该月报装量', '当前下单量', '当前装表量','当前移交量']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: ['2018.01','2018.02','2018.03','2018.04','2018.05','2018.06','2018.07','2018.08'
                            ,'2018.09','2018.10','2018.11','2018.12','2019.01']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '该月报装量',
                            type: 'line',
                            itemStyle : {
                                normal : {
                                    lineStyle:{
                                        color:'#26d53f' //改变折线颜色
                                    }
                                }
                            },
                            data: [440, 442, 450, 380, 380, 520, 480,434, 467, 501, 491, 470, 453]
                        },
                        {
                            name: '当前下单量',
                            type: 'line',
                            itemStyle : {
                                normal : {
                                    lineStyle:{
                                        color:'#595ad5' //改变折线颜色
                                    }
                                }
                            },
                            data: [430,387,397,353,349,402,407,364,376,410,324,265,189]
                        },
                        {
                            name: '当前装表量',
                            type: 'line',
                            itemStyle : {
                                normal : {
                                    lineStyle:{
                                        color:'#8927d5' //改变折线颜色
                                    }
                                }
                            },
                            data: [420,301,267,244,241,302,323,261,273,305,216,187,124]
                        },
                        {
                            name: '当前移交量',
                            type: 'line',
                            itemStyle : {
                                normal : {
                                    lineStyle:{
                                        color:'#0a9cd5' //改变折线颜色
                                    }
                                }
                            },
                            data: [400,211,173,152,136,214,219,159,183,202,120,102,86]
                        }
                    ]
                });
            },
            drawwbBarChart(){
                this.wbchartLine = echarts.init(document.getElementById('wbchartLine'));
                this.wbchartLine.setOption({
                    tooltip : {'trigger':'axis'},
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '2%',
                        containLabel: true
                    },
                    legend : {
                        x:'left',
                        data:['该月报装量', '当前下单量', '当前装表量','当前移交量'],
                    },
                    xAxis : [{
                        type:'category',
                        data: ['2018.01','2018.02','2018.03','2018.04','2018.05','2018.06','2018.07','2018.08'
                            ,'2018.09','2018.10','2018.11','2018.12','2019.01']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'数量（个）',
                        }
                    ],
                    series : [
                        {
                            name:'该月报装量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:10,
                            data: [54,45,67,32,31,43,52,37,42,36,33,56,64]
                        },
                        {
                            name:'当前下单量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:10,
                            data: [53,44,64,29,27,40,46,34,38,34,25,48,55]
                        },
                        {
                            name:'当前装表量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#f4a6ed'
                                },
                            },
                            barWidth:10,
                            data: [50,41,61,27,24,36,43,30,34,30,21,40,50]
                        },
                        {
                            name:'当前移交量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#9cf4d6'
                                },
                            },
                            barWidth:10,
                            data: [47,38,58,24,21,33,40,27,31,26,18,35,40]
                        }
                    ]
                });
            },
            drawgsbBarChart(){
                this.gsbchartLine = echarts.init(document.getElementById('gsbchartLine'));
                this.gsbchartLine.setOption({
                    tooltip : {'trigger':'axis'},
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '2%',
                        containLabel: true
                    },
                    legend : {
                        x:'left',
                        data:['该月报装量', '当前下单量', '当前装表量','当前移交量'],
                    },
                    xAxis : [{
                        type:'category',
                        data: ['2018.01','2018.02','2018.03','2018.04','2018.05','2018.06','2018.07','2018.08'
                            ,'2018.09','2018.10','2018.11','2018.12','2019.01']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'数量（个）',
                        }
                    ],
                    series : [
                        {
                            name:'该月报装量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:10,
                            data: [44,35,57,22,21,33,42,27,32,26,33,46,54]
                        },
                        {
                            name:'当前下单量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:10,
                            data: [40,34,54,19,17,30,36,24,28,24,25,38,45]
                        },
                        {
                            name:'当前装表量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#f4a6ed'
                                },
                            },
                            barWidth:10,
                            data: [36,31,51,16,15,27,33,21,23,19,21,31,39]
                        },
                        {
                            name:'当前移交量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#9cf4d6'
                                },
                            },
                            barWidth:10,
                            data: [33,28,47,13,12,24,30,17,19,13,17,25,30]
                        }
                    ]
                });
            },
            drawjmbBarChart(){
                this.jmbchartLine = echarts.init(document.getElementById('jmbchartLine'));
                this.jmbchartLine.setOption({
                    tooltip : {'trigger':'axis'},
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '2%',
                        containLabel: true
                    },
                    legend : {
                        x:'left',
                        data:['该月报装量', '当前下单量', '当前装表量','当前移交量'],
                    },
                    xAxis : [{
                        type:'category',
                        data: ['2018.01','2018.02','2018.03','2018.04','2018.05','2018.06','2018.07','2018.08'
                            ,'2018.09','2018.10','2018.11','2018.12','2019.01']
                    }],
                    yAxis : [
                        {
                            type:'value',
                            name:'数量（个）',
                        }
                    ],
                    series : [
                        {
                            name:'该月报装量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#FE8463'
                                },
                            },
                            barWidth:10,
                            data: [64,55,77,42,41,53,62,47,52,46,43,66,74]
                        },
                        {
                            name:'当前下单量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#F4E001'
                                },
                            },
                            barWidth:10,
                            data: [60,50,73,38,37,49,58,44,49,43,40,60,64]
                        },
                        {
                            name:'当前装表量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#f4a6ed'
                                },
                            },
                            barWidth:10,
                            data: [57,46,68,35,34,45,53,40,45,39,36,53,55]
                        },
                        {
                            name:'当前移交量',yAxisIndex:0,type:'bar',
                            itemStyle: {
                                normal: {
                                    color: '#9cf4d6'
                                },
                            },
                            barWidth:10,
                            data: [54,42,63,32,30,42,49,37,41,34,31,46,46]
                        }
                    ]
                });
            },
            drawCharts(){
              this.drawLineChart();
              this.drawwbBarChart();
              this.drawgsbBarChart();
              this.drawjmbBarChart();
            },
            query_vue(){

            }
        }

    }
</script>

<style scoped>

</style>