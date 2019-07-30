<!--上传用户确认凭证-->
<template>
	<el-row>
		<el-col :span="24">
			<el-row style="margin-top: 15px;">
				<el-col :span="24">
					<el-card class="box-card">
						<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
							<span>用户确认凭证上传</span>
						</div>
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
							<el-button slot="trigger" size="mini" type="success" style="margin-bottom: 10px" v-if="type !='1'"><i class="iconfont icon-shangchuan_f"></i>上传</el-button>
							<el-button  size="mini" type="primary" @click="openVisible" style="margin-left: 10px" v-if="type !='1'"><i class="iconfont icon-paishe"></i>拍摄</el-button>
							<el-button size="mini" type="primary" @click="showImageList" v-if="type !='1'"><i class="iconfont icon-yulan_f" ></i>预览(已拍摄{{fileSize}}张)</el-button>
							<el-dialog title="拍摄" :visible.sync = "creamVisible" top="5vh">
								<DocCamera v-bind:dirName ="dirName" v-bind:fileName ="fileName" @fatherMethod="showImageList" v-on:listenToChildEvent="showMsg"></DocCamera>
							</el-dialog>

							<el-dialog title="预览" :visible.sync ="photoVisible" width="800px">
								<el-row>
									<el-col :span="24">
										<el-button type="primary" @click.native="type = '1'" v-if="type == '0'">选择</el-button>
										<el-button type="danger"  v-if="type == '1'" @click="deleteImage" v-show="type !='1'">删除</el-button>
										<el-button type="info" @click.native="type = '0'" v-if="type == '1'">取消</el-button>
									</el-col>
								</el-row>
								<el-row style="margin-top: 25px;">
									<el-checkbox-group v-model="checkImage">
										<el-col :span="6" v-model="file" v-for="item in file" :key="item.name" style="margin-top: 2px;">
											<img :src="item.src" width="150px" height="120px" @click="showImage" style="margin-right: 2px">
											<el-checkbox :label="item.name" v-if="type == '1'" size="medium">&nbsp;</el-checkbox>
										</el-col>
									</el-checkbox-group>
								</el-row>
								<el-row>&nbsp;</el-row>
							</el-dialog>
							<!--	</section>-->
						</el-upload>
						<div class="text item" id="yszy">
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
										<label title="删除" v-if="type != '1'">
											<i class="iconfont delete" @click="deleteFj(scope.$index, scope.row)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
										</label>
									</template>
								</el-table-column>
							</el-table>
							<!--<YSZLB :id='zbid'></YSZLB>-->
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
	//import YSZLB from '../../components/gcpd/yszlb.vue'
	import DocCamera from '../../views/template/DocCamera'
	import { showImage } from '../../api/xkylx';
	import { saveImageToDb,getPsTotals,deleteImage,importFile,download,getyszl,deleteFkFj } from '../../api/Gcpd';

	export default {
		props: ['zbid','xmid','xmbh','type'],
		data() {
			return {
				tableData: [],
				fileList: [],
				fileSizeIsSatisfy: true,
				//(高拍仪)用来接收子组件保存的图片名称以及之前保存的图片 将名字push进去
				fileItem : [],
				fileName: 'YSHG',//给子组件的文件名字
				dirName: '',//保存图片的文件夹名字
				file : [],//图片Base64数据
				checkImage : [],//选中的文件暂存数组
				creamVisible : false,
				photoVisible : false,
				type : 0,
				fileSize : 0,
				slide1 : [],
				yshgZbid:'',
				//

			};
		},

		components:{
			//YSZLB,
			'DocCamera' : DocCamera,
		},
		methods: {
			//查询基本数据
			getYszl() {
				let params = {
					id:this.$route.query.tow1.id,
				}
				getyszl(params).then((res) => {
					if (!res.data.fjxx[0]){
						this.tableData = '';
					}else {
						this.tableData = res.data.fjxx;
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
			init(){//初始化参数
				this.fileSize = this.fileItem.length;
				let JSONstr = sessionStorage.getItem("user");
				let JSON = eval("("+JSONstr+")");
				this.dirName = JSON.username;//文件夹名字
			},
			showMsg:function(data){//存储子组件保存的图片名称
				this.fileSize = this.fileSize + 1;
				console.log(data);
				//接口预留位置  data为图片名称 将图片名称保存到数据库
				let params = {zbid:this.zbid,dirName:this.dirName,fileName:data};
				saveImageToDb(params).then((res) => {
					this.fileItem.push(res.path);
					this.getYszl();
				});

			},
			showImageList(){//显示图片列表
				this.file = [];
				let params = {fileItem: this.fileItem.join(",")}
				showImage(params).then((res) => {
					if (res.data != null) {
						let result = res.data.row;
						for (let i = 0; i < result.length; i++) {
							let item = {};
							item.name = this.fileItem[i];
							item.msrc = item.src = "data:image/jpeg;base64," + result[i];
							this.file.push(item);
						}
					}
					this.creamVisible = false;
					this.photoVisible = true;
				})
			},
			openVisible(){
				this.creamVisible = true;
			},
			addFile(file, fileList) {
				this.fileList = fileList;
				//限制上传文件为5M
				let sizeIsSatisfy = file.size < 5 * 1024 * 1024 ? true : false;
				if (!sizeIsSatisfy) {
					this.fileSizeIsSatisfy = false;
				}
			},
			insert() {
				if (this.fileList != null) {
					for (let i = 0; i < this.fileList.length; i++) {
						let file = this.fileList[i];
						let formData = new FormData();
						//传值自己改
						formData.append('multipartFiles', file.raw);
						formData.append('filename', file.name);
						formData.append('zbid',this.zbid);
						formData.append('xmid',this.xmid);
						formData.append('xmbh',this.xmbh);

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
			//获取拍摄照片数量
			getPsTotal(){

				let timer = setTimeout(() => {
					let params={
						zbid:this.zbid
					}
					getPsTotals(params).then((res) => {
						this.fileSize=res.data.ysfjTotal;
						for (let i = 0; i < res.data.ysfjTotal; i++){
							this.fileItem.push(res.data.ysfjFjlj[i].fjlj)
						}
						//this.fileItem=res.data.ysfjFjlj;
					})
				},1000);


			},
			showImage(){//预览图片
				this.slide1 = [];
				let params = {fileItem: this.fileItem.join(",")}
				showImage(params).then((res) => {
					if (res.data != null) {
						let result = res.data.row;
						for (let i = 0; i < result.length; i++) {
							let img = new Image();
							img.src = "data:image/jpeg;base64," + result[i];
							let item = {};
							item.msrc = item.src = "data:image/jpeg;base64," + result[i];
							item.h = img.height;
							item.w = img.width;
							this.slide1.push(item);
						}
						this.$preview.openList(0, this.slide1);
					}
				})
			},
			deleteImage(){
				let name = this.checkImage;
				for(let i =0;i<name.length;i++){

					for(let j =0;j<this.fileItem.length;j++){
						if(name[i] == this.fileItem[j]){
							this.file.splice(j,1);
							this.fileItem.splice(j,1);
							//接口预留位置  this.fileItem[j]为图片名称 将删除的图片名称传入接口，数据库中删除图片
							let params={
								zbid:this.zbid,
								fjlj:name[i]
							}
							deleteImage(params).then((res) => {

								if (res.data == "ok"){
									this.$message({
										message: '删除成功!',
										type: 'success'
									});
								}else if (res.data == "error"){
									this.$message({
										message: '删除失败!',
										type: 'warning'
									});
								}
								this.getYszl();
							});
							j = j - 1;

						}
					}
				}
				this.checkImage = [];
				this.fileSize = this.fileItem.length;
			},
			start(el){
				if(el=='bStartRec(d:\\test.avi)')
				{
					timer = window.setInterval("TimeGo()",1000);
				}
				if(el=='bStopRec')
				{
					window.clearInterval(timer);
					TotalTimelenth=0;
					alert("录屏结束");
				}
				webSocket.send(el);
				return false;
			}

		},

		mounted(){
			this.getPsTotal();
			this.init();
			this.getYszl();
		}
	};
</script>

<style></style>
