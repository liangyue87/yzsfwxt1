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
					<div class="text item" style="width: 100%;">
						<el-form style="margin-top: 0px;" >
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
						</el-form>
						<el-button size="mini" type="primary" @click="openAddForm"><i class="iconfont icon-xinzeng"></i>添加</el-button>
						<!--<el-button type="primary" @click="openAddForm" icon="el-icon-plus">添加</el-button>-->
						<el-table :data="kcfkRow" v-loading="listLoading" border style="width: 100%;margin-top: 10px;">
							<el-table-column type="index" label="序号" width="150" align="center">
							</el-table-column>
							<el-table-column property="dcxnr" label="调查内容" width="200" align="center" :formatter="formatDcnr">
							</el-table-column>
							<el-table-column property="dcr" label="调查人" width="200" align="center">
							</el-table-column>
							<el-table-column property="dcrq" label="调查日期" min-width="180" align="center">
							</el-table-column>
							<el-table-column label="操作" width="250" align="center">
								<template slot-scope="scope">
									<label title="编辑" @click="openEditForm(scope.$index, scope.row)">
										<i class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
									<label title="删除" @click="deleteFk(scope.$index, scope.row)">
										<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>

									<!--<el-button size="small" type="warning" @click="openEditForm(scope.$index, scope.row)">编辑</el-button>
									<el-button size="small" type="danger" @click="deleteFk(scope.$index, scope.row)">删除</el-button>-->
								</template>
							</el-table-column>
						</el-table>
						<el-card class="box-card"  style="margin-top: 15px;">
							<div slot="header" class="clearfix">
								<div class="div_title">管线位置示意图附件资料</div>
							</div>
							<div class="text item" style="width: 100%;">
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
										:auto-upload="true"
										style="display: inline-block">
									<el-button slot="trigger" size="mini" type="success" style="margin: 0px 10px 10px 0px;">
										<i class="iconfont icon-shangchuan_f"></i>上传</el-button>
									<!--<el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload" icon="el-icon-upload">上传</el-button>-->
								</el-upload>
								<!--<el-button size="mini" type="success" style="margin: 0px 10px 10px 0px;" @click="downloadData">
									<i class="iconfont icon-xiazai_f"></i>批量下载</el-button>-->
								<el-table :data="fjData" border style="width: 100%;" ref="multipleTable">
									<!--<el-table-column
											type="selection"
											width="55">
									</el-table-column>-->
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
							</div>
						</el-card>
					</div>
					<el-form style="margin-top: 25px;" :model="fkpj" ref="fkpj" :rules="rules">
						<el-form-item label="反馈意见" style="width: 100%" prop="info">
							<el-input type="textarea" v-model="fkpj.info"></el-input>
						</el-form-item>
					</el-form>
				</el-card>
				<el-row style="text-align: center;margin-top: 25px;">
					<el-button size="mini" type="info" @click="toRwFklb">
						<i class="iconfont icon-quxiao"></i>取消</el-button>

					<el-button size="mini" type="primary" @click="submitKcfk('fkpj')" :loading="sumbitLoading">
						<i class="iconfont icon-queren"></i>提交</el-button>

					<el-button size="mini" type="primary" @click="goxmqj()">
						<i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
				</el-row>
			</el-col>
		</el-row>

		<!--新增编辑界面-->
		<el-dialog  :title="title" :visible.sync ="editFormVisible" top="2vh" width="60%" :close-on-click-modal="false">
			<el-form :model="kcfk" :rules="rules" label-width="100px" ref="kcfk" size="mini">
				<el-input type="text" v-model="kcfk.id" style="display: none;"></el-input>
				<el-row>
					<el-col :span="12">
						<el-form-item label="调查项分类">
							<el-radio v-model="kcfk.dcnr" label="0">项目周边管网</el-radio>
							<el-radio v-model="kcfk.dcnr" label="1">具体位置测压</el-radio>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="">
							<span>注：调查项主要内容，一张调查表可以有多个调查项</span>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="12">
						<el-form-item label="线路主路" prop="gxzl">
							<el-input type="text" v-model="kcfk.gxzl" class="inputBox"></el-input><span>格式：A路</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="管线路段" prop="gxld">
							<el-input type="text" v-model="kcfk.gxld" class="inputBox"></el-input><span>格式：B路至C路</span>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="24">
						<el-form-item label="" prop="gxjzl">
							管线在主路的
							<el-select v-model="kcfk.gxzzl" placeholder="请选择" style="width:20%;">
								<el-option
										v-for="item in zlwz"
										:key="item.id"
										:label="item.codename"
										:value="item.id">
								</el-option>
							</el-select>
							侧，距主路中线
							<el-input type="text" v-model="kcfk.gxjzl" style="width:15%;"></el-input>
							米，正上方覆盖物
							<el-select v-model="kcfk.gxzsf" placeholder="请选择" style="width:20%;">
								<el-option
										v-for="item in zsffgw"
										:key="item.id"
										:label="item.codename"
										:value="item.id">
								</el-option>
							</el-select>
							。
						</el-form-item>
					</el-col>

				</el-row>

				<el-row>
					<el-col :span="12">
						<el-form-item label="管线口径" prop="gxkj">
							<el-select v-model="kcfk.gxkj" placeholder="请选择" class="inputBox">
								<el-option
										v-for="item in gxkj"
										:key="item.id"
										:label="item.codename"
										:value="item.id">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="管线材质">
							<el-select v-model="kcfk.gxcz" placeholder="请选择" class="inputBox">
								<el-option
										v-for="item in gxcz"
										:key="item.id"
										:label="item.codename"
										:value="item.id">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="">
							<span>管线主路：管线所在道路名称，非市政道路不填。若调查测压点压力，可在调查点位置中填写位置信息。</span>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="12">
						<el-form-item label="测压对象">
							<el-input type="text" v-model="kcfk.cydx" class="inputBox"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="测压时间">
							<el-date-picker v-model="kcfk.cysj" type="datetime" placeholder="选择日期时间"
											value-format="yyyy-MM-dd HH:mm" format="yyyy-MM-dd HH:mm" class="inputBox"></el-date-picker>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="24">
						<el-form-item label="调查点位置">
							<el-input type="text" v-model="kcfk.dcdwz" class="inputBox"></el-input>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="24">
						<el-form-item label="">
							<span>测压对象：指消防栓、小区首层最高点（一层水龙头）等具体供水设施。</span><br>
							<span>调查点位置：指测压点、装表点等具有点特征的地址，如：A路与B路交口C侧D米，A路B小区门口C侧D米。</span>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="12">
						<el-form-item label="测压点压力" prop="cydyl">
							<el-input type="text" v-model="kcfk.cydyl" class="inputBox"></el-input><span>Mpa</span>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="测压点标高" prop="cydbg">
							<el-input type="text" v-model="kcfk.cydbg" class="inputBox"></el-input><span>米</span>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="3">
						<el-form-item label="">
							<el-checkbox v-model="kcfk.bwts" true-label="1" false-label="0">并网通水</el-checkbox>
						</el-form-item>
					</el-col>
					<el-col :span="9">
						<el-form-item label="">
							<el-checkbox v-model="kcfk.gwyj" true-label="1" false-label="0">管网移交</el-checkbox>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="测压点深度" prop="cydsd">
							<el-input type="text" v-model="kcfk.cydsd" class="inputBox"></el-input><span>米</span>
						</el-form-item>
					</el-col>

				</el-row>

				<el-row>
					<el-col :span="12">
						<el-form-item label="调查人" prop="dcr">
							<el-input type="text" v-model="kcfk.dcr" class="inputBox"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="调查日期" prop="dcrq">
							<el-date-picker v-model="kcfk.dcrq" type="date" placeholder="选择日期时间"
											value-format="yyyy-MM-dd" format="yyyy-MM-dd" class="inputBox"></el-date-picker>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="24">
						<el-form-item label="备注">
							<el-input type="textarea" v-model="kcfk.bz"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>

			<el-row>
				<el-col :span="24" style="text-align: center;margin: 5px;">
					<el-button size="mini" type="info" @click.native="editFormVisible = false">
						<i class="iconfont icon-quxiao"></i>取消</el-button>

					<el-button size="mini" type="primary" @click="saveFk('kcfk')" :loading="addLoading">
						<i class="iconfont icon-save"></i>提交</el-button>
				</el-col>
			</el-row>
		</el-dialog>

	</section>
