<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template xmlns:o="http://www.w3.org/1999/xhtml">
	<section>
		<el-row>
			<el-col :span="24">
				<el-row style="margin-top: 15px;">
					<el-col :span="24">
						<el-card class="box-card">
							<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
								<span>供水工程竣工验收合格证明</span>
							</div>
							<div class="text item" style="width: 40%;margin: auto">
								<el-form :model="filters" label-width="120px" ref="xmxxForm" :inline="false">
									<el-form-item label="项目名称">
										<el-input v-model="filters.xmmc" style="width: 300px" :readonly="true"></el-input>
									</el-form-item>
									<el-form-item label="验收范围">
										<el-input v-model="filters.ysfw" style="width: 300px"></el-input>
									</el-form-item>
									<el-form-item label="建设单位">
										<el-input v-model="filters.jsdw" style="width: 300px" :readonly="true"></el-input>
									</el-form-item>
									<el-form-item label="项目类型">
										<el-radio-group v-model="filters.xmlx" @change="getFormBylx(filters.xmlx)">
											<el-radio label="0">住宅</el-radio>
											<el-radio label="1">非住宅</el-radio>
										</el-radio-group>
									</el-form-item>
									<el-form-item label="验收内容">
										<el-input v-model="filters.ysnr" style="width: 300px"></el-input>
									</el-form-item>
									<el-form-item label="初步意见">
										<el-col :span="100" style="width: 300px">
											<el-input type="textarea" :rows="5" autosize v-model="filters.cbyj"></el-input>
										</el-col>
									</el-form-item>
									<el-form-item label="验收结论">
										<el-radio-group v-model="filters.ysjl">
											<el-radio label="1">合格</el-radio>
											<el-radio label="0">不合格</el-radio>
										</el-radio-group>
									</el-form-item>
								</el-form>
							</div>
						</el-card>
					</el-col>
				</el-row>
			</el-col>
			<el-col :span="24" style="text-align: center;margin-top: 0px;"></el-col>
		</el-row>
		<YSZLSC :zbid="YsHgRwZbid" :xmid="Ysfrom.id" :xmbh="Ysfrom.xmbh" :type="xs"></YSZLSC>
		<el-row>
			<el-col :span="24">
				<el-card class="box-card">
					<el-col :span="8">&nbsp;</el-col>
					<router-link :to="tabUrl">
						<el-button size="mini" type="info" @click="toXckcrwcj()"><i class="iconfont icon-quxiao"></i>取消</el-button>
					</router-link>
					<el-button size="mini" type="primary" @click="bc('0')" style="margin-left: 10px"><i class="iconfont icon-save"></i>保存</el-button>
					<el-button size="mini" type="success" @click="dy()"><i class="iconfont icon-dayin_f"></i>打印</el-button>
					<el-button size="mini" type="primary" @click="goxmqj()" :loading="submitLoading"><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
					<!--<el-button size="mini" type="primary" @click="goxmzl()" :loading="submitLoading"><i class="iconfont icon-ziliaoquanjiing_"></i>项目资料</el-button>-->
				</el-card>
			</el-col>
		</el-row>
		<el-dialog title="" :visible.sync ="printFormVisible">
			<div class=WordSection1 style='layout-grid:15.6pt' id="printTest">
				<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
					<b style='mso-bidi-font-weight:normal'>
						<span style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>供水工程竣工验收合格证明
							<span lang=EN-US></span>
						</span>
					</b>
				</p>
				<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
					<b style='mso-bidi-font-weight:normal'>
						<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>

						</span>
					</b>
				</p>
				<div align=center>
					<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=0
						   style='width:432.1pt;border-collapse:collapse;border:none;mso-border-alt:solid windowtext .5pt;
 									mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt'>
						<tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;height:38.0pt'>
							<td width=113 style='width:84.75pt;border:solid windowtext 1.0pt;mso-border-alt:
												solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<b style='mso-bidi-font-weight:normal'><span style='font-size:12.0pt;font-family:
												黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>项目名称
									</span>
									</b>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;
  												font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
										</span>
									</b>
								</p>
							</td>
							<td width=463 style='width:347.35pt;border:solid windowtext 1.0pt;border-left:
												  none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
												  padding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<span style='font-size:11.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
											  0pt;mso-bidi-font-weight:bold' id="xmmc">
									</span>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:
											  宋体;mso-font-kerning:0pt'>
										</span>
									</b>
								</p>
							</td>
						</tr>
						<tr style='mso-yfti-irow:1;height:38.0pt'>
							<td width=113 style='width:84.75pt;border:solid windowtext 1.0pt;border-top:
							none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
							padding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<b style='mso-bidi-font-weight:normal'>
										<span style='font-size:12.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
											验收范围
										</span>
									</b>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;
										font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
										</span>
									</b>
								</p>
							</td>
							<td width=463 style='width:347.35pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<span lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;color:black;mso-font-kerning:0pt' id="ysfw">
									</span>
								</p>
							</td>
						</tr>
						<tr style='mso-yfti-irow:2;height:38.0pt'>
							<td width=113 style='width:84.75pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<b style='mso-bidi-font-weight:normal'>
										<span style='font-size:12.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
											建设单位
										</span>
									</b>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
										</span>
									</b>
								</p>
							</td>
							<td width=463 style='width:347.35pt;border-top:none;border-left:none;
							border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
							mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
							mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<span style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt' id="jsdw">
									</span>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
										</span>
									</b>
								</p>
							</td>
						</tr>

						<tr style='mso-yfti-irow:3;height:38.0pt' id="xmlx">
						</tr>
						<tr style='mso-yfti-irow:4;height:87.65pt'>
							<td width=113 style='width:84.75pt;border:solid windowtext 1.0pt;border-top:
										  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
										  padding:0cm 5.4pt 0cm 5.4pt;height:87.65pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<b style='mso-bidi-font-weight:normal'>
										<span style='font-size:12.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>验
											<span lang=EN-US>
												<br>
										  	</span>收
											<span lang=EN-US>
												<br>
										  	</span>内
											<span lang=EN-US>
												<br>
										  	</span>容
											<span lang=EN-US>
												<br>
										  	</span>
										</span>
									</b>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
											<o:p></o:p>
										</span>
									</b>
								</p>
							</td>
							<td width=463 style='width:347.35pt;border-top:none;border-left:none;
										  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
										  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
										  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:87.65pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<span style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt' id="ysnr"></span>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
											<o:p></o:p>
										</span>
									</b>
								</p>
							</td>
						</tr>
						<tr style='mso-yfti-irow:5;height:120.1pt'>
							<td width=113 style='width:84.75pt;border:solid windowtext 1.0pt;border-top:
										  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
										  padding:0cm 5.4pt 0cm 5.4pt;height:87.65pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<b style='mso-bidi-font-weight:normal'>
										<span style='font-size:12.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>初
											<span lang=EN-US>
												<br>
										  	</span>步
											<span lang=EN-US>
												<br>
										  	</span>意
											<span lang=EN-US>
												<br>
										  	</span>见
											<span lang=EN-US>
												<br>
										  	</span>
										</span>
									</b>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
											<o:p></o:p>
										</span>
									</b>
								</p>
							</td>
							<td width=463 style='width:347.35pt;border-top:none;border-left:none;
										  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
										  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
										  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:120.1pt'>
								<p class=MsoNormal align=center style='text-align:center;text-indent:24.0pt;mso-char-indent-count:2.0;mso-pagination:widow-orphan'>
									<span style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt' id="cbyj"></span>
									<b style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:20.0pt;
																font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'><o:p></o:p>
									</span>
									</b>
								</p>
							</td>
						</tr>
						<tr style='mso-yfti-irow:6;mso-yfti-lastrow:yes;height:112.55pt'>
							<td width=113 style='width:84.75pt;border:solid windowtext 1.0pt;border-top:
										  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
										  padding:0cm 5.4pt 0cm 5.4pt;height:87.65pt'>
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<b style='mso-bidi-font-weight:normal'>
										<span style='font-size:12.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>验
											<span lang=EN-US>
												<br>
										  	</span>收
											<span lang=EN-US>
												<br>
										  	</span>结
											<span lang=EN-US>
												<br>
										  	</span>论
											<span lang=EN-US>
												<br>
										  	</span>
										</span>
									</b>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
											<o:p></o:p>
										</span>
									</b>
								</p>
							</td>
							<td width=463 style='width:347.35pt;border-top:none;border-left:none;
											  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
											  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
											  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:112.55pt' >
								<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
									<span id="ysjl">
									</span>
									<span lang=EN-US style='font-size:20.0pt;font-family:
  														宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
										<br>
									</span>
									<span lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
										<br>
										<br>
										<span style='mso-spacerun:yes'>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
										</span>
										<span id="sj"></span>
										（盖章）
									</span>
									<b style='mso-bidi-font-weight:normal'>
										<span lang=EN-US style='font-size:20.0pt;
										font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
											<o:p></o:p>
										</span>
									</b>
								</p>
							</td>
						</tr>
					</table>

				</div>

				<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
						style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
