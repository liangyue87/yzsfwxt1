<template>
	<el-container>
		<el-main class="main">
			<el-row :gutter="15">
				<!--左侧-->
				<el-col :span="18" :style="style_up">
					<el-row :gutter="15" :style="style_up">
						<el-col :span="8" :style="style_up">
							<div :style="style_up" class="backcol">
								<div id="myChart1" style="width: 100%;height: 140px"></div>
							</div>
						</el-col>
						<el-col :span="8">
							<div :style="style_up" class="backcol">
								<div id="myChart2" style="width: 100%;height: 140px"></div>
							</div>
						</el-col>
						<el-col :span="8">
							<div :style="style_up" class="backcol">
								<div id="myChart3" style="width: 100%;height: 140px"></div>
							</div>
						</el-col>
					</el-row>
					<el-row :style="style_down" style="margin-top: 10px;">
						<el-col :span="24" :style="style_down">
							<div :style="style_down" class="backcol">
								<div class="tit">
									<div class="tit_lf"><i class="iconfont icon-renwu"></i>我发起的任务</div>
									<!--<div class="tit_rt" @click="toOriginate">更多</div>-->
								</div>
								<div>
									<el-col :span="24" class="toolbar">
										<!--列表-->
										<el-table :data="wfqdRw" highlight-current-row v-loading="listLoading"
												  style="width: 96%;margin: auto" :header-cell-style="{background:'#f5f7fa',fontSize:'13px'}">
											<el-table-column type="index" label="状态" width="60" align="left">
												<template slot-scope="scope">
													<i v-if="scope.row.QSZT == '已签收' && scope.row.SFHUANGD == '0' && scope.row.SFHONGD== '0'" class="el-icon-s-opportunity lignt_green"></i>
													<i v-else-if="scope.row.QSZT == '已签收' && scope.row.SFHUANGD == '1' && scope.row.SFHONGD== '0'" class="el-icon-s-opportunity lignt_yellow"></i>
													<i v-else-if="scope.row.QSZT == '已签收' && scope.row.SFHONGD== '1'" class="el-icon-s-opportunity lignt_red"></i>
													<i v-else-if="scope.row.QSZT == '未签收' && scope.row.QSSFCS== '1'" class="el-icon-s-opportunity lignt_red"></i>
													<i v-else-if="scope.row.QSZT == '未签收' && scope.row.QSSFCS== '0'" class="el-icon-s-opportunity lignt_green"></i>
												</template>
											</el-table-column>
											<el-table-column property="RWBH" label="项目信息" width="220" header-align="left"  align="left">
												<template slot-scope="scope">
													<span class="table_text">{{scope.row.XMMC}}</span>
													<br>
													<span class="table_text">{{scope.row.XMBH}}</span>
													<!--<br>
													<span class="table_text">{{scope.row.XMDZ}}</span>-->
												</template>
											</el-table-column>
											<el-table-column property="ACTIVITYNAME" label="环节名称" width="120" header-align="left"  align="left">
												<template slot-scope="scope">
													<span class="table_text">{{scope.row.ACTIVITYNAME}}</span>
												</template>
											</el-table-column>
											<el-table-column property="TIME" label="时间" width="200" header-align="left" align="left">
												<template slot-scope="scope">
													<span class="table_text">发送时间：{{scope.row.FSSJ}}</span>
													<br>
													<span v-if="scope.row.CHECK_TIME_STR == null" class="table_text">签收时间：未签收</span>
													<span v-else class="table_text">签收时间：{{scope.row.CHECK_TIME_STR}}</span>
												</template>
											</el-table-column>
											<el-table-column property="TIME" label="办理人" width="190" header-align="left" align="left">
												<template slot-scope="scope">
													<span class="table_text">办理人：{{scope.row.JSR}}</span>
													<br>
													<span class="table_text">联系方式：{{scope.row.JSRSJH}}</span>
												</template>
											</el-table-column>
											<el-table-column label="操作" min-width="60" align="left">
												<template slot-scope="scope">
													<label title="查看" @click="toShow(scope.row)">
														<i class="iconfont icon-yulan_f" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
												</template>
											</el-table-column>
										</el-table>
									</el-col>
								</div>
							</div>
						</el-col>
					</el-row>
				</el-col>
				<!--右侧-->
				<el-col :span="6">
					<div :style="style_right" class="backcol" >
						<el-row style="text-align: center">
                            <el-col :span="24">
								<el-col :span="24">
									<div class="tit">
										<div class="tit_lf"><i class="iconfont icon-tongji"></i>红黄绿灯统计</div>
										<!--<div class="tit_rt">更多</div>-->
									</div>
								</el-col>
                            </el-col>
                            <el-col :span="24" style="text-align: center">
								<div id="myChart4" style="width: 95%;height: 120px;margin: 0px auto;"></div>
                            </el-col>
						</el-row>

						<el-row style="text-align: center;margin-top: 10px;">
						<el-col :span="24">
							<el-col :span="24">
								<div class="tit">
									<div class="tit_lf"><i class="iconfont icon-iconfontlaba1"></i>消息提醒</div>
									<!--<div class="tit_rt">更多</div>-->
								</div>
							</el-col>
						</el-col>
						<el-col :span="24" style="text-align: center">
							<div style="height: 185px">
								<el-row v-for="item,index in messageList" :key="index" style="margin-bottom: 0px;margin-top: 8px">
									<el-col :span="2"><i class="iconfont icon-qizhi"></i></el-col>
									<el-col :span="22" style="text-align: left">
										<span class="spanText" v-if="item.isGreen">
											【项目办结】项目{{item.XMBH}}{{item.XMMC}}发起的{{item.ACTIVITYNAME}}任务已办结，办结人“{{item.RWBLRNAME}}”，办结时限：绿灯办结
										</span>
										<span class="spanText" v-if="item.isYellow">
											【项目办结】项目{{item.XMBH}}{{item.XMMC}}发起的{{item.ACTIVITYNAME}}任务已办结，办结人“{{item.RWBLRNAME}}”，办结时限：黄灯办结
										</span>
										<span class="spanText" v-if="item.isRed">
											【项目办结】项目{{item.XMBH}}{{item.XMMC}}发起的{{item.ACTIVITYNAME}}任务已办结，办结人“{{item.RWBLRNAME}}”，办结时限：红灯办结
										</span><br>
										<span class="spanTime">
											{{item.JSSJ}}
										</span>
									</el-col>
								</el-row>
							</div>
						</el-col>
					</el-row>

						<!--<el-row style="text-align: center">-->
							<!--<el-col :span="24">-->
								<!--<div class="tit">-->
									<!--<div class="tit_lf"><i class="iconfont icon-gaojing"></i>收费告警</div>-->
									<!--&lt;!&ndash;<div class="tit_rt">更多</div>&ndash;&gt;-->
								<!--</div>-->
							<!--</el-col>-->
							<!--<el-col v-for="i in 3" :key="i" :span="24" style="text-align: center">-->
								<!--<div class="div_bottom">-->
									<!--<div class="bottom_lf">-->
										<!--<span class="spanText">-->
											<!--收费通知单{{i}}-->
										<!--</span>-->
										<!--<br>-->
										<!--<span class="spanTime">-->
											<!--SFTZD20190518001{{i}}-->
										<!--</span>-->
									<!--</div>-->
									<!--<div class="bottom_rt">-->
										<!--2019-05-08-->
									<!--</div>-->
								<!--</div>-->
							<!--</el-col>-->
						<!--</el-row>-->
					</div>
				</el-col>
			</el-row>

		</el-main>
	</el-container>