</template>
<style lang="css">
	.rowDiv el-row{
		margin: 10px;
	}
	.inputBox{
		width: 60% !important;
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
	.el-textarea.is-disabled .el-textarea__inner {
		background-color: #F5F7FA;
		border-color: #E4E7ED;
		color: #303133;
		cursor: not-allowed;
	}
</style>
<script>
	import axios from 'axios'
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import hhldsx from '@/views/template/hhldsx.vue';
	import util from '../../common/js/util'
	import { saveXckcFk, getKcfkRowQs,deleteKcfkRowQs,saveKcfkRowQs,getFkQsCode,updateCheckTask,getKczbxx,download,deleteFkFj,importFile,getFkFjData } from '../../api/xkylx';
	import ZKCXX from '@/views/xkylx/zkcxx.vue';
	export default {
		data() {
			var checkNum = (rule, value, callback) => {
				//var reg =  /^[1-9]\d*$/;
				var reg = /^(?:0|[1-9]\d*)(?:\.\d*[1-9])?$/;
				if (!reg.test(value) && !value == '') {
					callback(new Error('距离主路中路距离必须为非负数字'));
				} else {
					callback();
				}
			};
			var checkNum2 = (rule, value, callback) => {
				//var reg =  /^[1-9]\d*$/;
				var reg = /^(?:0|[1-9]\d*)(?:\.\d*[1-9])?$/;
				if (!reg.test(value) && !value == '') {
					callback(new Error('必须为有效数字'));
				} else {
					callback();
				}
			};
			var checkGxzl = (rule, value, callback) => {
				if (value == '' && this.kcfk.dcnr == '0') {
					callback(new Error('管线主路不能为空'));
				} else {
					callback();
				}
			};
			var checkGxld = (rule, value, callback) => {
				if (value == '' && this.kcfk.dcnr == '0') {
					callback(new Error('管线路段不能为空'));
				} else {
					callback();
				}
			};
			var checkGxkj = (rule, value, callback) => {
				if (value == '' && this.kcfk.dcnr == '0') {
					callback(new Error('管线口径不能为空'));
				} else {
					callback();
				}
			};
			return {
				flag : true,
				title : '',
				name : '',
				type : '3',//区所
				kcfkRow : [],//反馈记录
				gxkj : [],//管线口径
				gxcz : [],//管线材质
				zlwz : [],//相对主路方向
				zsffgw :[],//正上方覆盖物
				listLoading : false,
				editFormVisible : false,
				kcsj : '',//预约勘查时间
				kcdd:'',//勘查地点
				kcbz:'',//勘查备注
				xmid : this.$route.query.xmid,
				activityId:this.$route.query.activityId,
				kcrwcbid : '',
				//zlwz: [{value:'1',label:'上方'},{value:'2',label:'下方'},{value:'3',label:'左右侧'}],
				//zsffgw: [{value:'1',label:'杆子'},{value:'2',label:'道路'},{value:'3',label:'柱子'}],
				kcfk : {
					id:'',
					dcnr:'',
					gxzl: '',
					gxld: '',
					gxjzl: '',
					gxzzl: '',
					gxzsf: '',
					gxkj: '',
					gxcz: '',
					cydx: '',
					cysj: '',
					dcdwz: '',
					cydbg: '',
					cydyl: '',
					gwyj: '0',
					bwts: '0',
					dcr: '',
					dcrq: '',
					bz: '',
					cydsd:''//深度
				},
				fkpj: {
					info: ''
				},
				fileList : [],
				fjData : [],
				rules: {
					gxjzl: [
						{validator: checkNum, trigger: 'blur'}
					],
					cydyl: [
						{validator: checkNum2, trigger: 'blur'}
					],
					cydbg: [
						{validator: checkNum2, trigger: 'blur'}
					],
					gxzl:[
						{validator: checkGxzl, trigger: 'blur'}
					],
					gxld:[
						{validator: checkGxld, trigger: 'blur'}
					],
					gxkj:[
						{validator: checkGxkj, trigger: 'blur'}
					],
					dcr: [
						{required: true, message: '调查人不能为空', trigger: 'blur'}
					],
					dcrq: [
						{required: true, message: '调查日期不能为空', trigger: 'blur'}
					],
					info: [
						{required: true, message: '勘查反馈意见不可为空', trigger: 'blur'}
					]
				},
				rules1: {
					info: [
						{required: true, message: '勘查反馈意见不可为空', trigger: 'blur'}
					]
				},
				sumbitLoading : false,
				addLoading : false,
				isDisable : false
			}
		},
		methods: {
			formatDcnr : function (row, column) {
				return row.dcnr == '0' ? '项目周边管网' :  '具体位置测压';
			},
			toRwFklb(){
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');
				this.$routerTab.refresh('/MyWork');
			},
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid }})
			},
			downloadData(){
				let data =this.$refs.multipleTable.selection;
				if(data.length == 0){
					this.$message({
						message: '请勾选你要下载的文件',
						type: 'warning'
					});
					return;
				}else {
					for(let i = 0;i<data.length;i++){
						if(data[i].fjlj){
							this.downloadFile(data[i].fjlj,data[i].fjmc);
						}
					}
				}
			},
			getCode(){
				getFkQsCode().then((res) => {
					this.gxkj = res.data.gxkj;
					this.gxcz = res.data.gxcz;
					this.zsffgw = res.data.zsffgw;
					this.zlwz = res.data.zlwz;
				});
			},
			getKczbxx(){
				let params = {xmid:this.xmid}
				getKczbxx(params).then((res) => {
					this.kcbz = res.data.kcbz;
					this.kcdd = res.data.kcdd;
					this.kcsj = res.data.kcsj;
				})
			},
			getKcfkRow() {//获取区所反馈记录
				let params = { type: this.type,xmid:this.xmid };
				this.listLoading = true;
				getKcfkRowQs(params).then((res) => {
					this.kcrwcbid = res.data.kcrwcbid;
					let row = res.data.row;
					for(var i in row){

						if(row[i].dcrq != null){
							row[i].dcrq = row[i].dcrq.substring(0,row[i].dcrq.lastIndexOf(' '));
						}
						if(row[i].cysj != null){
							row[i].cysj = row[i].cysj.substring(0,row[i].cysj.lastIndexOf(':'));
						}
						if(row[i].dcnr == '0'){
							this.flag = false;
						}
					}
					this.kcfkRow = row;
					this.listLoading = false;
				});
			},
			openEditForm: function(index, row){
				this.addLoading = false;
				this.title = "编辑";
				for(var i in row) {
					if (row[i] == null) {
						row[i] = '';
					}
				}
				this.kcfk = row;
				//this.kcfk.cysj = this.kcfk.cysj.substring(0,this.kcfk.cysj.lastIndexOf(':'));
				//this.kcfk.dcrq = this.kcfk.dcrq.substring(0,this.kcfk.dcrq.lastIndexOf(' '));
				this.editFormVisible = true;
			},
			openAddForm: function(){
				this.addLoading = false;
				this.title = "新增";
				this.kcfk = {
					id:'',
					dcnr:'0',
					gxzl: '',
					gxld: '',
					gxjzl: '',
					gxzzl: '',
					gxzsf: '',
					gxkj: '',
					gxcz: '',
					cydx: '',
					cysj: '',
					dcdwz: '',
					cydbg: '',
					cydyl: '',
					gwyj: '0',
					bwts: '0',
					dcr: this.name,
					dcrq: '',
					bz: '',
					cydsd:''//深度
				};
				this.editFormVisible = true;
			},
			//删除
			deleteFk: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let para = { id: row.id };
					deleteKcfkRowQs(para).then((res) => {
						if(res.result == 'success'){
							//this.addLoading = false;
							this.$message({
								message: '删除成功',
								type: 'success'
							});
						}
						this.flag = true;
						this.getKcfkRow();
					});
				});
			},
			getFjData(){
				let params = {xmid:this.xmid,type:'3'}
				getFkFjData(params).then((res) => {
					this.fjData = res.data.fj;
				})
			},
			//保存+新增
			saveFk: function (kcfk) {
				this.$refs[kcfk].validate((valid) => {
					if (valid) {
						if(this.kcfk.dcnr == '0'){
							this.flag = false;
						}
						this.addLoading = true;
						let params = Object.assign({ kcrwcbid : this.kcrwcbid, type : '1'}, this.kcfk);
						saveKcfkRowQs(params).then((res) => {
							//this.addLoading = false;
							if(res.result == 'success'){
								this.$message({
									message: '保存成功',
									type: 'success'
								});
							}
							this.flag = true;
							this.editFormVisible = false;
							this.getKcfkRow();
						});
					} else {
						return false;
					}
				});

			},
			submitKcfk(fkpj){//提交勘察反馈
				this.$refs[fkpj].validate((valid) => {
					if (valid) {
						if(this.flag){
							this.$message({
								message: '请至少有一条项目周边管网信息',
								type: 'warning'
							});
							return;
						}
						this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
							confirmButtonText: '确定',
							cancelButtonText: '取消',
							type: 'warning'
						}).then(() => {
							this.sumbitLoading = true;
							let params = {type: this.type, xmid: this.xmid, info: this.fkpj.info};
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
					} else {
						return false;
					}
				})
			},
			deleteFj(index,row,flag) {
				this.$confirm('确认删除该附件？, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'error'
				}).then(() => {
					let params = {id:row.id,type:'3'}
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
			submitUpload() {
				let fileList = this.fileList;
				let operation = "区所反馈勘查附件";

				if (fileList !=null && fileList.length >0){
					//for (let i=0;i<fileList.length;i++){
						let file = fileList[fileList.length-1];
						let formData = new FormData();
						formData.append("operation",operation);
						formData.append("multipartFiles",file.raw);
						formData.append("filename",file.name);
						formData.append("xmid",this.xmid);
						formData.append("type",'3');
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
			},
			downloadFile(fjlj,fjmc){//附件查看或下载
				let formData = new FormData();
				formData.append("path",fjlj);
				download(formData).then((res) => { // 处理返回的文件流
					const content = res
					const blob = new Blob([content])
					const fileName = fjmc
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
				})
			}

		},
		mounted() {
			updateCheckTask({xmid:this.xmid,type:this.type}).then((res) => {});//更改任务签收状态
			this.getCode();
			this.getKcfkRow();
			this.getKczbxx();
			this.getFjData();

			let JSONstr = sessionStorage.getItem("user");
			let JSON = eval("("+JSONstr+")");
			this.kcfk.dcr = JSON.name;
			this.name = JSON.name;
		},
		components: {
			'hhldsx': hhldsx,
			'XMJBXX': XMJBXX,  //XMJBXX 变成 组件 XMJBXX
			'ZKCXX' : ZKCXX
		}

	}
</script>