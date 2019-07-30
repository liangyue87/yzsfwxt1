<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24">
				<!--项目基本信息模板-->
				<XMJBXX></XMJBXX>
				<!--附件资料-->
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">受理资料</div>
					</div>
					<!--<el-form>-->
						<!--<el-form-item>-->
							<!--<el-col :span="8" v-model="zllx_data" v-for="(item,index) in zllx_data" class="col" :key="index">-->
								<!--<el-col :span="12" style="text-align: right;margin-right: 10px">-->
									<!--<span>{{item.codename}}</span>-->
								<!--</el-col>-->

								<!--<el-button  size="small" type="primary" @click="fjzlPreview_vue(item.codevalue,item.parentid)"><i style="font-size: 14px" class="iconfont icon-yulan"></i>预览</el-button>-->
								<!--<span>（附件：{{item.count}}张）</span>-->
							<!--</el-col>-->
						<!--</el-form-item>-->
					<!--</el-form>-->
					<el-table :data="zllx_data"  :stripe="true" style="width: 100%;" >
						<el-table-column prop="codename" label="资料类型"  align="center">
						</el-table-column>
						<el-table-column prop="count" label="资料数量"  align="center">
						</el-table-column>
						<el-table-column label="操作" align="center" >
							<template slot-scope="scope" >
								<el-row>
									<el-col :span="24">
										<label title="预览"><i @click="fjzlPreview_vue(scope.$index, scope.row,1)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										<!--<el-button size="small" type="primary" >上传</el-button>-->
										<!--<el-button size="small" type="primary" @click="fjzlPreview_vue(scope.$index, scope.row)">详情</el-button>-->
										<label title="详情"><i @click="toslDetial_vue(scope.$index, scope.row)"  class="iconfont icon-xiangqing" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
									</el-col>
								</el-row>
							</template>
						</el-table-column>
					</el-table>
				</el-card>
				<!--其他资料上传-->
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">设计前资料上传</div>
					</div>
                    <el-table :data="zlrow"  :stripe="true" style="width: 100%;" height="250px" v-loading="upLoading" element-loading-text="文件上传中，请稍后" element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.1)">
                        <el-table-column prop="fjlxEjname" label="资料类型" align="center">
                        </el-table-column>
                        <el-table-column prop="count" label="资料数量" align="center">
                        </el-table-column>
                        <el-table-column label="操作" align="center">
                            <template slot-scope="scope" >
                                <el-row>
                                    <el-col :span="12" style="text-align: right">
										<el-upload
												class="upload-demo"
												ref="upload"
												:multiple=true
												action="#"
												:http-request="submitUpload"
												:on-preview="handlePreview"
												:on-change="handleChange"
												:on-remove="handleRemove"

												:file-list="fileList"
												:show-file-list="false">
											<label title="上传"><i @click="chooseFileClick(scope.$index, scope.row)" class="iconfont icon-shangchuan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										</el-upload>
									</el-col>
									<el-col :span="12" style="text-align: left">
										<label title="详情"><i @click="toDetial_vue(scope.$index, scope.row)"  class="iconfont icon-xiangqing" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										<!--<el-button size="small" type="primary" >上传</el-button>-->
										<!--<el-button size="mini" type="primary" @click="toDetial_vue(scope.$index, scope.row)">详情</el-button>-->
									</el-col>
                                </el-row>
                            </template>
                        </el-table-column>
                    </el-table>

				</el-card>
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">设计时限</div>
					</div>
					<div style="text-align: center" v-if="iscontinue">{{sjsx}}工作日</div>
					<el-form v-if="!iscontinue">
						<el-form-item >
							<el-col :span="3"style="text-align: right">
								<span >含表前管&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="20">
								<el-radio-group v-model="bqgValue" v-for="item in bqgOptions">
									<el-radio :label="item.pzts" @click.native.prevent="bqgclickitem(item.pzts)" style="margin-right: 10px">{{item.pzmc}}</el-radio>
								</el-radio-group>
							</el-col>
						</el-form-item>
						<el-form-item>
							<el-col :span="3" style="text-align: right">
								<span >市政道路&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="20">
								<el-radio-group v-model="szdlValue" v-for="item in szdlOptions">
									<el-radio :label="item.pzts" @click.native.prevent="szdlclickitem(item.pzts)" style="margin-right: 10px">{{item.pzmc}}</el-radio>
								</el-radio-group>
							</el-col>
						</el-form-item>
						<el-form-item>
							<el-col :span="3" style="text-align: right">
								<span >新建小区&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="20">
								<el-radio-group v-model="xjxqValue" v-for="item in xjxqOptions">
									<el-radio :label="item.pzts" @click.native.prevent="xjxqclickitem(item.pzts)" style="margin-right: 10px">{{item.pzmc}}</el-radio>
								</el-radio-group>
							</el-col>
						</el-form-item>
						<el-form-item>
							<el-col :span="3" style="text-align: right">
								<span >出户改造&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="20">
								<el-radio-group v-model="chgzValue" v-for="item in chgzOptions">
									<el-radio :label="item.pzts" @click.native.prevent="chgzclickitem(item.pzts)" style="margin-right: 10px">{{item.pzmc}}</el-radio>
								</el-radio-group>
							</el-col>
						</el-form-item>
						<el-form-item>
							<el-col :span="3" style="text-align: right">
								<span >管廊&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="20">
								<el-radio-group v-model="glValue" v-for="item in glOptions">
									<el-radio :label="item.pzts" @click.native.prevent="glclickitem(item.pzts)" style="margin-right: 10px">{{item.pzmc}}</el-radio>
								</el-radio-group>
							</el-col>
						</el-form-item>
						<!--<el-form-item>-->
							<!--<el-col :span="3" style="text-align: right">-->
								<!--<span >其他&nbsp;&nbsp;</span>-->
							<!--</el-col>-->
							<!--<el-col :span="3">-->
								<!--<el-input v-model="qtValue" placeholder="10"></el-input>-->
							<!--</el-col>-->
							<!--<el-col :span="1"><label>工作日</label></el-col>-->
						<!--</el-form-item>-->
						<el-form-item>
							<el-col :span="3" style="text-align: right">
								<span >共&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="3">
								<el-input v-model="qtValue" readonly></el-input>
							</el-col>
							<el-col :span="1"><label>工作日</label></el-col>
						</el-form-item>
					</el-form>
				</el-card>
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">备注</div>
					</div>
					<el-input
							type="textarea"
							:rows="4"
							v-model="textarea">
					</el-input>
				</el-card>
				<el-row style="text-align: center;margin-top: 20px;">
					<router-link :to="tabUrl">
						<el-button size="mini" type="info" @click="cancel()"><i class="iconfont icon-quxiao"></i>取消</el-button>
						<el-button size="mini" v-if="!iscontinue" type="primary"@click="wtsj_vue()" :loading="loading"><i class="iconfont icon-weituoshejiguanli"></i>委托设计</el-button>
					</router-link>
					<el-button size="mini" type="primary" @click="goxmqj()" style="margin-left: 10px;"><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
				</el-row>
			</el-col>
		</el-row>
        <!--<img class="preview-img" v-for="(item, index) in imgList" :src="item.src" height="100" @click="$preview.open(index, imgList)">-->
		<!--<ImageView :src="Imgsrc"></ImageView>-->
        <el-dialog title="项目资料下载" :visible.sync="dialogTableVisible" :modal-append-to-body="false" style="padding-bottom: 10px" height="400px">
			<bigFileUpload @resultPath="getResultPath" ref="mychild" :xmid="xmid"  v-if="dialogTableVisible&&isdeleteshow"></bigFileUpload>
            <el-table :data="gridData"  :stripe="true" style="padding-bottom: 10px" v-loading="downLoading" element-loading-text="文件下载中，请稍后" element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.1)">
                <el-table-column property="fjmc" label="附件名称" align="center"></el-table-column>
                <el-table-column property="scsj_vue" label="上传时间" align="center"></el-table-column>
                <el-table-column property="scr" label="上传人" align="center"></el-table-column>
                <el-table-column property="address" label="操作" align="center">
                    <template slot-scope="scope">
                        <el-row>
                            <el-col :span="24">
                                <div v-if="scope.row.fjhz!=null&&(scope.row.fjhz.toLowerCase()=='png'||scope.row.fjhz.toLowerCase()=='jpg')">
                                    <el-col :span="24">
										<label title="删除" v-if="isdeleteshow"><i class="iconfont delete" @click="deleteFj_vue(scope.$index, scope.row)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										<label title="预览"><i class="iconfont icon-yulan" @click="downloadFile_vue(scope.$index, scope.row,1)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										<label title="下载"><i @click="downloadFile_vue(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                    </el-col>
                                </div>
                                <div v-else>
									<el-col :span="24">
										<label title="删除" v-if="isdeleteshow"><i @click="deleteFj_vue(scope.$index, scope.row)" class="iconfont delete" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
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

