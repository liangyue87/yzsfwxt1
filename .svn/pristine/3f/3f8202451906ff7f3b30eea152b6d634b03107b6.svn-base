<template>
	<section>
		<el-row>
			<!--工具条-->
			<el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
				<el-form  :model="filters" >
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目编号：</span>
						</el-col>
						<el-col :span="5">
							<el-input clearable v-model="filters.xmbh" size="small " @keyup.enter.native="getspXmlb_vue"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>审批状态：</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.spzt" clearable style="width: 100%" size="small">
								<el-option
										v-for="item in spztOptions"
										:key="item.codevalue"
										:label="item.codename"
										:value="item.codevalue">
								</el-option>
							</el-select>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="2"style="text-align: right">
							<span>申请时间：</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker :editable="false" size="small " value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="filters.kssj" style="width: 100%;"></el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker :editable="false" size="small " value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="filters.jssj" style="width: 100%;"></el-date-picker>
						</el-col>
						<el-col :span="3" style="text-align: center">
							<el-button size="mini " type="primary" v-on:click="getspXmlb_vue"><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--列表-->
				<el-table :data="row" :stripe="true" highlight-current-row v-loading="listLoading"  style="width: 100%;" height="400px">
					<el-table-column type="index" label="序号"  align="center">
					</el-table-column>
					<el-table-column prop="SQRNAME" label="申请人" width="100" align="center">
					</el-table-column>
					<el-table-column prop="BASJ" label="申请时间" width="150" align="center">
					</el-table-column>
					<el-table-column prop="ACTIVITYNAME" label="活动名称" width="150" align="center">
					</el-table-column>
					<el-table-column prop="RWKSSJ" label="任务开始时间" width="150" align="center" >
					</el-table-column>
					<el-table-column prop="HDBJSX" label="红灯办结时限" width="150" align="center" >
					</el-table-column>
					<el-table-column prop="JHYQJZ" label="计划延期时间" width="150" align="center">
					</el-table-column>
					<el-table-column prop="SPRNAME" label="审批人" width="100" align="center">
					</el-table-column>
					<el-table-column prop="SPZT" label="审批状态" width="100" align="center" :formatter="ztformatter">
					</el-table-column>
					<el-table-column prop="SPSFTY" label="是否同意延期" width="100" align="center" :formatter="yqformatter">
					</el-table-column>
					<el-table-column prop="CSYY" label="延期原因" width="200" header-align="center">
					</el-table-column>
					<el-table-column prop="SPYJ" label="审批意见" width="200" header-align="center">
					</el-table-column>
					<el-table-column prop="XMBH" label="项目编号" width="200" align="center">
					</el-table-column>
					<el-table-column prop="XMMC" label="项目名称" width="150" header-align="center">
					</el-table-column>
					<el-table-column prop="XMFZRNAME" label="项目负责人" width="100" align="center">
					</el-table-column>
					<el-table-column label="操作" align="center" fixed="right">
						<template slot-scope="scope">
							<label title="审批"><i @click="passEdit_vue(scope.$index, scope.row)" class="iconfont icon-shenpi_" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
						</template>
					</el-table-column>
				</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--工具条-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
							   @current-change="handleCurrentChange" @size-change="handleSizeChange" :current-page.sync="this.page"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>
			</el-col>
			<el-dialog title="延期审批" :visible.sync = "yqspVisible" :close-on-click-modal="false">
				<el-form  :model="yqspForm" class="demo-form-inline" label-position="right" label-width="120px">
					<el-input v-model="yqspForm.id" style="display: none"></el-input>
					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">审批内容</div>
						</div>
						<el-row>
							<el-col :span="12">
								<el-form-item label="任务下达时间">
									<el-input v-model="yqspForm.rwjdsj" readonly></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item label="办理状态">
									<el-input v-model="yqspForm.bjzt" readonly></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="12">
								<el-form-item label="绿灯办结时限">
									<el-input v-model="yqspForm.ldbjsx" readonly></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item label="黄灯办结时限">
									<el-input v-model="yqspForm.hdbjsx" readonly></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row>
							<el-form-item label="延期原因">
								<el-input v-model="yqspForm.yqyy" readonly type="textarea" :rows="4"></el-input>
							</el-form-item>
						</el-row>
						<el-row>
							<el-form-item label="延期凭据">
								<el-table :data="delayfjData" :stripe="true" style="padding-bottom: 10px">
									<el-table-column property="FJMC" label="附件名称" align="center"></el-table-column>
									<el-table-column property="SCSJ" label="上传时间" align="center"></el-table-column>
									<el-table-column property="USERNAME" label="上传人" align="center"></el-table-column>
									<el-table-column property="address" label="操作" align="center">
										<template slot-scope="scope">
											<el-row>
												<el-col :span="24">
													<div v-if="scope.row.FJHZ!=null&&(scope.row.FJHZ.toLowerCase()=='png'||scope.row.FJHZ.toLowerCase()=='jpg')">
														<el-col :span="24">
															<label title="预览"><i class="iconfont icon-yulan" @click="downloadDelayFile_vue(scope.$index, scope.row,1)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
														</el-col>
													</div>
													<div v-else>
														<el-col :span="24">
															<label title="下载"><i @click="downloadDelayFile_vue(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
														</el-col>
													</div>
												</el-col>
											</el-row>
										</template>
									</el-table-column>
								</el-table>
							</el-form-item>
						</el-row>
					</el-card>
					<!--<el-card class="box-card">-->
						<!--<div slot="header" class="clearfix">-->
							<!--<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">调后时限</div>-->
						<!--</div>-->
						<!--<el-form-item label="绿灯办结时限">-->
							<!--<el-input v-model="yqspForm.ldbjsx" readonly></el-input>-->
						<!--</el-form-item>-->
						<!--<el-form-item label="黄灯办结时限">-->
							<!--<el-input v-model="yqspForm.jhyqsj" readonly></el-input>-->
						<!--</el-form-item>-->
					<!--</el-card>-->
					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">审批结果</div>
						</div>
						<el-form-item label="审批状态">
							<el-radio-group v-model="yqspForm.spsfty" style="width: 100%" :disabled="isreadonly">
								<el-radio :label="0">不予延期</el-radio>
								<el-radio :label="1">同意延期</el-radio>
							</el-radio-group>
						</el-form-item>
						<el-row>
							<el-col :span="12">
								<el-form-item label="计划延期至">
									<el-input v-model="yqspForm.jhyqsj" readonly style="width: 100%"></el-input>
								</el-form-item>
							</el-col>
						</el-row>

						<el-row>
							<el-form-item label="审批意见" v-if="this.yqspForm.spsfty!=1">
								<el-input v-model="yqspForm.spyj"  type="textarea" :rows="4"  :readonly="isreadonly"></el-input>
							</el-form-item>
						</el-row>

						<el-form-item label="审批人、时间">
							<el-input v-model="yqspForm.spr" readonly ></el-input>
						</el-form-item>
					</el-card>

				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button size="mini" type="info" @click="yqspVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
					<el-button v-if="!isreadonly" type="primary" size="mini" @click="passdelay_vue()"><i class="iconfont icon-queren"></i>确 定</el-button>
				</div>
			</el-dialog>
		</el-row>
	</section>
