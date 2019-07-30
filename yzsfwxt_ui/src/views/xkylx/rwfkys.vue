<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24">
				<!--红黄绿灯时间-->
				<hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
				<!--项目基本信息模板-->
				<XMJBXX></XMJBXX>

				<!--其他部门的反馈意见-->
				<ZKCXX></ZKCXX>

				<!--勘查反馈-->
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div class="div_title">勘查反馈</div>
					</div>
					<div class="text item">
						<el-form :model="kcfk" label-width="150px"  ref="kcfk" :rules="rules">
							<el-form-item label="预约时间">
								<el-date-picker v-model="kcsj" type="datetime"
												format="yyyy-MM-dd HH:mm" size="small" style="width: 200px;" readonly disabled>
								</el-date-picker>
							</el-form-item>
							<el-form-item label="勘查地点" style="width: 90%;margin-bottom: 14px;">
								<el-input type="text" v-model="kcdd" style="width: 400px;" readonly disabled></el-input>
							</el-form-item>
							<el-form-item label="勘查备注" style="width: 90%;margin-bottom: 25px;">
								<el-input type="textarea" v-model="kcbz" style="width: 400px;" readonly disabled></el-input>
							</el-form-item>
							<!--<el-form-item label="实际勘查时间" style="width: 45%" prop="sjkcsj">
								<el-date-picker v-model="kcfk.sjkcsj" type="datetime" placeholder="选择日期时间"
												value-format="yyyy-MM-dd HH:mm" format="yyyy-MM-dd HH:mm"></el-date-picker>

							</el-form-item>-->
							<el-form-item label="附件上传" style="width: 90%">
								<el-upload
										action="#"
										multiple
										class="upload-demo"
										:on-preview="handlePreview"
										:on-remove="handleRemove"
										:on-change="handleChange"
										:file-list="fileList"
										:http-request="submitUpload"
										:show-file-list="false"
										:auto-upload="true">
									<el-button slot="trigger" size="mini" type="success">
										<i class="iconfont icon-shangchuan_f"></i>上传</el-button>
									<!--<el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload" icon="el-icon-upload">上传</el-button>-->
								</el-upload>
								<el-table :data="fjData" border style="width: 100%;">
									<el-table-column property="fjmc" label="附件名称" min-width="180" align="center">
									</el-table-column>
									<el-table-column property="fjhz" label="文件类型" width="120" align="center">
									</el-table-column>
									<el-table-column property="fjlx_str" label="附件类型" width="180" align="center">
									</el-table-column>
									<el-table-column property="scsj_str" label="上传时间" min-width="180" align="center">
									</el-table-column>
									<el-table-column property="xmdz" label="操作"  width="200" align="center">
										<template slot-scope="scope">
											<div v-if="scope.row.fjhz=='png'||scope.row.fjhz=='jpg'">
												<!--<el-button slot="reference" size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.row,1)">查看</el-button>
												<el-button size="small" type="danger" @click="deleteFj(scope.$index, scope.row)">删除</el-button>-->

												<label title="查看" @click="downloadFile_vue(scope.$index, scope.row,1)">
													<i class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

												<label title="删除" @click="deleteFj(scope.$index, scope.row)">
													<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
											</div>
											<div v-else>
												<!--<el-button size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.row,2)">下载</el-button>
												<el-button size="small" type="error" @click="deleteFj(scope.$index, scope.row)">删除</el-button>-->

												<label title="下载" @click="downloadFile_vue(scope.$index, scope.row,2)">
													<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

												<label title="删除" @click="deleteFj(scope.$index, scope.row)">
													<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
											</div>

										</template>
									</el-table-column>
								</el-table>
							</el-form-item>
							<el-form-item label="反馈意见" style="width: 90%" prop="info">
								<el-input type="textarea" v-model="kcfk.info"></el-input>
							</el-form-item>
						</el-form>
					</div>
				</el-card>
				<el-row style="text-align: center;margin-top: 25px;">
					<el-button size="mini" type="info" @click="toRwfklb">
						<i class="iconfont icon-quxiao"></i>取消</el-button>

					<el-button size="mini" type="primary" @click="submitKcfk('kcfk')":loading="sumbitLoading">
						<i class="iconfont icon-queren"></i>提交</el-button>

					<el-button size="mini" type="primary" @click="goxmqj()">
						<i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
				</el-row>
			</el-col>
		</el-row>

	</section>
</template>
<style lang="css">
	.div_title {
		margin:-18px -20px;
		padding:10px 20px;
		background: #f5f7fa;
		height: 18px;
	}
	.delete {
		font-size: 19px;
	}
