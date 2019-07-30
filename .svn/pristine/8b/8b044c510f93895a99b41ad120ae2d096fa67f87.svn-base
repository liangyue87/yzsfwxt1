<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<hhldsx :xmid="this.xmid" :activityid="'40081'" :type="1"></hhldsx>
			<el-col :span="24">
				<el-row style="margin-top: 15px;">
					<el-col :span="24">
						<!--项目基本信息模板-->
						<XMJBXX></XMJBXX>
					</el-col>
				</el-row>
			</el-col>
			<el-col :span="24" style="text-align: center;margin-top: 10px;"></el-col>
		</el-row>
		
		<el-row>
			<el-col>
				<el-card class="box-card">
					<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
						<span>预算汇总表</span>
						<!--<el-button style="float: right; padding: 3px 0" type="text" @click="ysxx">选择预算项</el-button>-->
					</div>
					<div class="text item">
						<YSJEZH></YSJEZH>
					</div>
					<div>
						<table>
							<tr>
								<td style="width: 80px">
									<span><b>预算说明：</b></span>
								</td>
								<td>
									<!--<el-input v-model="this.ysrybz" readonly="true" style="width: 600px;"></el-input>-->
									<span>{{this.ysrybz}}</span>
								</td>
							</tr>
						</table>
					</div>
				</el-card>
			</el-col>
			<el-col :span="24" style="text-align: center;margin-top: 10px;"></el-col>
		</el-row>

		<el-row >
			<el-card  class="box-card"  style="margin-top: 15px;">
				<div slot="header" class="clearfix">
					<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目配置</div>
				</div>
				<div class="text item">
					<el-tabs v-model="activeName" type="card" >
						<el-tab-pane label="项目范围配置" name="1"><xmfwpz :qb="0" :isxmtc="'2'" v-if="activeName=='1'"></xmfwpz></el-tab-pane>
						<el-tab-pane label="物料清单配置" name="2"><wlqdpz :qb="0" :xmid="xmid" v-if="activeName=='2'"></wlqdpz></el-tab-pane>
						<el-tab-pane label="工商表配置" name="3"><dbpz :qb="0" :isxmtc="'2'" :xmid="xmid" v-if="activeName=='3'"></dbpz></el-tab-pane>
						<el-tab-pane label="五级计量表配置" name="4"><wjjlbpz :qb="0" :isxmtc="'2'" :xmid="xmid" v-if="activeName=='4'"></wjjlbpz></el-tab-pane>
						<el-tab-pane label="居民表配置" name="5"><hbpz :qb="0" :isxmtc="'2'" :xmid="xmid" v-if="activeName=='5'"></hbpz></el-tab-pane>
					</el-tabs>
				</div>

			</el-card>
			<el-card class="box-card"  style="margin-top: 15px;">
				<div slot="header" class="clearfix">
					<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">审图成果</div>
				</div>
				<div class="text item">
					<el-row>
						<el-table :data="Data"style="width: 100%" :stripe="true" :show-header="false">
							<el-table-column
									prop="name"
									label="审图类型"
									align="center">
							</el-table-column>
							<el-table-column
									prop="psyl"
									label="拍摄预览"
									align="center">
								<template slot-scope="scope">
									<label title="预览"><i @click="stPreview_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
									<span>(附件：{{scope.row.pictureNumber}}张)</span>
								</template>
							</el-table-column>
							<el-table-column
									prop="glxz"
									label="管理下载"
									align="center">
								<template slot-scope="scope">
									<label title="管理"><i @click="stManage_vue(scope.$index, scope.row,'stcg')" class="iconfont icon-renyuanguanli" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
									<!--<a :href="'#'" @click="stDownLoad_vue(scope.$index, scope.row)">下载</a>-->
									<span>(附件：{{scope.row.fjNumber}}张)</span>
								</template>
							</el-table-column>
						</el-table>
					</el-row>
					<el-row class="row"></el-row>
				</div>
			</el-card>
			<el-card class="box-card"  style="margin-top: 15px;">
				<div slot="header" class="clearfix">
					<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">设计成果</div>
				</div>
				<div class="text item">
					<el-row>
						<el-table :data="sjData"style="width: 100%" :stripe="true" >
							<el-table-column
									prop="name"
									label="设计成果类别"
									align="center">
							</el-table-column>
							<el-table-column
									prop="psyl"
									label="图纸目录"
									align="center">
								<template slot-scope="scope">
									<label title="预览"><i @click="stPreview_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
									<span>(附件：{{scope.row.pictureNumber}}张)</span>
								</template>
							</el-table-column>
							<el-table-column
									prop="glxz"
									label="给施资料"
									align="center">
								<template slot-scope="scope">
									<label title="管理"><i @click="stManage_vue(scope.$index, scope.row,'sjcg')" class="iconfont icon-renyuanguanli" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
									<span>(附件：{{scope.row.fjNumber}}张)</span>
								</template>
							</el-table-column>
						</el-table>
					</el-row>
					<el-row class="row"></el-row>
				</div>
			</el-card>

			<el-card style="margin-top: 10px">
				<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;"><span>预算资料</span></div>
				<div class="text item" >
					<el-table :data="Early_stage" style="width: 100%">
						<el-table-column property="fjmc" label="资料名" width="280" align="center"></el-table-column>
						<el-table-column property="fjhz" label="资料类型" width="180" align="center"></el-table-column>
						<el-table-column property="scsj" label="上传时间" width="200" align="center"></el-table-column>
						<el-table-column property="username" label="上传人" width="200" align="center"></el-table-column>
						<el-table-column label="操作" width="200" align="center">
							<template slot-scope="scope">
								<label title="预览" v-if="scope.row.fjhz!=null&&(scope.row.fjhz.toLowerCase()=='png'||scope.row.fjhz.toLowerCase()=='jpg')">
									<i class="iconfont icon-yulan" @click="downloadFile_qqyszl(scope.$index, scope.Early_stage,1)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
								</label>
								<label title="下载">
									<i class="iconfont icon-xiazai" @click="downloadFile_qqyszl(scope.$index, scope.Early_stage,2)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
								</label>
							</template>
						</el-table-column>
					</el-table>

				</div>
			</el-card>

			<el-dialog  :title="title" :visible.sync="stcgDialogFormVisible" width="50%">
				<el-table :data="stcgxqData"style="width: 100%" :stripe="true" >
					<el-table-column
							prop="fjmc"
							label="附件名称"
							align="center">
					</el-table-column>
					<el-table-column
							prop="fjhz"
							label="附件后缀"
							align="center">
					</el-table-column>
					<el-table-column
							prop="scr"
							label="上传人"
							align="center">
					</el-table-column>
					<el-table-column
							prop="scsj"
							label="上传时间"
							align="center">
					</el-table-column>
					<el-table-column
							prop="cz"
							label="操作"
							align="center"
							width="200">
						<template slot-scope="scope">
							<label title="下载"><i @click="stDownLoad_vue(scope.$index, scope.row)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
						</template>
					</el-table-column>

				</el-table>

			</el-dialog>
		</el-row>
		<YSCG :zbid="Ysfrom.zbid" :type="40008"></YSCG>

		<!--<el-row>
			<el-col :span="24">
				<el-row style="margin-top: 15px;">
					<el-col :span="24">
						<el-card class="box-card">
							<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
								<span>预算资料下载</span>
							</div>
							<div class="text item">
								<YSZLB :zbid='this.Ysfrom.zbid' :xmid="this.xmid"></YSZLB>
							</div>
						</el-card>
					</el-col>
				</el-row>
			</el-col>
			<el-col :span="24" style="text-align: center;margin-top: 10px;"></el-col>
		</el-row>-->
		<el-row>
			<el-col :span="24">
				<el-card class="box-card">
					<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
						<span>领导审批</span>
					</div>
					<el-form :model="Ysfrom" label-width="120px" ref="xmxxForm" :inline="true">
						<div class="text item"><el-input type="textarea" :rows="2" autosize placeholder="请输入内容" v-model="Ysfrom.shyj" :readonly="true"></el-input></div>
					</el-form>
				</el-card>
			</el-col>
		</el-row>
		<el-row>
			<el-col>
				<el-card class="box-card" style="margin-top: 20px">
					<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;"><span>用户确认凭证上传</span></div>
					<el-upload
							class="upload-demo"
							action="#"
							:on-preview="handlePreview"
							:on-remove="handleRemove"
							:on-change="handleChange"
							:file-list="fileList"
							:http-request="importYhpz"
							:show-file-list="false"
							:auto-upload="true">
						<el-button slot="trigger" size="mini" type="success" style="margin-bottom: 10px"><i class="iconfont icon-shangchuan_f"></i>上传</el-button>
					</el-upload>
					<div class="text item" id="yszy">
						<el-table :data="fjData" style="width: 100%">
							<el-table-column property="fjmc" label="资料名" width="280" align="center"></el-table-column>
							<el-table-column property="fjhz" label="资料类型" width="180" align="center"></el-table-column>
							<el-table-column property="scsj" label="上传时间" width="200" align="center"></el-table-column>
							<el-table-column property="username" label="上传人" width="200" align="center"></el-table-column>
							<el-table-column label="操作" width="200" align="center">
								<template slot-scope="scope">
									<label title="预览" v-if="scope.row.fjhz!=null&&(scope.row.fjhz.toLowerCase()=='png'||scope.row.fjhz.toLowerCase()=='jpg')">
										<i class="iconfont icon-yulan" @click="downloadFile_vue(scope.$index, scope.fjData,1)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
									</label>
									<label title="下载">
										<i class="iconfont icon-xiazai" @click="downloadFile_vue(scope.$index, scope.fjData,2)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
									</label>
									<label title="删除">
										<i class="iconfont delete" @click="deleteYhqrfj(scope.$index, scope.row)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
									</label>
								</template>
							</el-table-column>
						</el-table>

					</div>
				</el-card>
			</el-col>
		</el-row>
		<el-row>
			<el-col :span="24">
				<el-card class="box-card">
					<el-col :span="8">&nbsp;</el-col>
					<router-link :to="tabUrl">
						<el-button size="mini" type="info" @click="toXckcrwcj()"><i class="iconfont icon-quxiao"></i>取消</el-button>
					</router-link>
					<el-button size="mini" type="primary" @click="shSuccess()" :loading="sumbitLoading" style="margin-left: 10px"><i class="iconfont icon-queren"></i>签收</el-button>
					<el-button size="mini" type="primary" @click="goxmqj()" :loading="submitLoading"><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
					<!--<el-button size="mini" type="primary" @click="goxmzl()" :loading="submitLoading"><i class="iconfont icon-ziliaoquanjiing_"></i>项目资料</el-button>-->
				</el-card>
			</el-col>
		</el-row>

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
<script>
import XMJBXX from '@/views/template/xmjbxx.vue';
import hhldsx from '@/views/template/hhldsx.vue';
import xmfwpz from '@/views/template/xmfwpz.vue';
import wlqdpz from '@/views/template/wlqdpz.vue';
import dbpz from '@/views/template/dbpz.vue';
import hbpz from '@/views/template/hbpz.vue';
import wjjlbpz from '@/views/template/wjjlbpz.vue';
import JEJS from '../../components/Jejs.vue';
import YSZLSC from '../../components/yswt/yszlUpload.vue';
import YSCG from '../../components/yswt/yscg.vue';
import YSZLXZ from '../../components/yswt/yszlDownload.vue';
import YSJEZH from '../../components/yswt/Ysjezh.vue';
import YSZLB from '../../components/yswt/yszlb.vue'
import { initStcgTable,stManage,stDelete,stPreview,stcgsc,scsjcgSubmit,sjcgscBack,download_sjst,goSjrwfp} from '../../api/sjst';
import ElImage from "element-ui/packages/image/src/main";
import util from '../../common/js/util';
import { getsjstrzwid,getYssm, getTable, startDelegate, getYsx, Qs,updateQszt,importYhpz,getYszlYhpz,downloads,deleteYhqrfj,getEarly_stage } from '../../api/Yswt';

