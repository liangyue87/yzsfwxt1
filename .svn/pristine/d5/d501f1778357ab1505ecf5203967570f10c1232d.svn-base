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
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">费用产生与收取跟踪图表</div>
                    </div>
                    <div style="height: 300px">
                        <div id="chartLine" style="width:100%; height:300px;"></div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">工商</div>
                    </div>
                    <div style="height: 300px">
                        <div id="gschartBar" style="width:100%; height:300px;"></div>
                    </div>
                    <div style="height: 200px">
                        <el-col :span="3">
                            <span>预算</span>
                        </el-col>
                        <el-col :span="7" style="height: 200px">
                            <div id="gshtchartybp" style="width:100%; height:200px;"></div>
                        </el-col>
                        <el-col :span="7" style="height: 200px">
                            <div id="gssfdchartybp" style="width:100%; height:200px;"></div>
                        </el-col>
                        <el-col :span="7" style="height: 200px">
                            <div id="gsdzchartybp" style="width:100%; height:200px;"></div>
                        </el-col>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">居民</div>
                    </div>
                    <div style="height: 300px">
                        <div id="jmchartBar" style="width:100%; height:300px;"></div>
                    </div>
                    <div style="height: 200px">
                        <el-col :span="3">
                            <span>预算</span>
                        </el-col>
                        <el-col :span="7" style="height: 200px">
                            <div id="jmhtchartybp" style="width:100%; height:200px;"></div>
                        </el-col>
                        <el-col :span="7" style="height: 200px">
                            <div id="jmsfdchartybp" style="width:100%; height:200px;"></div>
                        </el-col>
                        <el-col :span="7" style="height: 200px">
                            <div id="jmdzchartybp" style="width:100%; height:200px;"></div>
                        </el-col>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">户改</div>
                    </div>
                    <div style="height: 300px">
                        <div id="hgchartBar" style="width:100%; height:300px;"></div>
                    </div>
                    <div style="height: 200px">
                        <el-col :span="3">
                            <span>预算</span>
                        </el-col>
                        <el-col :span="7" style="height: 200px">
                            <div id="hghtchartybp" style="width:100%; height:200px;"></div>
                        </el-col>
                        <el-col :span="7" style="height: 200px">
                            <div id="hgsfdchartybp" style="width:100%; height:200px;"></div>
                        </el-col>
                        <el-col :span="7" style="height: 200px">
                            <div id="hgdzchartybp" style="width:100%; height:200px;"></div>
                        </el-col>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">预算</div>
                    </div>
                    <div style="height: 300px">
                        <div id="yschartPie" style="width:100%; height:300px;"></div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">到账</div>
                    </div>
                    <div style="height: 300px">
                        <div id="dzchartPie" style="width:100%; height:300px;"></div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "fytj",
        data(){
            return{
                filters:{
                    kssj:'',
                    jssj:'',
                },
                chartLine:null,
                gschartBar:null,
                jmchartBar:null,
                hgchartBar:null,
                gshtchartybp:null,
                gssfdchartybp:null,
                gsdzchartybp:null,
                jmhtchartybp:null,
                jmsfdchartybp:null,
                jmdzchartybp:null,
                hghtchartybp:null,
                hgsfdchartybp:null,
                hgdzchartybp:null,
                yschartPie:null,
                dzchartPie:null,
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
                        data: ['预算', '合同','收费单', '到账']
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
                            name: '预算',
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
                            name: '合同',
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
                            name: '收费单',
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
                            name: '到账',
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
            drawgsBarChart() {
                this.gschartBar = echarts.init(document.getElementById('gschartBar'));
                this.gschartBar.setOption({
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ['预算','合同','收费单','到账'],
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            type:'bar',
                            barWidth: '30%',
                            data:[23, 52, 35, 53]
                        }
                    ]
                });
            },
            drawjmBarChart() {
                this.jmchartBar = echarts.init(document.getElementById('jmchartBar'));
                this.jmchartBar.setOption({
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ['预算','合同','收费单','到账'],
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            type:'bar',
                            barWidth: '30%',
                            data:[45, 34, 24, 56]
                        }
                    ]
                });
            },
            drawhgBarChart() {
                this.hgchartBar = echarts.init(document.getElementById('hgchartBar'));
                this.hgchartBar.setOption({
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ['预算','合同','收费单','到账'],
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            type:'bar',
                            barWidth: '30%',
                            data:[45, 21, 56, 78]
                        }
                    ]
                });
            },
            drawgshtYbpChart(){
                this.gshtchartybp = echarts.init(document.getElementById('gshtchartybp'));
                this.gshtchartybp.setOption({
                    tooltip : {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    series : [
                        {
                            type:'gauge',
                            splitNumber: 10,       // 分割段数，默认为5
                            axisLine: {            // 坐标轴线
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                    width: 8
                                }
                            },
                            axisTick: {            // 坐标轴小标记
                                splitNumber: 10,   // 每份split细分多少段
                                length :12,        // 属性length控制线长
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: 'auto'
                                }
                            },
                            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto'
                                }
                            },
                            pointer : {
                                width : 5
                            },
                            title : {
                                show : true,
                                offsetCenter: [0, '-40%'],       // x, y，单位px
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    fontWeight: 'bolder'
                                }
                            },
                            detail : {
                                formatter:'{value}%',
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontWeight: 'bolder'
                                }
                            },
                            data:[{value: 75, name: '合同'}]
                        }
                    ]
                });
            },
            drawgssfdYbpChart(){
                this.gssfdchartybp = echarts.init(document.getElementById('gssfdchartybp'));
                this.gssfdchartybp.setOption({
                    tooltip : {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    series : [
                        {
                            type:'gauge',
                            splitNumber: 10,       // 分割段数，默认为5
                            axisLine: {            // 坐标轴线
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                    width: 8
                                }
                            },
                            axisTick: {            // 坐标轴小标记
                                splitNumber: 10,   // 每份split细分多少段
                                length :12,        // 属性length控制线长
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: 'auto'
                                }
                            },
                            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto'
                                }
                            },
                            pointer : {
                                width : 5
                            },
                            title : {
                                show : true,
                                offsetCenter: [0, '-40%'],       // x, y，单位px
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    fontWeight: 'bolder'
                                }
                            },
                            detail : {
                                formatter:'{value}%',
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontWeight: 'bolder'
                                }
                            },
                            data:[{value: 50, name: '收费单'}]
                        }
                    ]
                });
            },
            drawgsdzYbpChart(){
                this.gsdzchartybp = echarts.init(document.getElementById('gsdzchartybp'));
                this.gsdzchartybp.setOption({
                    tooltip : {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    series : [
                        {
                            type:'gauge',
                            splitNumber: 10,       // 分割段数，默认为5
                            axisLine: {            // 坐标轴线
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                    width: 8
                                }
                            },
                            axisTick: {            // 坐标轴小标记
                                splitNumber: 10,   // 每份split细分多少段
                                length :12,        // 属性length控制线长
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: 'auto'
                                }
                            },
                            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto'
                                }
                            },
                            pointer : {
                                width : 5
                            },
                            title : {
                                show : true,
                                offsetCenter: [0, '-40%'],       // x, y，单位px
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    fontWeight: 'bolder'
                                }
                            },
                            detail : {
                                formatter:'{value}%',
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontWeight: 'bolder'
                                }
                            },
                            data:[{value: 25, name: '到账'}]
                        }
                    ]
                });
            },
            drawjmhtYbpChart(){
                this.jmhtchartybp = echarts.init(document.getElementById('jmhtchartybp'));
                this.jmhtchartybp.setOption({
                    tooltip : {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    series : [
                        {
                            type:'gauge',
                            splitNumber: 10,       // 分割段数，默认为5
                            axisLine: {            // 坐标轴线
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                    width: 8
                                }
                            },
                            axisTick: {            // 坐标轴小标记
                                splitNumber: 10,   // 每份split细分多少段
                                length :12,        // 属性length控制线长
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: 'auto'
                                }
                            },
                            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto'
                                }
                            },
                            pointer : {
                                width : 5
                            },
                            title : {
                                show : true,
                                offsetCenter: [0, '-40%'],       // x, y，单位px
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    fontWeight: 'bolder'
                                }
                            },
                            detail : {
                                formatter:'{value}%',
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontWeight: 'bolder'
                                }
                            },
                            data:[{value: 75, name: '合同'}]
                        }
                    ]
                });
            },
            drawjmsfdYbpChart(){
                this.jmsfdchartybp = echarts.init(document.getElementById('jmsfdchartybp'));
                this.jmsfdchartybp.setOption({
                    tooltip : {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    series : [
                        {
                            type:'gauge',
                            splitNumber: 10,       // 分割段数，默认为5
                            axisLine: {            // 坐标轴线
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                    width: 8
                                }
                            },
                            axisTick: {            // 坐标轴小标记
                                splitNumber: 10,   // 每份split细分多少段
                                length :12,        // 属性length控制线长
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: 'auto'
                                }
                            },
                            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto'
                                }
                            },
                            pointer : {
                                width : 5
                            },
                            title : {
                                show : true,
                                offsetCenter: [0, '-40%'],       // x, y，单位px
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    fontWeight: 'bolder'
                                }
                            },
                            detail : {
                                formatter:'{value}%',
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontWeight: 'bolder'
                                }
                            },
                            data:[{value: 50, name: '收费单'}]
                        }
                    ]
                });
            },
            drawjmdzYbpChart(){
                this.jmdzchartybp = echarts.init(document.getElementById('jmdzchartybp'));
                this.jmdzchartybp.setOption({
                    tooltip : {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    series : [
                        {
                            type:'gauge',
                            splitNumber: 10,       // 分割段数，默认为5
                            axisLine: {            // 坐标轴线
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                    width: 8
                                }
                            },
                            axisTick: {            // 坐标轴小标记
                                splitNumber: 10,   // 每份split细分多少段
                                length :12,        // 属性length控制线长
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: 'auto'
                                }
                            },
                            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto'
                                }
                            },
                            pointer : {
                                width : 5
                            },
                            title : {
                                show : true,
                                offsetCenter: [0, '-40%'],       // x, y，单位px
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    fontWeight: 'bolder'
                                }
                            },
                            detail : {
                                formatter:'{value}%',
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontWeight: 'bolder'
                                }
                            },
                            data:[{value: 25, name: '到账'}]
                        }
                    ]
                });
            },
            drawhghtYbpChart(){
                this.hghtchartybp = echarts.init(document.getElementById('hghtchartybp'));
                this.hghtchartybp.setOption({
                    tooltip : {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    series : [
                        {
                            type:'gauge',
                            splitNumber: 10,       // 分割段数，默认为5
                            axisLine: {            // 坐标轴线
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                    width: 8
                                }
                            },
                            axisTick: {            // 坐标轴小标记
                                splitNumber: 10,   // 每份split细分多少段
                                length :12,        // 属性length控制线长
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: 'auto'
                                }
                            },
                            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto'
                                }
                            },
                            pointer : {
                                width : 5
                            },
                            title : {
                                show : true,
                                offsetCenter: [0, '-40%'],       // x, y，单位px
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    fontWeight: 'bolder'
                                }
                            },
                            detail : {
                                formatter:'{value}%',
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontWeight: 'bolder'
                                }
                            },
                            data:[{value: 75, name: '合同'}]
                        }
                    ]
                });
            },
            drawhgsfdYbpChart(){
                this.hgsfdchartybp = echarts.init(document.getElementById('hgsfdchartybp'));
                this.hgsfdchartybp.setOption({
                    tooltip : {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    series : [
                        {
                            type:'gauge',
                            splitNumber: 10,       // 分割段数，默认为5
                            axisLine: {            // 坐标轴线
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                    width: 8
                                }
                            },
                            axisTick: {            // 坐标轴小标记
                                splitNumber: 10,   // 每份split细分多少段
                                length :12,        // 属性length控制线长
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: 'auto'
                                }
                            },
                            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto'
                                }
                            },
                            pointer : {
                                width : 5
                            },
                            title : {
                                show : true,
                                offsetCenter: [0, '-40%'],       // x, y，单位px
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    fontWeight: 'bolder'
                                }
                            },
                            detail : {
                                formatter:'{value}%',
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontWeight: 'bolder'
                                }
                            },
                            data:[{value: 50, name: '收费单'}]
                        }
                    ]
                });
            },
            drawhgdzYbpChart(){
                this.hgdzchartybp = echarts.init(document.getElementById('hgdzchartybp'));
                this.hgdzchartybp.setOption({
                    tooltip : {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    series : [
                        {
                            type:'gauge',
                            splitNumber: 10,       // 分割段数，默认为5
                            axisLine: {            // 坐标轴线
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                    width: 8
                                }
                            },
                            axisTick: {            // 坐标轴小标记
                                splitNumber: 10,   // 每份split细分多少段
                                length :12,        // 属性length控制线长
                                lineStyle: {       // 属性lineStyle控制线条样式
                                    color: 'auto'
                                }
                            },
                            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto'
                                }
                            },
                            pointer : {
                                width : 5
                            },
                            title : {
                                show : true,
                                offsetCenter: [0, '-40%'],       // x, y，单位px
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    fontWeight: 'bolder'
                                }
                            },
                            detail : {
                                formatter:'{value}%',
                                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontWeight: 'bolder'
                                }
                            },
                            data:[{value: 25, name: '到账'}]
                        }
                    ]
                });
            },
            drawysPieChart(){
                this.yschartPie = echarts.init(document.getElementById('yschartPie'));
                this.yschartPie.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['预算', '工商表', '管理', '吊管', '设计']
                    },
                    series: [
                        {
                            name: '占比',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: [
                                { value: 335, name: '预算' },
                                { value: 310, name: '工商表' },
                                { value: 234, name: '管理' },
                                { value: 135, name: '吊管' },
                                { value: 1548, name: '设计' }
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            },
            drawdzPieChart(){
                this.dzchartPie = echarts.init(document.getElementById('dzchartPie'));
                this.dzchartPie.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['栏目', '工商表', '监理', '吊管', '设计']
                    },
                    series: [
                        {
                            name: '占比',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: [
                                { value: 335, name: '栏目' },
                                { value: 310, name: '工商表' },
                                { value: 234, name: '监理' },
                                { value: 135, name: '吊管' },
                                { value: 1548, name: '设计' }
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            },
            drawCharts() {
                this.drawLineChart();
                this.drawgsBarChart();
                this.drawjmBarChart();
                this.drawhgBarChart();
                this.drawgshtYbpChart();
                this.drawgssfdYbpChart();
                this.drawgsdzYbpChart();
                this.drawjmhtYbpChart();
                this.drawjmsfdYbpChart();
                this.drawjmdzYbpChart();
                this.drawhghtYbpChart();
                this.drawhgsfdYbpChart();
                this.drawhgdzYbpChart();
                this.drawysPieChart();
                this.drawdzPieChart();
            },
            //查询
            query_vue(){

            },

        },
    }
</script>

<style scoped>

</style>