<style>
	.table_td{
		color:#606266;
		font-size: 14px;
		padding:3px;
		text-align: center;
	}
	a{
		text-decoration:none;
	}


</style>
<script>

	import XMJBXX from '@/views/template/xmjbxx.vue';
	import bigFileUpload from '@/views/template/bigFileUpload.vue';
	import ImageView from '../../common/components/form/img-preview/ImageView.vue'
	import { initTble,getQtzl,deleteFj,wtsj,importFile,download_sjst,fjzlPreview,submitShow,toDetial,initSjsx,rwtxfsdx,sjqzl} from '../../api/sjst';
	let zlType="";
	export default {
		components:{
			ImageView
		},
		data() {

			return {
				upLoading:false,
				downLoading:false,
				isdeleteshow:false,
				sjsx:0,
				qtValue:10,
				bqgValue:0,
				szdlValue:0,
				xjxqValue:0,
				chgzValue:0,
				glValue:0,
				glOptions:[],
				xjxqOptions:[],
				szdlOptions:[],
				bqgOptions:[],
				chgzOptions:[],
				zlType:"",
                slide1:[],
				textarea:"",
				//ImgFlag:false,
				loading: false,
                dialogTableVisible:false,
                gridData:[],
                zlrow:[],
				fileList:[],
				zllx_data:[],
				xmid:this.$route.query.xmid,
				iscontinue:false,
				tabUrl:'/fqwtsj?xmid='+this.$route.query.xmid,
			}
		},
		methods: {
			//大文件上传
			getResultPath(result) {
				this.submitUpload_vue(result.name, result.path)
			},
			// 上传
			submitUpload_vue(name, path) {
				let params = {
					name: name,
					path: path,
					xmid: this.xmid,
					zlType: this.zlType
				}
				sjqzl(params).then((res) => {
					let msg = res.data.msg;
					if(msg == '上传成功'){
						this.$message({
							message: '上传成功',
							type: 'success'
						});
					}else{
						this.$message.error('上传失败！');
					}
					this.getQtzl_vue();
					let row = {
						id: this.zlType,
						parentid: 'yhtgzl'
					}
					this.toDetial_vue("", row);
				})
			},
			//跳转至项目全景
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
			},
			toslDetial_vue(index, row){
				this.isdeleteshow=false;
				this.dialogTableVisible = true;
				let params = {
					ejfjlx: row.codevalue,
					yjfjlx: row.parentid,
					xmid: this.xmid
				}
				toDetial(params).then((res) => {
					if (res.data != null && res.data.row != null) {
						this.gridData = res.data.row;
					}
				})
			},
			initSjsx_vue(){
				let params={
					xmid:this.xmid
				}
				initSjsx(params).then((res)=>{
					this.xjxqOptions=res.data.xjxqList;
					this.szdlOptions=res.data.szdlList;
					this.bqgOptions=res.data.bqgList;
					this.chgzOptions=res.data.chgzList;
					this.glOptions=res.data.glList;
					this.sjsx=res.data.sjsx;
					this.textarea=res.data.bz;
				})
			},
			glclickitem (e) {
				e === this.glValue ? this.glValue = 0 : this.glValue = e
				this.qtValue = this.bqgValue+this.szdlValue+this.xjxqValue+this.glValue+this.chgzValue;
				if(this.qtValue==0){
					this.qtValue=10;
				}
			},
			bqgclickitem (e) {
				e === this.bqgValue ? this.bqgValue = 0 : this.bqgValue = e
				this.qtValue = this.bqgValue+this.szdlValue+this.xjxqValue+this.glValue+this.chgzValue;
				if(this.qtValue==0){
					this.qtValue=10;
				}
			},
			chgzclickitem (e) {
				e === this.chgzValue ? this.chgzValue = 0 : this.chgzValue = e
				this.qtValue = this.bqgValue+this.szdlValue+this.xjxqValue+this.glValue+this.chgzValue;
				if(this.qtValue==0){
					this.qtValue=10;
				}
			},

			szdlclickitem (e) {
				e === this.szdlValue ? this.szdlValue = 0 : this.szdlValue = e
				this.qtValue = this.bqgValue+this.szdlValue+this.xjxqValue+this.glValue+this.chgzValue;
				if(this.qtValue==0){
					this.qtValue=10;
				}
			},
			xjxqclickitem (e) {
				e === this.xjxqValue ? this.xjxqValue = 0 : this.xjxqValue = e
				this.qtValue = this.bqgValue+this.szdlValue+this.xjxqValue+this.glValue+this.chgzValue;
				if(this.qtValue==0){
					this.qtValue=10;
				}
			},
			initTble_vue(){
				let params = {
					xmid: this.xmid,
				};
				initTble(params).then((res) => {
					this.zllx_data = res.data.row;
				});
			},
			//附件预览
			fjzlPreview_vue(index,row){
				this.slide1=[];
				let params={
					xmid:this.xmid,
					ejfjlx:row.codevalue,
					yjfjlx:row.parentid
				}
				fjzlPreview(params).then((res)=>{
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
			handleChange(file,fileList){
				this.fileList = fileList;
			},
			handleRemove(file, fileList) {
			},

			handlePreview(file) {
			},
			chooseFileClick(index,row){
				zlType = row.id;
			},
			submitUpload(){
				this.upLoading=true;
				if(this.fileList.length>0){
					// for(let i = 0;i<this.fileList.length;i++){
						this.zlType=zlType;
						let file = this.fileList[this.fileList.length-1];
						let formData = new FormData();
						formData.append("multipartFiles",file.raw);
						formData.append("filename",file.name);
						formData.append("xmid",this.xmid);
						formData.append("zlType",this.zlType);
						importFile(formData).then((res)=>{
							let msg = res.data.msg;
							if(msg == '上传成功'){
								this.$message({
									message: '上传成功',
									type: 'success'
								});
							}else{
								this.$message.error('上传失败！');
							}
							this.fileList=[];
							this.getQtzl_vue();
							this.upLoading=false;
						})
					// }
				}
			},
			getQtzl_vue(){
				let params = {
					xmid: this.xmid,
				};
				getQtzl(params).then((res) => {
					this.zlrow = res.data.row;
				});
			},
            toDetial_vue(index, row) {
				this.zlType=row.id;
				this.isdeleteshow=true;
                this.dialogTableVisible = true;
                let params = {
                    ejfjlx: row.id,
                    yjfjlx: row.parentid,
                    xmid: this.xmid
                }
                toDetial(params).then((res) => {
                    if (res.data != null && res.data.row != null) {
                        this.gridData = res.data.row;
                    }
                })
            },
			deleteFj_vue(index,row){
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {
						id: row.id,
					};
					deleteFj(params).then((res) => {
						let msg = res.data;
						if(msg){
							this.$message({
								message: '删除成功',
								type: 'success'
							});
							this.getQtzl_vue();
							let detialRow={
                                id:row.fjlxEj,
                                parentid:row.fjlxYj
                            }
                            this.toDetial_vue("", detialRow);
						}else{
							this.$message.error('删除失败！');
						}
					});
				})
			},
			downloadFile_vue(index,row,flag){
				this.downLoading=true;
				let formData = new FormData()
				formData.append("path",row.fjlj);
				// let params = {
				// 	path:row.fjlj
				// }
				// let instance = axios.create({
				// 	baseURL:'http://localhost:8080',
				// 	headers:{
				// 		'Content-Type': 'application/json; application/octet-stream'
				// 	},
				// 	responseType: 'blob',
				//
				// });
				download_sjst(formData).then((res)=>{
					if(flag==1){
						this.slide1=[];
						let reader = new FileReader()
						reader.onload = (e) => {
							let img = new Image();
							img.src =e.target.result;
							let item = {};
							item.msrc = item.src = e.target.result;
							// this.Imgsrc = e.target.result;
							item.h = img.height;
							item.w = img.width;
							this.slide1.push(item);
							this.$preview.openList(0, this.slide1);
							//this.msgFlag = true;
							//this.prop.src = this.Imgsrc
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
					this.downLoading=false;
				})
				// instance.post('/sjstController/download',formData).then((res) => { // 处理返回的文件流
				//
				//
				//
				// })
			},

            handleClose(){},
            cancel(){
				this.tabUrl='/project'
				this.$routerTab.close();
				this.$routerTab.refresh('/project');
			},
            wtsj_vue(){
				// if(this.bqgValue==""&&this.szdlValue==""&&this.xjxqValue==""){
				// 	if(this.qtValue==""){
				// 		this.sjsx=10
				// 	}else{
				// 		const reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
				// 		if (!reg.test(this.qtValue) && !this.qtValue == '') {
				// 			this.$message({
				// 				message: '设计时限中其他需填写数字，最多2位小数',
				// 				type: 'error'
				// 			});
				// 			return;
				// 		}
				// 		this.sjsx=this.qtValue
				// 	}
				// }else{
				// 	this.sjsx = this.bqgValue+this.szdlValue+this.xjxqValue
				// }

				this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.sjsx = this.qtValue;
					this.loading=true;
					let params={
						"xmid":this.xmid,
						"activitiId":30001,
						"bz":this.textarea,
						"type":"sjst",
						"sjsx":this.sjsx
					}
					wtsj(params).then((res) => {
						this.loading=false;
						if(res.data==1){
							this.$message({
								message: '提交成功',
								type: 'success'
							});
							let param={
								"xmid":this.xmid,
								"activitiId":30001,
								"direction":0
							}
							rwtxfsdx(param).then((res)=>{})
							this.tabUrl='/project'
							this.$routerTab.close();
							this.$routerTab.refresh('/project');
						}else if(res.data==-1){
							this.$message({
								message: '该项目已发起委托设计,提交失败',
								type: 'error'
							});
						}else if(res.data==-2){
							this.$message({
								message: '暂无任务办理人，请联系管理员',
								type: 'error'
							});
						}else{
							this.$message({
								message: '提交失败',
								type: 'error'
							});
						}
					})
				})

			},
			submitShow_vue(){
				let params={
					xmid:this.xmid
				}
				submitShow(params).then((res)=>{
					this.iscontinue=res.data;
				})
			},
		},

		mounted() {
			this.submitShow_vue();
			this.initTble_vue();
			this.getQtzl_vue();
			this.initSjsx_vue();
		},
		components: {
			'XMJBXX': XMJBXX , //将别名demo 变成 组件 Demo
			'bigFileUpload': bigFileUpload,
		},


	}

</script>
