<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24">
				<!--项目基本信息模板-->
				<XMJBXX></XMJBXX>
				<!--配合联合勘查的部门-->
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div class="div_title">{{title}}</div>
					</div>
					<div class="text item" id="row">
						<el-row>
							<el-col :span="14">
								<table class="border-table">
									<tr><td></td><td>设计院</td><td>施工单位</td><td>区所</td><td>预算科</td></tr>
									<tr>
										<td>勘查部门</td>
										<td><el-checkbox v-model="check_value.sjy_value" :disabled="disabled" @change="changeSjy"></el-checkbox></td>
										<td><el-checkbox v-model="check_value.sgdw_value" :disabled="disabled" @change="changeSgdw"></el-checkbox></td>
										<td><el-checkbox v-model="check_value.qs_value" :disabled="disabled" @change="changeQs"></el-checkbox></td>
										<td><el-checkbox v-model="check_value.ys_value" :disabled="disabled" @change="changeYs"></el-checkbox></td>
									</tr>
									<tr>
										<td>是否要求到场</td>
										<td><el-checkbox v-model="check_value.sjydc_value" :disabled="disabled" @change="changeSjyDc"></el-checkbox></td>
										<td><el-checkbox v-model="check_value.sgdwdc_value" :disabled="disabled" @change="changeSgdwDc"></el-checkbox></td>
										<td><el-checkbox v-model="check_value.qsdc_value" :disabled="disabled" @change="changeQsDc"></el-checkbox></td>
										<td><el-checkbox v-model="check_value.ysdc_value" :disabled="disabled" @change="changeYsDc"></el-checkbox></td>
									</tr>
								</table>
							</el-col>
							<el-col :span="1" style="text-align: center;">&nbsp;</el-col>
							<el-col :span="9" style="text-align: center;">
								<el-form label-width="120px" :rules="rules" :model="kcxx" ref="kcxx">
									<el-row>
										<el-form-item label="预约勘查时间：" v-if="type == '0'" prop="kcsj">
											<el-date-picker v-model="kcxx.kcsj" type="datetime" placeholder="选择日期时间"
															format="yyyy-MM-dd HH:mm" value-format="yyyy-MM-dd HH:mm"
															style="width: 100%;"></el-date-picker>
										</el-form-item>
										<el-form-item label="预约勘查时间：" v-if="type == '1'">
											<el-date-picker v-model="kcxx.kcsj" type="datetime" placeholder="选择日期时间"
															format="yyyy-MM-dd HH:mm" value-format="yyyy-MM-dd HH:mm"
															style="width: 100%;" readonly></el-date-picker>
											<!--<el-button type="warning" @click="updateKcsj" icon="el-icon-close">修改勘查时间</el-button>-->
											<el-button size="mini" type="warning" @click="updateKcsj" :loading="sumbitLoading" style="display: none">
												<i class="iconfont icon-ai-edit"></i>修改勘查时间</el-button>
										</el-form-item>
									</el-row>
									<el-row>
										<el-form-item label="勘查地点：" prop="kcdd">
											<el-input type="textarea" v-model="kcxx.kcdd" style="width: 100%;" :readonly="readonly"></el-input>
										</el-form-item>
									</el-row>
								</el-form>
							</el-col>
							<el-col :span="24" style="margin-top: 15px;">
								<el-form label-width="60px" :model="kcxx">
									<el-form-item label="备注：">
										<el-input type="textarea" v-model="kcxx.bz" style="width: 100%;" :readonly="readonly"></el-input>
									</el-form-item>
								</el-form>
							</el-col>
							<el-col :span="24" style="text-align: center;">

									<el-row style="margin-top: 25px;" v-if="type == '0'">
										<el-button size="mini" type="info" @click="toXckcrwcj">
											<i class="iconfont icon-quxiao"></i>取消</el-button>

										<el-button size="mini" type="primary" @click="saveXckcTask('kcxx')" :loading="sumbitLoading">
											<i class="iconfont icon-queren"></i>发起</el-button>

										<el-button size="mini" type="primary" @click="goxmqj()">
											<i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
										<!--<el-button type="info" @click="toXckcrwcj" ><i class="iconfont icon-quxiao"></i>取消</el-button>
										<el-button type="primary" @click="saveXckcTask('kcxx')" icon="el-icon-check" :loading="sumbitLoading">提交</el-button>-->
									</el-row>
									<el-row style="margin-top: 25px;" v-if="type == '1'">
										<el-button size="mini" type="info" @click="toXckcrwcj">
											<i class="iconfont icon-quxiao"></i>取消</el-button>

										<el-button size="mini" type="danger" @click="open">
											<i class="iconfont icon-zuofei"></i>终止</el-button>

										<el-button size="mini" type="primary" @click="goxmqj()">
											<i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
										<!--<el-button type="info" @click="toXckcrwcj" icon="el-icon-close">取消</el-button>
										<el-button type="danger" @click="open" icon="el-icon-check">终止</el-button>-->
									</el-row>

							</el-col>
						</el-row>
					</div>
				</el-card>
			</el-col>

			<el-col :span="24" style="text-align: center;margin-top: 35px;">

			</el-col>
		</el-row>


		<el-dialog title="修改预约勘查时间" :visible.sync ="updateVisible" width="600px" top="35vh">
			<el-form label-width="140px" >
				<el-row>
					<el-col :span="24">
						<el-form-item label="修改预约勘查时间">
							<el-date-picker v-model="newkcsj" type="datetime" placeholder="选择日期时间"
											value-format="yyyy-MM-dd HH:mm" format="yyyy-MM-dd HH:mm" class="inputBox"></el-date-picker>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="24" style="text-align: center;margin: 10px;">
						<el-button type="info" size="mini" @click.native="updateVisible = false" icon="el-icon-close">取消</el-button>
						<el-button type="primary" size="mini" @click="saveKcsj" icon="el-icon-check">保存</el-button>
					</el-col>
				</el-row>
			</el-form>
		</el-dialog>

	</section>