0pt'>注：此表只作为规划局核发建设工程规划许可证（正本）时查验。<span lang=EN-US><o:p></o:p></span></span></p>

				<p class=MsoNormal align=center style='margin-top:0cm;margin-right:67.95pt;
margin-bottom:0cm;margin-left:210.0pt;margin-bottom:.0001pt;mso-para-margin-top:
0cm;mso-para-margin-right:6.47gd;mso-para-margin-bottom:0cm;mso-para-margin-left:
20.0gd;mso-para-margin-bottom:.0001pt;text-align:center;mso-pagination:widow-orphan'><span
						style='mso-bidi-font-size:10.5pt;font-family:黑体;mso-bidi-font-family:宋体;
mso-font-kerning:0pt'>用户签章：<span lang=EN-US><o:p></o:p></span></span></p>

			</div>
			<div align="center">
				<el-button size="mini" type="success" v-print="'#printTest'" style="margin-bottom: 10px"><i class="iconfont icon-dayin_f"></i>打印</el-button>
			</div>
		</el-dialog>
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
<style scoped>
	.border-table {
		border-collapse: collapse;
		border: none;
		width: 100%;
	}
	.border-table td {
		border: solid #000 1px;
	}
</style>
<script>
	import YSZLSC from '../../components/gcpd/YhqrPzUpload.vue';
	import YSHGZM from '../../components/gcpd/yshgzm.vue';

	import XMJBXX from '@/views/template/xmjbxx.vue';
	import util from '../../common/js/util';

	import { bc ,getSfbc,startDelegate} from '../../api/Gcpd';
	export default {
		data() {
			return {
				tabUrl:'',
				printFormVisible:false,//打印界面是否显示
				sx:'primary',
				textarea:'',//备注
				Ysfrom: this.$route.query.tow1, //上个页面传来的项目信息
				xs: '0', //上传组件上传按钮是否显示
				filters: {
					xmmc: '',
					ysfw:'',
					jsdw:'',
					xmlx:'',
					ysnr:'',
					cbyj:'',
					ysjl:'',//验收结论
				},
				kcsj: '', //预约勘查时间
				tableData:[],
				check_value: {
					sjy_value: true,
					sgdw_value: true,
					ysk_value: true,
					sjydc_value: true,
					sgdwdc_value: true,
					yskdc_value: true
				},
				zzysnr:'供水系统',
				zzcbyj:'供水工程验收基本合格，建设单位应在取得“建设工程规划许可证（正本）”后，立即到供水集团办理供水设施（二次供水泵房）移交，移交后供水集团正式接收管理该项目供水设施。',
				fzzysnr:'该项目属（总表供水）',
				fzzcbyj:'已并网通水，水表用户端不属于供水集团验收、维护、范围。',
				YsHgRwZbid:'',//验收合格任务主表id
			};
		},
		watch: {
			// 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
			$route: 'getParams'
		},
		methods: {
			//跳转至项目全景
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.Ysfrom.id  }})

			},
			//跳转至项目资料
			goxmzl(){
				this.$router.push({path:'/lookZl', query:{ xmid:this.Ysfrom.id }})
			},
			getFormBylx(value){
				if (value == "0"){
					this.filters.ysnr=this.zzysnr;
					this.filters.cbyj=this.zzcbyj;
				} else if (value == "1") {
					this.filters.ysnr=this.fzzysnr;
					this.filters.cbyj=this.fzzcbyj;
				}
			},
			//加载项目基本信息
			getParams() {
				//this.Ysfrom = this.$route.query.tow1;
				this.filters.xmmc = this.Ysfrom.xmmc;
				this.filters.jsdw = this.Ysfrom.dwmc;

				let params ={
					xmid:this.Ysfrom.id
				}
				getSfbc(params).then(res =>{
					let test=res.data.yshgRwb;
					this.YsHgRwZbid = test.id;
					if (test != "0"){
						this.filters.xmlx= test.xmlx;
						this.filters.ysnr= test.ysnr;
						this.filters.cbyj= test.cbyj;
						this.filters.ysfw = test.ysfw;
						this.filters.ysjl = test.ysjl;
					} else{
						this.filters.ysjl='1';
						this.filters.xmlx='0';
						this.filters.ysnr=this.zzysnr;
						this.filters.cbyj=this.zzcbyj;

					}
				});



				},
			//跳转页面
			toXckcrwcj() {
				this.tabUrl = '/sqgchgpd';
				this.$routerTab.close();
				this.$routerTab.refresh('/sqgchgpd');
			},
			//element表格渲染方法
			objectSpanMethod({ row, column, rowIndex, columnIndex }) {
				if (columnIndex === 0) {
					if (rowIndex % 2 === 0) {
						return {
							rowspan: 2,
							colspan: 1
						};
					} else {
						return {
							rowspan: 0,
							colspan: 0
						};
					}
				}
			},
			bc(type){
				if (this.filters.xmmc == '' || typeof(this.filters.xmmc)=="undefined"){
					this.$message({
						type: 'warning',
						message: '项目名称不能为空！'
					});
				}else if (this.filters.ysfw == '' || typeof(this.filters.ysfw)=="undefined"){
					this.$message({
						type: 'warning',
						message: '验收范围不能为空！'
					});
				}else if (this.filters.jsdw == '' || typeof(this.filters.jsdw)=="undefined"){
					this.$message({
						type: 'warning',
						message: '建设单位不能为空！'
					});
				}else if (this.filters.ysnr == '' || typeof(this.filters.ysnr)=="undefined"){
					this.$message({
						type: 'warning',
						message: '验收内容不能为空！'
					});
				}else {
					let params ={
						xmid : this.Ysfrom.id,//项目id
						xmmc:this.filters.xmmc,
						ysfw:this.filters.ysfw,
						jsdw:this.filters.jsdw,
						ysnr:this.filters.ysnr,
						cbyj:this.filters.cbyj,
						xmlx:this.filters.xmlx,
						ysjl:this.filters.ysjl
					}
					bc(params).then(res =>{
						if (type != "1"){
							this.$message({
								type: 'success',
								message: '保存成功!',
							});

							this.$routerTab.close();
							this.$routerTab.refresh('/sqgchgpd');
						}
					});


				}

			},
			dy(){
				if (this.filters.xmmc == '' || typeof(this.filters.xmmc)=="undefined"){
					this.filters.xmmc = '';
					this.openDy();
				}else if (this.filters.ysfw == '' || typeof(this.filters.ysfw)=="undefined"){
					this.filters.ysfw = '';
					if (this.filters.jsdw == '' || typeof(this.filters.jsdw)=="undefined"){
						this.filters.jsdw = '';
					}
					this.openDy();
				}else if (this.filters.jsdw == '' || typeof(this.filters.jsdw)=="undefined" || this.filters.jsdw == "undefined" || typeof(this.filters.jsdw)=="null"){
					this.filters.jsdw = '';
					this.openDy();
				}else if (this.filters.ysnr == '' || typeof(this.filters.ysnr)=="undefined"){
					this.filters.ysnr = '';
					this.openDy();
				}else {
					this.openDy();
				}
			},
			//打开打印界面
			openDy(){
				this.bc("1");
				this.printFormVisible = true;
				this.$nextTick(()=>{
					document.getElementById("xmmc").innerText = this.filters.xmmc;
					document.getElementById("jsdw").innerText = this.filters.jsdw;
					document.getElementById("ysfw").innerText = this.filters.ysfw;
					document.getElementById("ysnr").innerText = this.filters.ysnr;
					document.getElementById("cbyj").innerText = this.filters.cbyj;
					document.getElementById("sj").innerText = this.publicUtil.getNowDay();

					if (this.filters.xmlx == "0"){//住宅
						document.getElementById("xmlx").innerHTML = "<td width=113 style='width:84.75pt;border:solid windowtext 1.0pt;border-top:\n" +
								" \t\t\t\t\t\t\t\t\t\t\tnone;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n" +
								"  \t\t\t\t\t\t\t\t\t\t\tpadding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>\n" +
								"\t\t\t\t\t\t\t\t<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>\n" +
								"\t\t\t\t\t\t\t\t\t<b style='mso-bidi-font-weight:normal'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span style='font-size:12.0pt;font-family:\n" +
								"  \t\t\t\t\t\t\t\t\t\t\t黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>项目类型\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</b>\n" +
								"\t\t\t\t\t\t\t\t\t<b style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:20.0pt;\n" +
								"  \t\t\t\t\t\t\t\t\t\t\tfont-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<o:p></o:p>\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</b>\n" +
								"\t\t\t\t\t\t\t\t</p>\n" +
								"\t\t\t\t\t\t\t</td>\n" +
								"\t\t\t\t\t\t\t<td width=463 style='width:347.35pt;border-top:none;border-left:none;\n" +
								"\t\t\t\t\t\t\t\t\t\t  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
								"\t\t\t\t\t\t\t\t\t\t  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
								"\t\t\t\t\t\t\t\t\t\t  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>\n" +
								"\t\t\t\t\t\t\t\t<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>\n" +
								"\t\t\t\t\t\t\t\t\t<span style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t住宅\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t<span lang=EN-US style='font-size:14.0pt;font-family:\"Wingdings 2\";\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  mso-ascii-font-family:等线;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  等线;mso-fareast-theme-font:minor-latin;mso-hansi-font-family:等线;mso-hansi-theme-font:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  minor-latin;mso-bidi-font-family:宋体;mso-font-kerning:0pt;mso-char-type:symbol;\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  mso-symbol-font-family:\"Wingdings 2\"'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span style='mso-char-type:symbol;mso-symbol-font-family:\"Wingdings 2\"'>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t☑\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t<span lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t<span style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t非住宅\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t<span style='font-size:12.0pt;mso-fareast-font-family:等线;\n" +
								"  \t\t\t\t\t\t\t\t\t\tmso-fareast-theme-font:minor-latin;mso-bidi-font-family:宋体;mso-font-kerning:\n" +
								"  \t\t\t\t\t\t\t\t\t\t0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t□\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t<b style='mso-bidi-font-weight:normal'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t<o:p></o:p>\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</b>\n" +
								"\t\t\t\t\t\t\t\t</p>\n" +
								"\t\t\t\t\t\t\t</td>"

					}else if (this.filters.xmlx == "1"){//非住宅
						document.getElementById("xmlx").innerHTML="<td width=113 style='width:84.75pt;border:solid windowtext 1.0pt;border-top:\n" +
								" \t\t\t\t\t\t\t\t\t\t\tnone;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n" +
								"  \t\t\t\t\t\t\t\t\t\t\tpadding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>\n" +
								"\t\t\t\t\t\t\t\t<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>\n" +
								"\t\t\t\t\t\t\t\t\t<b style='mso-bidi-font-weight:normal'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span style='font-size:12.0pt;font-family:\n" +
								"  \t\t\t\t\t\t\t\t\t\t\t黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>项目类型\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</b>\n" +
								"\t\t\t\t\t\t\t\t\t<b style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:20.0pt;\n" +
								"  \t\t\t\t\t\t\t\t\t\t\tfont-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<o:p></o:p>\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</b>\n" +
								"\t\t\t\t\t\t\t\t</p>\n" +
								"\t\t\t\t\t\t\t</td>\n" +
								"\t\t\t\t\t\t\t<td width=463 style='width:347.35pt;border-top:none;border-left:none;\n" +
								"\t\t\t\t\t\t\t\t\t\t  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
								"\t\t\t\t\t\t\t\t\t\t  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;\n" +
								"\t\t\t\t\t\t\t\t\t\t  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:38.0pt'>\n" +
								"\t\t\t\t\t\t\t\t<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>\n" +
								"\t\t\t\t\t\t\t\t\t<span style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t住宅\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t<span style='font-size:12.0pt;mso-fareast-font-family:等线;\n" +
								"  \t\t\t\t\t\t\t\t\t\tmso-fareast-theme-font:minor-latin;mso-bidi-font-family:宋体;mso-font-kerning:\n" +
								"  \t\t\t\t\t\t\t\t\t\t0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t□\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t<span lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t<span style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"非住宅" +
								"</span>" +
								"\t\t\t\t\t\t\t\t\t<span lang=EN-US style='font-size:14.0pt;font-family:\"Wingdings 2\";\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  mso-ascii-font-family:等线;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  等线;mso-fareast-theme-font:minor-latin;mso-hansi-font-family:等线;mso-hansi-theme-font:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  minor-latin;mso-bidi-font-family:宋体;mso-font-kerning:0pt;mso-char-type:symbol;\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  mso-symbol-font-family:\"Wingdings 2\"'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span style='mso-char-type:symbol;mso-symbol-font-family:\"Wingdings 2\"'>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t☑\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t<b style='mso-bidi-font-weight:normal'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t<o:p></o:p>\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t</b>\n" +
								"\t\t\t\t\t\t\t\t</p>\n" +
								"\t\t\t\t\t\t\t</td>";

					}

					if (this.filters.ysjl == "0"){//不合格
						document.getElementById("ysjl").innerHTML = "<span style='font-size:20.0pt;font-family:华文行楷;mso-hansi-font-family:宋体;mso-bidi-font-family:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t宋体;mso-font-kerning:0pt'>□合 格\n" +
								"\t\t\t\t\t\t\t\t\t\t\t<span lang=EN-US>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t<span lang=EN-US style='font-size:22.0pt;font-family:\"Wingdings 2\";mso-ascii-font-family:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  华文行楷;mso-fareast-font-family:华文行楷;mso-hansi-font-family:宋体;mso-bidi-font-family:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  宋体;mso-font-kerning:0pt;mso-char-type:symbol;mso-symbol-font-family:\"Wingdings 2\"'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span style='mso-char-type:symbol;mso-symbol-font-family:\"Wingdings 2\"'>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t☑\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>不合格\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>";
					}else if(this.filters.ysjl == "1"){//合格
						document.getElementById("ysjl").innerHTML = "<span lang=EN-US style='font-size:22.0pt;font-family:\"Wingdings 2\";mso-ascii-font-family:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  华文行楷;mso-fareast-font-family:华文行楷;mso-hansi-font-family:宋体;mso-bidi-font-family:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t\t  宋体;mso-font-kerning:0pt;mso-char-type:symbol;mso-symbol-font-family:\"Wingdings 2\"'>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span style='mso-char-type:symbol;mso-symbol-font-family:\"Wingdings 2\"'>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t☑\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t\t<span style='font-size:20.0pt;font-family:华文行楷;mso-hansi-font-family:宋体;mso-bidi-font-family:\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t宋体;mso-font-kerning:0pt'>合 格\n" +
								"\t\t\t\t\t\t\t\t\t\t\t<span lang=EN-US>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
								"\t\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
								"\t\t\t\t\t\t\t\t\t\t\t</span>□不合格\n" +
								"\t\t\t\t\t\t\t\t\t\t</span>";
					}
				});


			},

		},
		//页面启动调用的方法
		mounted() {
			this.getParams();
		},
		components: {
			//将别名demo 变成 组件 Demo
			XMJBXX: XMJBXX,
			YSZLSC:YSZLSC,
		}
	};
</script>

<style scoped></style>