</template>

<script>
	import util from '../../common/js/util'
	import { findOriginate,getData,getMessageList } from '../../api/work';

	export default {
		data() {
			return {
				messageList:[],
                listLoading : false,
				total: 5,//表格数据总数量
				page: 1,//当前页码
				size: 5,//每页容量
				wfqdRw: [],
				style_up:{
					height : '0px',
				},
				style_down:{
					height : '0px',
				},
				style_right:{
					height : '0px',
				},
				echarts1:{
					ylx : 0,
					wlx : 0
				},
				echarts2:{
					yhd : 0,
					whd : 0
				},
				echarts3:{
					ydz : 0,
					wdz : 0
				},
				echarts4:{
					green : 0,
					yellow : 0,
					red: 0
				},
			}
		},

		methods: {

			getMessageList_vue(){
				getMessageList().then((res) => {
					if(res.data!=null&&res.data!=undefined){
						this.messageList = res.data.rows;
					}
				});
			},
			toShow(row) {
				let c_activityid = row.C_ACTIVITYID.substr(0,1);
				let xmid = row.ID;
				this.$router.push({path: '/Jsplumb', query: {xmid : xmid,type : c_activityid}})
			},

			//获取我的发起任务
			findOriginate() {
				let JSONstr = sessionStorage.getItem("user");
				let JSON = eval("("+JSONstr+")");
				let params = Object.assign({ page: this.page, size: this .size, rwblr: JSON.id });
				this.listLoading = true;
				findOriginate(params).then((res) => {
					this.total = res.data.total;
					this.wfqdRw = res.data.list;
					this.listLoading = false;
				});
			},
			//跳转至我发起的任务
			toOriginate(){
				this.$router.push({
					path:'/Originate',
				})
			},
			getData(){
				let JSONstr = sessionStorage.getItem("user");
				let JSON = eval("("+JSONstr+")");
				let userid = JSON.id;
				let params = {userid:userid};
				getData(params).then((res) => {
					if(Object.keys(res.data).length == 0){
						this.drawLine();
					}else {
						this.echarts1.ylx = res.data['已立项'] == undefined ? 0: res.data['已立项'];
						this.echarts1.wlx = res.data['未立项'] == undefined ? 0: res.data['未立项'];
						this.echarts2.yhd = res.data['有活动'] == undefined ? 0: res.data['有活动'];
						this.echarts2.whd = res.data['无活动'] == undefined ? 0: res.data['无活动'];
						this.echarts3.ydz = res.data['已到账'] == undefined ? 0: res.data['已到账'];
						this.echarts3.wdz = res.data['未到账'] == undefined ? 0: res.data['未到账'];
						if( res.data.hhl == undefined ){
							this.echarts4.green = 0;
							this.echarts4.yellow = 0;
							this.echarts4.red = 0;
						}else {
							this.echarts4.green = res.data.hhl['LDDBSL'] == undefined ? 0 : res.data.hhl['LDDBSL'];
							this.echarts4.yellow = res.data.hhl['HUANGDDBSL'] == undefined ? 0 : res.data.hhl['HUANGDDBSL'];
							this.echarts4.red = res.data.hhl['HONGDDBSL'] == undefined ? 0 : res.data.hhl['HONGDDBSL'];
						}
						this.drawLine();
					}

				});
			},
			drawLine() {//echarts图标init
				// 基于准备好的dom，初始化echarts实例
				let data1 = [{value:this.echarts1.ylx, name:'已立项'},{value:this.echarts1.wlx, name:'未立项'}];
				let myChart1 = this.$echarts.init(document.getElementById('myChart1'))
				// 绘制图表
				myChart1.setOption({
					title : {
						text: '受理分派',
						x:'left',
						textStyle:{ //设置主标题风格
							color:'#43AEA8',//设置主标题字体颜色
							fontSize: 15
						}
					},
					tooltip: {
						show : true,
						trigger: 'item',
						backgroundColor: 'rgba(0,0,0,0.7)',//背景颜色（此时为默认色）
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						itemWidth: 20,　　　　//图例大小  我这里用的是圆
						itemGap: 8,　　　　　//图例之间的间隔
						type : 'plain',
						orient: 'vertical',
						x: 'right',
						y: 'bottom',
						data: data1,
						selectedMode: false,
						formatter: function (name) {
							let target;
							for(let i=0;i<data1.length;i++){
								if(data1[i].name===name){
									target=data1[i].value
								}
							}
							return name + ":" + target;
						}
					},
					color : ['#43AEA8','#E8E8E8'],
					series: [
						{
							name:'受理分派',
							type:'pie',
							radius: ['60%', '80%'],
							avoidLabelOverlap: false,
							label: {
								normal: {
									show: true,
									position: 'center',
									formatter: this.formatterEcharts1,
									textStyle:{
										fontSize:30,
										color:'#43AEA8'
									}
								}
							},
							data: data1
						}
					]
				});


				let myChart2 = this.$echarts.init(document.getElementById('myChart2'))
				let data2 = [{value:this.echarts2.yhd, name:'有活动'},{value:this.echarts2.whd, name:'无活动'}];
				// 绘制图表
				myChart2.setOption({
					title : {
						text: '在办项目',
						x:'left',
						textStyle:{ //设置主标题风格
							color:'#6C85BD',//设置主标题字体颜色
							fontSize: 15
						}
					},
					tooltip: {
						trigger: 'item',
					},
					legend: {
						itemWidth: 20,　　　　//图例大小  我这里用的是圆
						itemGap: 8,　　　　　//图例之间的间隔
						type : 'plain',
						orient: 'vertical',
						x: 'right',
						y: 'bottom',
						data: data2,
						selectedMode: false,
						formatter: function (name) {
							let target;
							for(let i=0;i<data2.length;i++){
								if(data2[i].name===name){
									target=data2[i].value
								}
							}
							return name + ":" + target;
						}
					},
					color : ['#6C85BD','#E8E8E8'],
					series: [
						{
							name:'在办项目',
							type:'pie',
							radius: ['60%', '80%'],
							avoidLabelOverlap: false,
							label: {
								normal: {
									show: true,
									position: 'center',
									formatter:  this.formatterEcharts2,
									textStyle:{
										fontSize:30,
										color:'#6C85BD'
									}
								}
							},
							data:data2
						}
					]
				});

				let myChart3 = this.$echarts.init(document.getElementById('myChart3'))
				let data3 = [{value:this.echarts3.ydz, name:'已到账'},{value:this.echarts3.wdz, name:'未到账'}];
				// 绘制图表
				myChart3.setOption({
					title : {
						text: '在收费用',
						x:'left',
						textStyle:{ //设置主标题风格
							color:'#60B1CC',//设置主标题字体颜色
							fontSize: 15
						}
					},
					tooltip: {
						trigger: 'item',
					},
					legend: {
						itemWidth: 20,　　　　//图例大小  我这里用的是圆
						itemGap: 8,　　　　　//图例之间的间隔
						type : 'plain',
						orient: 'vertical',
						x: 'right',
						y: 'bottom',
						data: data3,
						selectedMode: false,
						formatter: function (name) {
							let target;
							for(let i=0;i<data3.length;i++){
								if(data3[i].name===name){
									target=data3[i].value
								}
							}
							return name + ":" + target;
						}
					},
					color : ['#60B1CC','#E8E8E8'],
					series: [
						{
							name:'在收费用',
							type:'pie',
							radius: ['60%', '80%'],
							avoidLabelOverlap: false,
							label: {
								normal: {
									show: true,
									position: 'center',
									formatter:  this.formatterEcharts3,
									textStyle:{
										fontSize:30,
										color:'#60B1CC'
									}
								}
							},
							data:data3
						}
					]
				});

				let myChart4 = this.$echarts.init(document.getElementById('myChart4'))
				let data4 = [
						{value:this.echarts4.red, name:'红灯'},
						{value:this.echarts4.yellow, name:'黄灯'},
						{value:this.echarts4.green, name:'绿灯'}
					];
				myChart4.setOption({
					trigger: 'axis',
					axisPointer: {            // 坐标轴指示器，坐标轴触发有效
						type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					},
					grid: {
						top: '5%',
						left: '0%',
						right: '10%',
						bottom: '3%',
						containLabel: true
					},
					yAxis: {
						type: 'category',
						data: [ '红灯','黄灯','绿灯'],
						axisTick: {
							show: false,
						}
					},
					xAxis: {
						type: 'value'
					},
					series: [{
						barWidth: 18,
						data: data4,
						type: 'bar',
						itemStyle: {
							normal: {
								color: function(params) {
									var colorList = ['#f56c6c','#ff9f00','#67c23a'];
									return colorList[params.dataIndex]
								},
								//以下为是否显示，显示位置和显示格式的设置了
								label: {
									show: true,
									position: 'right',
									formatter: '{c}'

								}
							},
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.1)',
								formatter: '{c}'
							}

						},
					}]
				})
			},
			formatterEcharts1(){
				return parseInt(this.echarts1.ylx) + parseInt(this.echarts1.wlx)
			},
			formatterEcharts2(){
				return parseInt(this.echarts2.yhd) + parseInt(this.echarts2.whd)
			},
			formatterEcharts3(){
				return parseInt(this.echarts3.ydz) + parseInt(this.echarts3.wdz)
			},
			getHeight(){
				let height = window.innerHeight - 160;
				this.style_right.height= height+10 + 'px';
				this.style_up.height= height*0.25 + 'px';
				this.style_down.height= height*0.75 + 'px';
			},
			created(){//获取屏幕高度
				window.addEventListener('resize', this.getHeight);
				this.getHeight()
			}
		},
		created() {
			this.getMessageList_vue();
		},
		mounted() {
			window.setInterval(() => {
				setTimeout(this.getMessageList_vue(), 0)
			}, 60000)
			this.created();
			this.getData();
			this.findOriginate();
		}
	}