export default {
	data() {
		return {
			tabUrl:'',
			stcgDialogFormVisible:false,
			stcgxqData:[],
			sjstrzwid:'',//设计审图任务id
			fileName: 'sjcgsc',//给子组件的文件名字
			dirName:"",
			Data:[],
			sjData:[],
			activeName:'1',
			formData: {
				domains: [{}]
			},
			ysbz: {
				zje: '',
				ysrwb: '',
				ysbzbz: ''
			},
			checked: true,
			fileList:[],//文件上传数组
			zje: 0,
			yshz: [],
			ysrybz:'',
			yshzFrom: {
				mc: '',
				je: '',
				bz: ''
			},
			Early_stage:[],
			xmid:this.$route.query.xmid,//项目id
			Ysjezh:[],//预算金额总汇
			dialogTableVisible: false,
			sx: 'primary',
			textarea: '', //备注
			YsxData: [], //预算项的数据
			Ysfrom: this.$route.query.tow1, //上个页面传来的项目信息
			kcsj: '', //预约勘查时间
			tableData: [],
			fjData:[],
			downLoad:false,
			check_value: {
				sjy_value: true,
				sgdw_value: true,
				ysk_value: true,
				sjydc_value: true,
				sgdwdc_value: true,
				yskdc_value: true
			},
			sumbitLoading:false,
		};
	},
	watch: {
		// 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
		$route: 'getParams'
	},
	methods: {
		//跳转至项目全景
		goxmqj(){
			this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})

		},
		//跳转至项目资料
		goxmzl(){
			this.$router.push({path:'/lookZl', query:{ xmid:this.xmid }})
		},
		//初始化参数
		init(){
			let JSONstr = sessionStorage.getItem("user");
			let JSON = eval("("+JSONstr+")");
			//console.log(JSON);
			//console.log(JSON.username);
			this.dirName = JSON.username;//文件夹名字
		},
		stPreview_vue(index, row) {
			this.slide1 = [];
			let path = [];
			this.fjlxYj = row.parentid;
			this.fjlxEj = row.id;
			let params = {
				xmid: this.xmid,
				sjstrzwid: this.sjstrzwid,
				fjlxEj: row.id,
				fjlxYj: row.parentid
			}
			stPreview(params).then((res) => {
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
		//下载单个文件
		stDownLoad_vue(index, row) {
			this.downLoad=true;
			let formData = new FormData();
			formData.append("path", row.fjlj);
			formData.append("type", "1");
			download_sjst(formData).then((res)=>{
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
					this.downLoad=false;
				} else { // IE10+下载
					navigator.msSaveBlob(blob, fileName);
					this.downLoad=false;
				}
			})
		},
		//打开详情
		stManage_vue(index, row, type) {

			this.fjlxYj = row.parentid;
			this.fjlxEj = row.id;
			let params = {
				xmid: this.xmid,
				sjstrzwid: this.sjstrzwid,
				fjlxEj: row.id,
				fjlxYj: row.parentid
			}
			stManage(params).then((res) => {
				if (res.data != null) {
					this.stcgxqData = res.data.row;
					if (type == 'stcg') {
						this.title = "审图成果";
					} else {
						this.title = "设计成果";
					}
					this.type = type;

					this.stcgDialogFormVisible = true;
					setTimeout(()=>{
						this.$refs.mychild.childClick();
					},10)
				}
			})
		},
		//初始化审图成果列表
		initStcgTable_vue(type) {
			let params = {
				xmid: this.xmid,
				sjstrzwid: this.sjstrzwid,
				type: type
			}
			initStcgTable(params).then((res) => {
				if (res.data != null) {
					if (type == 'stcg') {
						this.Data = res.data.row;
					}
					if (type == 'sjcg') {
						this.sjData = res.data.row;
					}
				}
			})
		},
		getsjstrzwid(){
			let params = {
				xmid:this.xmid,
				activityId:'30003'
			}
			getsjstrzwid(params).then((res) =>{
				if(res != "error"){
					//this.$router.push({path: '/scsjcg', query: {xmid : row.ID,activityId:'30003',sjstrzwid:row.REFID}})
					if(res.sjstrzwid.length>0){
						this.sjstrzwid = res.sjstrzwid[0].refid;
					}
					//初始化审图成果
					this.initStcgTable_vue('stcg');
					//初始化设计成果
					this.initStcgTable_vue('sjcg');

				}
			})
		},
		updateQszt(){
			let params = {
				xmid : this.xmid,
				activityId:'40081'
			}
			updateQszt(params).then(res =>{

			});
		},
		//签收
		shSuccess(){
			this.$confirm('确认签收?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {

				let params ={
					xmid : this.Ysfrom.id,
				}
				//签收
				this.sumbitLoading=true;
				Qs(params).then(res => {
					this.sumbitLoading=false;
					this.$message({
						type: 'success',
						message: '已签收!'
					});
				});
				this.$router.push({ path: '/Backlog', query: {} });
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');
				this.$routerTab.refresh('/MyWork');//刷新我的工作
				/*this.$store.commit("delete_tabs", '/Qsxq');
				this.$router.push({ path: '/Backlog', query: {} });*/
			}).catch(() => {
					this.$message({
						type: 'info',
						message: '操作已取消!'
					});
				});
		},
		//预算项列表
		ysxx() {
			let params = {};
			//this.dialogTableVisible = !this.dialogTableVisible;//弹出预算项框
			getYsx(params).then(res => {
				this.YsxData = res.data.ysxx;
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
		//上传
		importYhpz() {
			if (this.fileList != null) {
				for (let i = 0; i < this.fileList.length; i++) {
					let file = this.fileList[i];
					let formData = new FormData();
					//传值自己改
					formData.append('multipartFiles', file.raw);
					formData.append('filename', file.name);
					formData.append('ysrwzbid', this.Ysfrom.zbid);
					formData.append('xmid',this.xmid);
					formData.append('xmbh',this.Ysfrom.xmbh);

					//接口自己改
					importYhpz(formData).then((res) => {
						let msg = res.msg;
						if (msg == '上传成功') {
							this.$message({
								message: '上传成功',
								type: 'success'
							});
							this.getYszlYhpz();
						} else {
							this.$message.error('上传失败！');
						}
						this.fileList = [];
						this.getYszlYhpz();
					});
				}
			}
		},
		//查询上传的数据
		getYszlYhpz() {
			let params = {
				ysrwzbid:this.Ysfrom.zbid,
			}
			getYszlYhpz(params).then((res) => {
				if (!res.YszlYhpz[0]){
					this.fjData = '';
				}else {
					this.fjData = res.YszlYhpz;
				}

			});
		},
		downloadFile_qqyszl(index,Early_stage,flag){
			Early_stage=this.Early_stage[index];
			let formData = new FormData();
			formData.append("path",Early_stage.fjlj);

			downloads(formData).then((res) => { // 处理返回的文件流
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
					const fileName = Early_stage.fjmc;

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
		downloadFile_vue(index,fjData,flag){
			fjData=this.fjData[index];
			let formData = new FormData();
			formData.append("path",fjData.fjlj);

			downloads(formData).then((res) => { // 处理返回的文件流
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
					const fileName = fjData.fjmc;

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
		//删除用户凭证上传（列表信息）
		deleteYhqrfj(index,row) {
			this.$confirm('确认删除该附件？, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'error'
			}).then(() => {
				let params = {
					yhqrfjid:row.yhqrfjid
				};
				deleteYhqrfj(params).then((res) => {
					
					this.getYszlYhpz();
				})
			}).catch(() => {
			});

		},
		//审核通过
		shenghe() {
			this.textarea = '已审核,发起委托预算。';
			this.sx = '';
		},
		//获取项目范围数据
		getTableData() {
			this.tableData.push({cjr: "-", count: "0", lb: "楼栋", strCjsj: "-",xmid : "0"});
			this.tableData.push({cjr: "-", count: "0", lb: "庭院", strCjsj: "-",xmid : "0"});
			this.tableData.push({cjr: "-", count: "0", lb: "水箱", strCjsj: "-",xmid : "0"});
			this.tableData.push({cjr: "-", count: "0", lb: "地下室", strCjsj: "-",xmid : "0"});
			this.tableData.push({cjr: "-", count: "0", lb: "泵房", strCjsj: "-",xmid : "0"});
			let params = {
				id: this.Ysfrom.id,
			};
			getTable(params).then(res => {
				let num = res.xmfw.length;
				for (let i=0;i<this.tableData.length;i++){
					for (let j=0;j<num;j++){
						if (this.tableData[i].lb == res.xmfw[j].lb){
							this.tableData[i].cjr = res.xmfw[j].cjr;
							this.tableData[i].count = res.xmfw[j].count;
							this.tableData[i].strCjsj = res.xmfw[j].strCjsj;
							this.tableData[i].xmid = res.xmfw[j].xmid;
						}
					}
				}
			});
		},
		//接收上个页面的数据
		getParams() {
			this.getYssm();
			this.getYszlYhpz();
		},
		getYssm(){
			let params = {
				zbid: this.Ysfrom.zbid,
			};
			getYssm(params).then(res => {
				if (typeof(res.ysRwzb[0].ysrybz) != "undefined") {
					this.ysrybz = res.ysRwzb[0].ysrybz;
				}
			});

		},
		//跳转页面Xckcrwcj 状态
		toXckcrwcj() {
			this.tabUrl = '/Backlog';
			this.$routerTab.close();
			this.$routerTab.refresh('/Backlog');
			this.$routerTab.refresh('/MyWork');//刷新我的工作
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
		//发起联合勘查
		saveXckcTask() {
			if (this.textarea == '') {
				this.$message({
					type: 'warning',
					message: '点击\‘审核\’后才可以发起!'
				});
			} else {
				this.$confirm('确认发起委派, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				})
					.then(() => {
						let params = {
							xmid: this.Ysfrom.id,
							xmjlbz: this.textarea
						};
						startDelegate(params).then(res => {
							this.$message({
								type: 'success',
								message: '提交成功!'
							});
						});

						this.$router.push({ path: '/YSwt', query: {} });
					})
					.catch(() => {
						this.$message({
							type: 'info',
							message: '取消提交'
						});
					});
			}
		},
		//选中预算项动态渲染
		Selected(row) {
			this.formData = { domains: [{}] };

			// this.yshz = row;
			let list = row;
			for (let i = 0; i < list.length; i++) {
				this.formData.domains.push({
					bz: '',
					value: '',
					bh: list[i].bh,
					label: list[i].mc
				});
			}
		},
		//查询基本数据
		getEarly_stage() {
			let params = {
				xmid:this.xmid,
				zbid:this.Ysfrom.zbid
			}

			getEarly_stage(params).then((res) => {
				if (!res.getEarly_stage[0]){
					this.Early_stage = '';
				}else {
					this.Early_stage = res.getEarly_stage;
				}

			});
		},
	},

	//页面启动调用的方法
	mounted() {
		this.init();
		this.getParams();
		this.getEarly_stage();
		this.getTableData();
		this.ysxx();
		this.getsjstrzwid();
		this.updateQszt();
	},

	components: {
		YSZLXZ,
		YSJEZH,
		YSZLSC,
		YSCG,
		XMJBXX,
		JEJS,
		YSZLB,
		hhldsx,
		'xmfwpz': xmfwpz,
		'wlqdpz': wlqdpz,
		'dbpz': dbpz,
		'hbpz': hbpz,
		'wjjlbpz': wjjlbpz,
	}
};
</script>

<style scoped>
.cd {
	width: 25%;
	padding-left: 50px;
}
</style>