</template>

<script>
	import '../../assets/iconfont/iconfont.css'
	import util from '../../common/js/util'
	import {getspXmlb,passdelay,toDetial_delay,download_sjst,spfsdx} from '../../api/sjst';
	export default {
		data() {
			return {
				delayfjData:[],
				isreadonly:false,
				filters: {
					xmbh:'',
					spzt:'0',
					kssj:'',
					jssj:''
				},
				spztOptions:[{
					codevalue: '0',
					codename: '暂未审批'
				},{
					codevalue: '1',
					codename: '已审批'
				}],
				row: [],
				slide1:[],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				listLoading: false,
				yqspVisible:false,
				yqspForm:{
					id:"",
					rwjdsj:"",
					bjzt:"",
					ldbjsx:"",
					hdbjsx:"",
					yqyy:"",
					jhyqsj:"",
					spsfty:"",
					spyj:"",
					spr:"",
					spsj:""
				},
				xmid:""
			}
		},
		methods: {

			yqformatter(row, column) {
				switch (row.SPSFTY) {
					case 0:
					case "0":
						return "不同意";
						break;
					case 1:
					case "1":
						return "同意";
						break;
					default:
						return "";
						break;
				}
			},
			//格式化状态
			ztformatter(row, column) {
				switch (row.SPZT) {
					case 0:
					case "0":
						return "暂未审批";
						break;
					case 1:
					case "1":
						return "已审批";
						break;
					default:
						break;
				}
			},
			passEdit_vue(index,row){
				let date = new Date();
				this.yqspForm.rwjdsj = row.RWKSSJ;
				if(row.BJZT==0||row.BJZT=="0"){
					this.yqspForm.bjzt = "未办结"
				}else{
					this.yqspForm.bjzt = "已办结"
				}
				this.yqspForm.id = row.ID;
				this.yqspForm.ldbjsx = row.JSSX;
				this.yqspForm.hdbjsx = row.HDBJSX;
				this.yqspForm.yqyy = row.CSYY;
				if(row.SPSFTY==undefined){
					this.yqspForm.spsfty=""
				}else{
					this.yqspForm.spsfty = row.SPSFTY;
				}
				this.yqspForm.jhyqsj = row.JHYQJZ;
				if(row.SPYJ==undefined){
					this.yqspForm.spyj=""
				}else{
					this.yqspForm.spyj = row.SPYJ;
				}
				if(row.SPSJ==""||row.SPSJ==null||row.SPSJ==undefined){
					this.yqspForm.spsj = util.formatDate.format(date,'yyyy-MM-dd hh:mm:ss');
				}else{
					this.yqspForm.spsj = row.SPSJ
				}
				let params = {
					delayid: row.ID
				}
				debugger
				toDetial_delay(params).then((res) => {
					if (res.data != null && res.data.row != null) {
						this.delayfjData = res.data.row;
					}
				})
				this.yqspForm.spr = row.SPRNAME+'('+this.yqspForm.spsj+')';
				this.yqspVisible = true;
				this.isreadonly=row.isreadonly;
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getXmlb_vue(1);
			},
			handleSizeChange(val) {
                this.size = val;
                this.getXmlb_vue();
            },
			passdelay_vue(){
				if(this.yqspForm.spsfty===0&&this.yqspForm.spyj==""){
					this.$message.error('请填写审批意见');
					return;
				}
				if(this.yqspForm.spsfty===""){
					this.$message.error('请选择审批状态');
					return;
				}
				let spyj = "";
				if(this.yqspForm.spsfty==0){
					spyj = this.yqspForm.spyj;
				}
				let params={
					id : this.yqspForm.id,
					spsfty:this.yqspForm.spsfty,
					jhyqsj:this.yqspForm.jhyqsj,
					spyj:spyj
				}
				passdelay(params).then((res)=>{
					if(res.data){
						this.$message.success('提交成功');
						this.yqspVisible = false;
						this.getspXmlb_vue();
						let param={
							id:this.yqspForm.id
						}
						spfsdx(param).then((res)=>{

						})
					}else{
						this.$message.error('提交失败');
					}
				})
			},
			downloadDelayFile_vue(index,row,flag){
				let formData = new FormData()
				formData.append("path",row.FJLJ);
				download_sjst(formData).then((res)=>{
					if(flag==1){
						this.slide1=[];
						let reader = new FileReader()
						reader.onload = (e) => {
							let img = new Image();
							img.src =e.target.result;
							let item = {};
							item.msrc = item.src = e.target.result;
							item.h = img.height;
							item.w = img.width;
							this.slide1.push(item);
							this.$preview.openList(0, this.slide1);
						}
						reader.readAsDataURL(res.data)
					}else{
						const content = res.data
						const blob = new Blob([content])
						const fileName = row.fjmc
						if ('download' in document.createElement('a')) { // 非IE下载
							const elink = document.createElement('a')
							elink.download = fileName
							elink.style.display = 'none'
							elink.href = URL.createObjectURL(blob)
							document.body.appendChild(elink)
							elink.click()
							URL.revokeObjectURL(elink.href) // 释放URL 对象
							document.body.removeChild(elink)
						} else { // IE10+下载
							navigator.msSaveBlob(blob, fileName)
						}
					}
				})
			},
			//获取项目列表
			getspXmlb_vue(flag) {
				if(flag!=1){
					this.page=1
				}
				let params = {
					page: this.page,
					size: this.size,
					xmbh:this.filters.xmbh,
					spzt:this.filters.spzt,
					kssj:this.filters.kssj,
					jssj:this.filters.jssj
				};
				this.listLoading = true;
				getspXmlb(params).then((res) => {
					if(res.data.total>0&&res.data.row.length<=0){
						this.getXmlb_vue();
					}
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			}
		},
		mounted() {
			let end = new Date();
			let start = new Date();
			start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
			this.filters.jssj = util.formatDate.format(end,'yyyy-MM-dd');
			this.filters.kssj = util.formatDate.format(start,'yyyy-MM-dd');
			this.getspXmlb_vue();
		}
	}

</script>
<style>
	.toolbar {
		background: #ffffff;
		 /*padding: 10px;*/
		margin: 10px 0px;
	}
	.el-table th{
		background-color: #f5f7fa;
	}
</style>