</script>

<style scoped>
	.backcol {
		background-color: #ffffff;
	}
	.table_text {
		text-align: left;
		font-size: 11px;
		margin: 0px;
	}
	.tit{
		width: 100%;
		height: 35px;
		line-height: 35px;
		background-color: #FAFAFA;
		box-sizing:border-box;
		padding: 0 12px;
	}
	.tit:after{
		content: "";
		display: inline-block;
		width: 100%;
		height: 0;
	}
	.tit_lf,.tit_rt{
		display:inline-block;
	}
	.tit_lf{
		font-size: 13px;
		font-weight: bold;
		color: #7E8392;
		float: left;
	}
	.tit_rt{
		font-size: 12px;
		color: #7E8392;
		float: right;
		cursor: pointer;
	}
	.spanText{
		margin-left: 2px;
		font-size: x-small;
		color: #7E8392;
	}
	.spanTime{
		font-size: x-small;
		color: #C8C8C8;
	}
	.div_bottom {
		width: 100%;
		height: 45px;
		border-bottom: 1px solid #f3f3f3;
		line-height: 45px;
		box-sizing:border-box;
		padding: 0 20px;
	}
	.bottom_lf,.bottom_rt{
		display:inline-block;
	}
	.bottom_lf{
		line-height: 22px;
		font-size: 5px;
		color: #999;
		float: left;
		text-align: left;
	}
	.bottom_rt{
		font-size: 12px;
		color: #999;
		float: right;
		cursor: pointer;
	}
	.icon-qizhi {
		color: #66b1ff;
		margin-left: 12px;
	}
	.lignt_green{
		color: #67c23a;
		font-size: large
	}
	.lignt_yellow{
		color: #ff9f00;
		font-size: large
	}
	.lignt_red{
		color: #f56c6c;
		font-size: large
	}
	.main {
		background-color: rgb(242,242,242);
		padding: 15px;
	}
</style>