</template>
<style>
	#row .el-row{
		margin-top: 10px;
	}

</style>
<script>
	import '../../assets/iconfont/iconfont.css'
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import util from '../../common/js/util'

	import { saveXckcRwzb,ShowXckcxx,saveKcsj,abandonKcrw } from '../../api/xkylx';
	export default {
		data() {
			return {
				title : '选择配合联合勘查的部门',
				tabUrl : '',
				newkcsj : '',//修改的预约勘查时间
				xmid : this.$route.query.xmid,
				kcdd : this.$route.query.kcdd,
				kczbid : '',
				type : this.$route.query.type,//0 发起勘查任务  1修改勘查时间 终止本次勘查任务
				updateVisible : false,
				sumbitLoading : false,
				disabled : false,
				readonly : false,
				kcxx: {
					kcsj : '',//预约勘查时间
					bz : '请区所提前了解管线情况',
					kcdd : ''
				},
				check_value : {
					sjy_value: true,
					sgdw_value: true,
					qs_value: true,
					sjydc_value: true,
					sgdwdc_value: true,
					qsdc_value: true,
					ys_value: true,
					ysdc_value: true
				},
				rules: {
					kcsj: [
						{required: true, message: '预约勘查时间不能为空', trigger: 'blur'}
					],
					kcdd: [
						{required: true, message: '勘查地点不能为空', trigger: 'blur'}
					]
				}
			}
		},
		methods: {
			toXckcrwcj(){//跳转页面Xckcrwcj

				this.$routerTab.close();
				this.$routerTab.refresh('/Xckcrwcj');

			},
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid }})
			},
			changeSjy(){
				this.check_value.sjydc_value = this.check_value.sjy_value;
			},
			changeSgdw(){
				this.check_value.sgdwdc_value = this.check_value.sgdw_value
			},
			changeQs(){
				this.check_value.qsdc_value = this.check_value.qs_value;
			},
			changeYs(){
				this.check_value.ysdc_value = this.check_value.ys_value;
			},
			changeSjyDc(){
				if(this.check_value.sjydc_value){
					this.check_value.sjy_value = true;
				}
			},
			changeSgdwDc(){
				if(this.check_value.sgdwdc_value){
					this.check_value.sgdw_value = true;
				}

			},
			changeQsDc(){
				if(this.check_value.qsdc_value){
					this.check_value.qs_value = true;
				}

			},
			changeYsDc(){
				if(this.check_value.ysdc_value){
					this.check_value.ys_value = true;
				}

			},
			saveXckcTask(kcxx){//发起联合勘查
				if( !(this.check_value.sjy_value || this.check_value.sgdw_value || this.check_value.qs_value || this.check_value.ys_value) ){
					this.$message({
						message: '请至少选择一个部门参与联合勘查！',
						type: 'error'
					});
					return;
				}
				this.$refs[kcxx].validate((valid) => {
					if (valid) {
						this.sumbitLoading = true;
						let params = Object.assign({ xckcsj : this.kcxx.kcsj, xmid : this.xmid , bz : this.kcxx.bz, kcdd : this.kcxx.kcdd}, this.check_value);
						saveXckcRwzb(params).then((res) => {
							this.sumbitLoading = false;
							if(res.result == "success"){
								this.$message({
									message: '发起勘查任务成功',
									type: 'success'
								});
							}else {
								this.$message({
									message: '发起勘查任务失败',
									type: 'error'
								});
							}
							this.tabUrl='/Xckcrwcj'
							this.toXckcrwcj();
						});
					} else {
						return false;
					}
				});
			},
			open() {
				this.$confirm('确认终止本次的勘查任务？, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'error'
				}).then(() => {
					this.abandon();
				}).catch(() => {
				});
			},
			showByType(){
				if(this.type == '1'){
					this.title = "配合勘查的部门";
					this.disabled = false;
					this.readonly = true;
					let params = { xmid : this.xmid };
					ShowXckcxx(params).then((res) => {
						let xckc = eval('('+res.data.xckc+')');
						this.kczbid = xckc.id;
						this.kcxx.kcsj = xckc.yykcrq;
						if(xckc.bz != 'null'){
							this.kcxx.bz = xckc.bz;
							this.kcxx.kcdd = xckc.kcdd;
						}
						let bm = res.data.bm;
						this.check_value = {
							sjy_value: false,
							sgdw_value: false,
							qs_value: false,
							sjydc_value: false,
							sgdwdc_value: false,
							qsdc_value: false
						}
						for(let i = 0;i<bm.length;i++){
							if(bm[i].kcbm == '1'){
								this.check_value.sjy_value=true;
								if(bm[i].sfxydc == '1')
								this.check_value.sjydc_value=true;
							}
							if(bm[i].kcbm == '2'){
								this.check_value.sgdw_value=true;
								if(bm[i].sfxydc == '1')
									this.check_value.sgdwdc_value=true;
							}
							if(bm[i].kcbm == '3'){
								this.check_value.qs_value=true;
								if(bm[i].sfxydc == '1')
									this.check_value.qsdc_value=true;
							}
							if(bm[i].kcbm == '4'){
								this.check_value.ys_value=true;
								if(bm[i].sfxydc == '1')
									this.check_value.ysdc_value=true;
							}
						}
					});
				}else {
					this.kcxx.kcdd = this.kcdd;
				}
			},
			updateKcsj(){
				this.updateVisible = true;
			},
			saveKcsj(){
				if(this.newkcsj == ''){
					this.$message({
						message: '预约勘查时间不可为空',
						type: 'error'
					});
					return;
				}
				let params = {kczbid: this.kczbid, newkcsj:  this.newkcsj};
				saveKcsj(params).then((res) => {
					let type = res.result;
					let message = res.msg;
					this.$message({
						message: message,
						type: type
					});
					this.updateVisible = false;
					this.showByType();
				});
			},
			abandon(){
				let params = { xmid: this.xmid, kczbid : this.kczbid};
				abandonKcrw(params).then((res) => {
					if(res.result == "success"){
						this.$message({
							type: 'success',
							message: '终止本次勘查任务成功!'
						});
					}else {
						this.$message({
							type: 'error',
							message: '终止本次勘查任务失败!'
						});
					}
				})
				this.toXckcrwcj();
			}
		},
		mounted() {
			this.showByType();
		},
		components: {
			'XMJBXX': XMJBXX  //将别名demo 变成 组件 Demo
		}

	}

</script>

<style scoped>
	.border-table {
		border-collapse: collapse;
		border: none;
		width: 100%;
	}
	.border-table td {
		border: solid rgb(235,237,240) 1px;
		width:8%;
		height:40px;
		text-align: center;
	}
	.div_title {
		margin:-18px -20px;
		padding:10px 20px;
		background: #f5f7fa;
		height: 18px;
	}
</style>