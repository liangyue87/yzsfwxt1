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
				<!--配合联合勘查的部门-->
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div class="div_title">勘查反馈</div>
					</div>
					<div class="text item">
						<el-form :model="kcfk" label-width="150px" ref="kcfk" :rules="rules">
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
							<!--<el-form-item label="实际勘查时间" prop="sjkcsj">
								<el-date-picker v-model="kcfk.sjkcsj" type="datetime" placeholder="选择日期时间"
												value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm"></el-date-picker>
							</el-form-item>-->
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-checkbox v-model="kcfk.sfycgc" true-label="1" false-label="0">超高层</el-checkbox>
									</el-col>
									<el-col :span="10">
										<el-form-item label="">
											泵房供水位置
											<el-input type="text" size="small" v-model="kcfk.bfgswz" style="width: 180px;"></el-input>
										</el-form-item>
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-checkbox v-model="kcfk.sfyldsx" true-label="1" false-label="0">楼顶水箱</el-checkbox>
									</el-col>
									<el-col :span="10" style="text-align: left;">
										<el-form-item label="">
											楼顶水箱位置
											<el-input type="text" size="small" v-model="kcfk.ldsxwz" style="width: 180px;"></el-input>
										</el-form-item>
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-checkbox v-model="kcfk.sfxydllhpf" true-label="1" false-label="0">道路绿化破复</el-checkbox>
									</el-col>
									<el-col :span="4">
										<el-checkbox v-model="kcfk.sfxypzdb" true-label="1" false-label="0">需要配置地表</el-checkbox>
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-radio v-model="kcfk.sfhykjbylzg" label="1" false-label="0">有</el-radio>
										<el-radio v-model="kcfk.sfhykjbylzg" label="0" false-label="0">否</el-radio>
									</el-col>
									<el-col :span="10">
										有可接表预留支管(若有在反馈意见里说明)；
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="" prop="bqgcd">
								<el-row>
									<el-col :span="4">
										<el-radio v-model="kcfk.sfxybqg" label="1" false-label="0">有</el-radio>
										<el-radio v-model="kcfk.sfxybqg" label="0" false-label="0">否</el-radio>
									</el-col>
									<el-col :span="20">
										需敷设表前管(约<el-input type="text" size="small" v-model="kcfk.bqgcd" style="width: 100px;"></el-input>m；
										接表需开挖面积约<el-input type="text" size="small" v-model="kcfk.jbxkwmj" style="width: 100px;" ref="jbxkwmj"> </el-input>㎡)；
										(包含过路管开挖敷设)
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="" prop="tgjl">
								<el-row>
									<el-col :span="4">
										<el-radio v-model="kcfk.sfxyzglg" label="1" false-label="0">有</el-radio>
										<el-radio v-model="kcfk.sfxyzglg" label="0" false-label="0">否</el-radio>
									</el-col>
									<el-col :span="20">
										接表是否需要做过路管(若需要做过路管，拖管距离约
										<el-input type="text" size="small" v-model="kcfk.tgjl" style="width: 100px;"></el-input>m)；
										顶管管尾处破复面积约<el-input type="text" size="small" v-model="kcfk.dggwcpfmj" style="width: 100px;" ref="dggwcpfmj"></el-input>㎡；
									<br>
										过路管勾点处破复面积约<el-input type="text" size="small" v-model="kcfk.glggdpfmj" style="width: 100px;" ref="glggdpfmj"></el-input>㎡
									</el-col>
								</el-row>
							</el-form-item>

							<el-table :data="zlrow"  :stripe="true" style="width: 80%;margin-left: 150px;margin-bottom: 20px;" >
								<el-table-column prop="FJLXEJNAME" label="资料类型" min-width="400" align="center">
								</el-table-column>
								<el-table-column prop="COUNT" label="资料数量" width="200" align="center">
								</el-table-column>
								<el-table-column label="操作" align="center" width="200">
									<template slot-scope="scope" >
										<el-row>
											<el-col :span="12" style="text-align: right">
												<el-upload
														class="upload-demo"
														:show-file-list="false"
														action="#"
														multiple
														:http-request="submitUpload"
														:on-preview="handlePreview"
														:on-change="handleChange"
														:on-remove="handleRemove"
														:file-list="fileList">
													<label title="上传"><i @click="chooseFileClick(scope.$index, scope.row)" class="iconfont icon-shangchuan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
												</el-upload>
											</el-col>
											<el-col :span="12" style="text-align: left">
												<label title="详情"><i @click="toDetial_vue(scope.$index, scope.row)"  class="iconfont icon-xiangqing" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
												<!--<el-button size="small" type="primary" >上传</el-button>-->
												<!--<el-button size="small" type="primary" @click="toDetial_vue(scope.$index, scope.row)">详情</el-button>-->
											</el-col>
										</el-row>
									</template>
								</el-table-column>
							</el-table>

							<!--<el-form-item label="附件上传" style="width: 90%">
								<el-upload
										action="#"
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
									&lt;!&ndash;<el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传</el-button>&ndash;&gt;
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
												&lt;!&ndash;<el-button slot="reference" size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.row,1)">查看</el-button>
												<el-button size="small" type="danger" @click="deleteFj(scope.$index, scope.row)">删除</el-button>&ndash;&gt;

												<label title="查看" @click="downloadFile_vue(scope.$index, scope.row,1)">
													<i class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

												<label title="删除" @click="deleteFj(scope.$index, scope.row)">
													<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
											</div>
											<div v-else>
												&lt;!&ndash;<el-button size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.row,2)">下载</el-button>
												<el-button size="small" type="error" @click="deleteFj(scope.$index, scope.row)">删除</el-button>&ndash;&gt;

												<label title="下载" @click="downloadFile_vue(scope.$index, scope.row,2)">
													<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

												<label title="删除" @click="deleteFj(scope.$index, scope.row)">
													<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
											</div>

										</template>
									</el-table-column>
								</el-table>
							</el-form-item>-->
							<el-form-item label="反馈意见" style="width: 90%" prop="info">
								<el-input type="textarea" v-model="kcfk.info"></el-input>
							</el-form-item>
						</el-form>
					</div>
				</el-card>
				<el-row style="text-align: center;margin-top: 25px;">
					<el-button size="mini" type="info" @click="toRwFklb">
						<i class="iconfont icon-quxiao"></i>取消</el-button>

					<el-button size="mini" type="primary" @click="submitKcfk('kcfk')":loading="sumbitLoading">
						<i class="iconfont icon-queren"></i>提交</el-button>

					<el-button size="mini" type="primary" @click="goxmqj()">
						<i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
				</el-row>
			</el-col>
		</el-row>


		<el-dialog title="项目资料详情" :visible.sync="dialogTableVisible" :modal-append-to-body="false" style="padding-bottom: 10px" height="400px">
			<el-table :data="gridData" :stripe="true" style="padding-bottom: 10px">
				<el-table-column property="fjmc" label="附件名称" align="center"></el-table-column>
				<el-table-column property="scsj_vue" label="上传时间" align="center"></el-table-column>
				<el-table-column property="scr" label="上传人" align="center"></el-table-column>
				<el-table-column property="address" label="操作" align="center">
					<template slot-scope="scope">
						<el-row>
							<el-col :span="24">
								<div v-if="scope.row.fjhz!=null&&(scope.row.fjhz.toLowerCase()=='png'||scope.row.fjhz.toLowerCase()=='jpg')">
									<el-col :span="24">
										<label title="删除"><i class="iconfont delete" @click="deleteFj(scope.$index, scope.row)" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
										<label title="预览"><i class="iconfont icon-yulan" @click="downloadFile_vue(scope.$index, scope.row,1)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
									</el-col>
								</div>
								<div v-else>
									<el-col :span="24">
										<label title="删除"><i @click="deleteFj(scope.$index, scope.row)" class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
										<label title="下载"><i @click="downloadFile_vue(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
									</el-col>
								</div>
							</el-col>
						</el-row>
					</template>
				</el-table-column>
			</el-table>
		</el-dialog>

	</section>