</style>
<script>
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import ZKCXX from '@/views/xkylx/zkcxx.vue';
	import util from '../../common/js/util'
	import { saveXckcFk,updateCheckTask,importFile,getFkFjData,deleteFkFj,download,getKczbxx } from '../../api/xkylx';
	import hhldsx from '@/views/template/hhldsx.vue';

	export default {
		components: {
			'hhldsx': hhldsx,
			'XMJBXX': XMJBXX,  //将别名demo 变成 组件 Demo
			'ZKCXX' : ZKCXX
		},
		data() {
			return {
				type : '4',//预算科
				kcsj : '',//预约勘查时间
				kcdd:'',//勘查地点
				kcbz:'',//勘查备注
				xmid:this.$route.query.xmid,
				activityId:this.$route.query.activityId,
				kcfk : {
					sjkcsj : '',
					info : '',
				},
				fileList : [],
				fjData :[],
				sumbitLoading : false,
				rules: {
					sjkcsj: [
						{required: true, message: '实际勘查时间不能为空', trigger: 'blur'}
					],
					info: [
						{required: true, message: '勘查反馈意见不可为空', trigger: 'blur'}
					]
				}
			}
		},
		methods: {
			toRwfklb(){//跳转页面Rwfkys
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');
				this.$routerTab.refresh('/MyWork');
			},
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid }})
			},
			getFjData(){
				let params = {xmid:this.xmid,type:'4'}
				getFkFjData(params).then((res) => {
					this.fjData = res.data.fj;
				})
			},
			getKczbxx(){
				let params = {xmid:this.xmid}
				getKczbxx(params).then((res) => {
					this.kcbz = res.data.kcbz;
					this.kcdd = res.data.kcdd;
					this.kcsj = res.data.kcsj;
				})
			},
			deleteFj(index,row,flag) {
				this.$confirm('确认删除该附件？, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'error'
				}).then(() => {
					let params = {id:row.id,type:'6'}
					deleteFkFj(params).then((res) => {
						this.getFjData();
					})
				}).catch(() => {
				});
			},
			handleRemove(file, fileList) {
				console.log(file, fileList);
			},
			handlePreview(file) {
				console.log(file);
			},
			handleChange(file,fileList){
				this.fileList = fileList;
			},
			beforeRemove(file, fileList) {
				return this.$confirm(`确定移除 ${ file.name }？`);
			},
			submitKcfk(kcfk){//提交勘察反馈
				this.$refs[kcfk].validate((valid) => {
					if (valid) {
						this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
							confirmButtonText: '确定',
							cancelButtonText: '取消',
							type: 'warning'
						}).then(() => {
							this.sumbitLoading = true;
							let params = Object.assign({type: this.type, xmid: this.xmid}, this.kcfk);
							saveXckcFk(params).then((res) => {
								this.sumbitLoading = false;
								if (res.result == 'success') {
									this.$message({
										message: '提交反馈结果成功',
										type: 'success'
									});
								} else {
									this.$message({
										message: '提交反馈结果异常',
										type: 'error'
									});
								}
								this.toRwfklb();
							})
						}).catch(() => {

						});
					} else {
						return false;
					}
				});
			},
			submitUpload() {
				let fileList = this.fileList;
				let operation = "设计院反馈勘查附件";

				if (fileList !=null && fileList.length >0){
					//for (let i=0;i<fileList.length;i++){
						let file = fileList[fileList.length-1];
						let formData = new FormData();
						formData.append("operation",operation);
						formData.append("multipartFiles",file.raw);
						formData.append("filename",file.name);
						formData.append("xmid",this.xmid);
						formData.append("type",'6');
						importFile(formData).then((res) => {
							let msg = res.msg;
							if(msg == '上传成功'){
								this.$message({
									message: '上传成功',
									type: 'success'
								});
							}else{
								this.$message.error('上传失败！');
							}
							this.fileList = [];
							this.getFjData();
						});
					//}
				}else {
					this.$message.warning('未选择任何文件！');
				}
			},
			downloadFile_vue(index,row,flag){//附件查看或下载
				let formData = new FormData();
				formData.append("path",row.fjlj);
				download(formData).then((res) => { // 处理返回的文件流
					if(flag==1){
						this.slide1=[];
						let reader = new FileReader()
						reader.onload = (e) => {
							let item = {};
							let img = new Image();
							img.src =e.target.result;
							item.msrc = item.src = e.target.result;
							// this.Imgsrc = e.target.result;
							item.h = img.height;
							item.w = img.width;
							this.slide1.push(item);
							this.$preview.openList(0, this.slide1);
							//this.msgFlag = true;
							//this.prop.src = this.Imgsrc
						}
						reader.readAsDataURL(res)
					}else{
						const content = res
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
			}

		},
		mounted() {
			updateCheckTask({xmid:this.xmid,type:this.type}).then((res) => {});//更改任务签收状态
			this.getFjData();
			this.getKczbxx();
		}
	}
</script>