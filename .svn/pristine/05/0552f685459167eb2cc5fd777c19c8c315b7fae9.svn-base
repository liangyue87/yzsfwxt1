<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24">
				<!--红黄绿灯时间-->
				<hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
				<!--项目基本信息模板-->
				<XMJBXX></XMJBXX>
				<!--配合联合勘查的部门-->
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div class="div_title">用户确认</div>
					</div>
					<div class="text item">
						<el-form :model="jgqr" label-width="150px" ref="jgqr" :rules="rules">
							<el-row>
								<el-col :span="12">
									<el-form-item label="客户同意时间" style="width: 100%" prop="yhtysj">
										<el-date-picker v-model="jgqr.yhtysj" type="date" placeholder="选择日期时间" size="small"
														format="yyyy-MM-dd" value-format="yyyy-MM-dd" ></el-date-picker>
									</el-form-item>
								</el-col>
								<!--<el-col :span="12">
									<el-form-item label="实际勘查时间" style="width: 100%">
										<el-date-picker v-model="jgqr.sjkcsj" type="datetime" placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss" ></el-date-picker>
									</el-form-item>
								</el-col>-->
							</el-row>
							<el-form-item label="资料上传" style="width: 90%">
								<!--<el-upload
										class="upload-demo"
										ref="upload"
										action="#"
										:on-preview="handlePreview"
										:on-remove="handleRemove"
										:on-change="handleChange"
										:file-list="fileList"
										:http-request="submitUpload"
										:show-file-list="false"
										:auto-upload="true">
									&lt;!&ndash;<el-button slot="trigger" size="mini" type="primary">
										<i class="iconfont icon-shangchuan"></i>上传</el-button>&ndash;&gt;
								</el-upload>-->
									<el-button size="mini" type="primary" @click="openVisible">
										<i class="iconfont icon-paishe"></i>拍摄</el-button>
									<el-table :data="fjData" border style="width: 100%;">
										<el-table-column property="fjmc" label="附件名称" min-width="180" align="center">
										</el-table-column>
										<el-table-column property="fjhz" label="文件类型" width="120" align="center">
										</el-table-column>
										<el-table-column property="fjlx_str" label="附件类型" width="200" align="center">
										</el-table-column>
										<el-table-column property="scsj_str" label="上传时间" min-width="180" align="center">
										</el-table-column>
										<el-table-column property="xmdz" label="操作"  width="120" align="center">
											<template slot-scope="scope">
												<!--<div v-if="scope.row.fjhz=='png'||scope.row.fjhz=='jpg'">
													<el-button slot="reference" size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.row,1)">查看</el-button>
													<el-button size="small" type="error" @click="deleteFj(scope.$index, scope.row)">删除</el-button>
												</div>
												<div v-else>
													<el-button size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.row,2)">下载</el-button>
													<el-button size="small" type="error" @click="deleteFj(scope.$index, scope.row)">删除</el-button>
												</div>-->

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
								<!--</el-upload>-->
							</el-form-item>
						</el-form>
					</div>
				</el-card>
				<el-row style="text-align: center;margin-top: 0px;">
					<el-row style="text-align: center;margin-top: 25px;">
						<el-button size="mini" type="info" @click="toFkjgqrlb">
							<i class="iconfont icon-quxiao"></i>取消</el-button>

						<el-button size="mini" type="primary" @click="submitJgqr('jgqr')" :loading="sumbitLoading">
							<i class="iconfont icon-queren"></i>提交</el-button>

						<el-button size="mini" type="primary" @click="goxmqj()">
							<i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
					</el-row>

				</el-row>
			</el-col>
		</el-row>


		<el-dialog title="拍摄" :visible.sync = "creamVisible" top="5vh">
			<DocCamera v-bind:dirName ="dirName" v-bind:fileName ="fileName" v-on:listenToChildEvent="showMsg"></DocCamera>
		</el-dialog>
	</section>
</template>
<style scoped>
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
	import DocCamera from '../template/DocCamera';
	import { saveXckcJgqr,updateCheckTask,getPjFjData,deleteFkFj,download,saveImagePath,getXmbhByXmid } from '../../api/xkylx';
	import hhldsx from '@/views/template/hhldsx.vue';

	export default {
		data() {
			return {
				//用来接收子组件保存的图片名称以及之前保存的图片 将名字push进去
				fileItem : [],
				fileName: 'xkylx',//给子组件的文件名字
				dirName: '',//保存图片的文件夹名字
				file : [],//图片Base64数据
				checkImage : [],//选中的文件暂存数组
				creamVisible : false,
				options: [
					{
						value: '1',
						label: '同意'
					},{
						value: '0',
						label: '不同意'
					}
				],
				fjData : [],
				kcsj : '2019-10-10 15:00:00',//预约勘查时间
				xmid:this.$route.query.xmid,
				activityId:this.$route.query.activityId,
				jgqr : {
					khsfty : '1',
					yhtysj : '',
				},
				fileList : [],
				sumbitLoading : false,
				rules: {
					yhtysj: [
						{required: true, message: '时间不能为空', trigger: 'blur'}
					]
				}
			}
		},
		methods: {
			toFkjgqrlb(){//跳转页面Fkjgqrlb
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');
			},
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid }})
			},
			getFjData(){
				let params = {xmid:this.xmid,type:'5'}
				getPjFjData(params).then((res) => {
					this.fjData = res.data.fj;
				})
			},
			init(){//初始化参数
				this.fileSize = this.fileItem.length;
				//let JSONstr = sessionStorage.getItem("user");
				//let JSON = eval("("+JSONstr+")");
				//console.log(JSON);
				//console.log(JSON.username);
				//this.dirName = JSON.username;//文件夹名字
				getXmbhByXmid({xmid:this.xmid}).then((res) => {
					this.dirName = res.data;//文件夹名字
				});
			},
			showMsg:function(data){//存储子组件保存的图片名称
				//this.fileItem.push(data);
				//this.fileSize = this.fileItem.length;
				console.log(data);
				//接口预留位置  data为图片名称 将图片名称保存到数据库
				let params = {xmid:this.xmid,fileName:data,dirName:this.dirName};
				saveImagePath(params).then((res) => {
					this.$message({
						message: '拍摄上传成功',
						type: 'success'
					});
					this.getFjData();
				})
			},
			openVisible(){
				this.creamVisible = true;
			},
			submitJgqr(jgqr){//提交勘察结果用户确认
				this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$refs[jgqr].validate((valid) => {
						if (valid) {
							this.sumbitLoading = true;
							let params = Object.assign({ type: this.type,xmid: this.xmid}, this.jgqr);
							saveXckcJgqr(params).then((res) => {
								this.sumbitLoading = false;
								if(res.result == 'success'){
									this.$message({
										message: '提交成功',
										type: 'success'
									});
									this.toFkjgqrlb();
								}else {
									this.$message({
										message: '提交异常',
										type: 'error'
									});
								}
							});
						}else {
							return false;
						}
					});
				}).catch(() => {

				});

			},
			deleteFj(index,row,flag) {
				this.$confirm('确认删除该附件？, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'error'
				}).then(() => {
					let params = {id:row.id,type:'5'}
					deleteFkFj(params).then((res) => {
						this.getFjData();
					})
				}).catch(() => {
				});
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
			updateCheckTask({xmid:this.xmid,type:''}).then((res) => {});//更改任务签收状态
			this.getFjData();
			this.init();
		},
		components: {
			'hhldsx': hhldsx,
			'DocCamera' : DocCamera,
			'XMJBXX': XMJBXX  //将别名demo 变成 组件 Demo
		}

	}
</script>