</template>
<style lang="css">
	.row{
		margin-top: 0px;
	}
	.col{
		margin-top: 12px;
	}
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
	import axios from 'axios'
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import ZKCXX from '@/views/xkylx/zkcxx.vue';
	import util from '../../common/js/util'
	import { saveXckcFk,updateCheckTask,getFkFjData,deleteFkFj,download,importFile_sgdw,getKczbxx,toDetial_sgdw,getFkFjData_sgdw } from '../../api/xkylx';
	import hhldsx from '@/views/template/hhldsx.vue';

	let zlType="";
	let yjfjlx="";
	export default {
		data() {
			var checkNum = (rule, value, callback) => {
				let jbxkwmj = this.$refs.jbxkwmj.value;
				var reg = /^(?:0|[1-9]\d*)(?:\.\d*[1-9])?$/;
				if (!reg.test(value) && !value == '') {
					callback(new Error('表前管长度必须为有效数字'));
				} else if(!reg.test(jbxkwmj) && !jbxkwmj == ''){
					callback(new Error('开挖面积必须为有效数字'));
				}else {
					callback();
				}
			};
			var checkNum2 = (rule, value, callback) => {
				let dggwcpfmj = this.$refs.dggwcpfmj.value;
				let glggdpfmj = this.$refs.glggdpfmj.value;
				var reg = /^(?:0|[1-9]\d*)(?:\.\d*[1-9])?$/;
				if (!reg.test(value) && !value == '') {
					callback(new Error('拖管距离必须为有效数字'));
				}else if(!reg.test(dggwcpfmj) && !dggwcpfmj == ''){
					callback(new Error('顶管管尾处破复面积必须为有效数字'));
				}else if(!reg.test(glggdpfmj) && !glggdpfmj == ''){
					callback(new Error('过路管勾点处破复面积必须为有效数字'));
				}else {
					callback();
				}
			};
			return {
				type : '2',//施工单位
				kcsj : '',//预约勘查时间
				kcdd:'',//勘查地点
				kcbz:'',//勘查备注
				xmid: this.$route.query.xmid,
				activityId:this.$route.query.activityId,
				zlrow:[],
				gridData:[],
				kcfk : {
					sfycgc : '0',
					sfyldsx : '0',
					sfxydllhpf :'0',
					sfxypzdb :'0',
					sjkcsj : '',
					bfgswz :'',
					ldsxwz :'',
					info : '',
					sfhykjbylzg : '0',
					sfxybqg :'0',
					jbxkwmj :'',
					bqgcd :'',
					sfxyzglg :'0',
					tgjl :'',
					dggwcpfmj :'',
					glggdpfmj :''
				},
				fjData:[],
				fileList : [],
				sumbitLoading : false,
				dialogTableVisible : false,
				rules: {
					bqgcd: [
						{validator: checkNum, trigger: 'blur'}
					],
					tgjl: [
						{validator: checkNum2, trigger: 'blur'}
					],
					info: [
						{required: true, message: '勘查反馈意见不可为空', trigger: 'blur'}
					]
				}
			}
		},
		methods: {
			getFjData(){
				let params = {xmid:this.xmid,type:'2'}
				getFkFjData_sgdw(params).then((res) => {
					this.zlrow = res.data.fj;
				})
			},
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid }})
			},
			chooseFileClick(index,row){
				zlType = row.ID;
				yjfjlx = row.PARENTID
			},
			toDetial_vue(index, row) {
				this.dialogTableVisible = true;
				let params = {
					ejfjlx: row.ID,
					yjfjlx: row.PARENTID,
					xmid: this.xmid
				}
				toDetial_sgdw(params).then((res) => {
					if (res.data != null && res.data.row != null) {
						this.gridData = res.data.row;
					}
				})
			},
			/*deleteFj(index,row,flag) {
				this.$confirm('确认删除该附件？, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'error'
				}).then(() => {
					let params = {id:row.id,type:'2'}
					deleteFkFj(params).then((res) => {
						this.getFjData();
					})
				}).catch(() => {
				});
			},*/
			deleteFj(index,row,flag) {
				this.$confirm('确认删除该附件？, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'error'
				}).then(() => {
					let params = {id:row.id,type:'2'}
					deleteFkFj(params).then((res) => {
						this.getFjData();
						let detialRow={
							ID:row.fjlxEj,
							PARENTID:row.fjlxYj
						}
						this.toDetial_vue("",detialRow);
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
			handleExceed(files, fileList) {
				this.$message.warning('当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件');
			},
			beforeRemove(file, fileList) {
				return this.$confirm(`确定移除 ${ file.name }？`);
			},
			toRwFklb(){
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');
				this.$routerTab.refresh('/MyWork');
			},
			getKczbxx(){
				let params = {xmid:this.xmid}
				getKczbxx(params).then((res) => {
					this.kcbz = res.data.kcbz;
					this.kcdd = res.data.kcdd;
					this.kcsj = res.data.kcsj;
				})
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
									this.toRwFklb();
								} else {
									this.$message({
										message: '提交反馈结果异常',
										type: 'error'
									});
								}
							});

						}).catch(() => {

						});
					}else {
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
						formData.append("multipartFiles",file.raw);
						formData.append("filename",file.name);
						formData.append("xmid",this.xmid);
						formData.append("zlType",zlType);
						formData.append("yjfjlx",yjfjlx);
						importFile_sgdw(formData).then((res) => {
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
		},
		components: {
			'hhldsx': hhldsx,
			'XMJBXX': XMJBXX,  //将别名demo 变成 组件 Demo
			'ZKCXX' : ZKCXX
		}

	}
</script>