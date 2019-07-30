<!--
预算资料上传
-->
<template>
	<el-row>
		<el-col :span="24">
			<el-row style="margin-top: 15px;">
				<el-col :span="24">
					<el-card class="box-card">
						<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;"><span>预算成果上传</span></div>
						<el-upload
								v-if="type != '40008'"
								class="upload-demo"
								action="#"
								:on-preview="handlePreview"
								:on-remove="handleRemove"
								:on-change="handleChange"
								:file-list="fileList"
								:http-request="insert"
								:show-file-list="false"
								:auto-upload="true">
							<el-button slot="trigger" size="mini" type="success" style="margin-bottom: 10px" v-if="type_qjt != '1'"><i class="iconfont icon-shangchuan_f" ></i>上传</el-button>
						</el-upload>
						<div class="text item">
							<el-table :data="tableData" style="width: 100%">
								<el-table-column property="fjmc" label="资料名" width="280" align="center"></el-table-column>
								<el-table-column property="fjhz" label="资料类型" width="180" align="center"></el-table-column>
								<el-table-column property="scsj" label="上传时间" width="200" align="center"></el-table-column>
								<el-table-column property="username" label="上传人" width="200" align="center"></el-table-column>
								<el-table-column label="操作" width="200" align="center">
									<template slot-scope="scope">
										<label title="预览" v-if="scope.row.fjhz!=null&&(scope.row.fjhz.toLowerCase()=='png'||scope.row.fjhz.toLowerCase()=='jpg')">
											<i class="iconfont icon-yulan" @click="downloadFile_vue(scope.$index, scope.tableData,1)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
										</label>
										<label title="下载">
											<i class="iconfont icon-xiazai" @click="downloadFile_vue(scope.$index, scope.tableData,2)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
										</label>
										<label title="删除" v-if="type != '40008'" v-show="type_qjt != '1'">
											<i class="iconfont delete" @click="deleteFj(scope.$index, scope.row)" style="cursor:pointer;padding: 2px;"></i>
										</label>
									</template>
								</el-table-column>
							</el-table>
						</div>
					</el-card>
				</el-col>
			</el-row>
		</el-col>
		<el-col :span="24" style="text-align: center;margin-top: 10px;"></el-col>
	</el-row>


</template>

<script>
	import axios from 'axios'
	import YSZLB from '../../components/yswt/yszlb.vue'
	import {getLate,importFile,download,deleteFkFj } from '../../api/Yswt';
	export default {
		props: ['zbid','type','xmid','xmbh','type_qjt'],
		data() {
			return {
				fileList: [],
				fileSizeIsSatisfy: true,
				isRouterAlive: true,
				tableData: [],
			};
		},

		components:{
			YSZLB,
		},
		methods: {
			//查询基本数据
			getLate() {
				let params = {
					zbid:this.$route.query.tow1.zbid
				}
				getLate(params).then((res) => {
					if (!res.getLate[0]){
						this.tableData = '';
					}else {
						this.tableData = res.getLate;
					}

				});
			},
			deleteFj(index,row) {
				this.$confirm('确认删除该附件？, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'error'
				}).then(() => {
					let params = {fjid:row.fjid}
					deleteFkFj(params).then((res) => {
						this.getLate();
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
			downloadFile_vue(index,tableData,flag){
				tableData=this.tableData[index];
				let formData = new FormData();
				formData.append("path",tableData.fjlj);

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
						const content = res;
						const blob = new Blob([content]);
						const fileName = tableData.fjmc;

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
			insert() {
				if (this.fileList != null) {
					for (let i = 0; i < this.fileList.length; i++) {
						let file = this.fileList[i];
						let formData = new FormData();
						//传值自己改
						formData.append('multipartFiles', file.raw);
						formData.append('filename', file.name);
						formData.append('ruzuid', this.zbid);
						formData.append('xmid',this.xmid);
						formData.append('xmbh',this.xmbh);
						//接口自己改
						importFile(formData).then((res) => {
							let msg = res.msg;
							if (msg == '上传成功') {
								this.$message({
									message: '上传成功',
									type: 'success'
								});
							} else {
								this.$message.error('上传失败！');
							}
							this.fileList = [];
							this.getLate();
						});
					}
				}
			},

		},
		mounted() {
			this.getLate();
		}
	};
</script>

<style></style>
