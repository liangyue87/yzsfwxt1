<!--
预算资料上传
-->
<template>
	<el-row>
		<el-col :span="24">
			<el-row style="margin-top: 15px;">
				<el-col :span="24">
					<el-card class="box-card">
						<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;"><span>预算资料上传</span></div>
						<el-upload
								class="upload-demo"
								action="#"
								:on-preview="handlePreview"
								:on-remove="handleRemove"
								:on-change="handleChange"
								:file-list="fileList"
								:http-request="insert"
								:show-file-list="false"
								:auto-upload="true">
							<el-button slot="trigger" size="mini" type="success"><i class="iconfont icon-shangchuan_f"></i>上传</el-button>
						</el-upload>
						<div class="text item" id="yszy">
							<!--<YSZLB :id='zbid'></YSZLB>-->
							<el-table :data="tableData" style="width: 100%">
								<el-table-column property="fjmc" label="资料名" width="280"></el-table-column>
								<el-table-column property="fjhz" label="资料类型" width="180"></el-table-column>
								<el-table-column property="scsj" label="上传时间" width="200"></el-table-column>
								<el-table-column property="username" label="上传人" width="200"></el-table-column>
								<el-table-column label="操作" width="200">
									<template slot-scope="scope">
										<a href="#" @click="downloadFile_vue(scope.$index, scope.tableData,2)" >
											<label title="下载">
												<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
											</label>
										</a>
										<el-button size="small" type="text" @click="deleteFj(scope.$index, scope.row)">
											<label title="删除">
												<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
											</label>
										</el-button>
										<!--<el-button size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.tableData,2)">下载</el-button>-->
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
	import {getyszl,importFile,download,deleteFkFj } from '../../api/Yswt';
	export default {
		props: ['zbid','type'],
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
			getYszl() {
				let params = {}
				if (this.type == "1"){
					params={
						xmid:this.$route.query.xmid,//项目id
						zbid:this.$route.query.yszbid,//预算主表id
					}
				} else {
					params = {
						xmid:this.$route.query.tow1.id,
						zbid:this.$route.query.tow1.zbid
					}
				}

				getyszl(params).then((res) => {
					if (!res.data.YsZlxx[0]){
						this.tableData = '';
					}else {
						this.tableData = res.data.YsZlxx;
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
						this.getYszl();
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
			addFile(file, fileList) {
				this.fileList = fileList;
				//限制上传文件为5M
				let sizeIsSatisfy = file.size < 5 * 1024 * 1024 ? true : false;
				if (!sizeIsSatisfy) {
					this.fileSizeIsSatisfy = false;
				}
				this.insert();
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
							this.getYszl();
						});
					}
				}
			},

		},
		mounted() {
			this.getYszl();
		}
	};
</script>

<style></style>
