<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<hhldsx :xmid="xmid" :activityid="activityId" v-if="isxmtc=='0'"></hhldsx>

			<el-col :span="24">
				<XMJBXX></XMJBXX>
				<!--项目基本信息模板-->
				<!--<el-card class="box-card">-->
					<!--<div slot="header" class="clearfix">-->
						<!--<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目基本信息</div>-->
					<!--</div>-->
					<!--<div class="text item">-->
						<!--<el-form :model="xmxxForm" label-width="80px" ref="xmxxForm" :inline="true" >-->
							<!--<el-row>-->
								<!--<el-col :span="8" >-->
									<!--<el-form-item label="项目编号" prop="xmbh">-->
										<!--<el-input v-model="xmxxForm.xmbh" :readonly="true" class="inputBox"></el-input>-->
									<!--</el-form-item>-->
								<!--</el-col>-->
								<!--<el-col :span="8">-->
									<!--<el-form-item label="项目名称" prop="xmmc" :rules="this.publicUtil.notNull">-->
										<!--<el-input v-model="xmxxForm.xmmc" :readonly="isReadonly" class="inputBox"></el-input>-->
									<!--</el-form-item>-->
								<!--</el-col>-->
								<!--<el-col :span="8">-->
									<!--<el-form-item label="项目地址" prop="xmdz" :rules="this.publicUtil.notNull">-->
										<!--<el-input v-model="xmxxForm.xmdz" :readonly="isReadonly" class="inputBox"></el-input>-->
									<!--</el-form-item>-->
								<!--</el-col>-->
								<!--<el-col :span="8">-->
									<!--<el-form-item label="联系人" prop="lxr" :rules="this.publicUtil.notNull">-->

										<!--<el-input v-model="xmxxForm.lxr" :readonly="isReadonly" class="inputBox"></el-input>-->
									<!--</el-form-item>-->
								<!--</el-col>-->
								<!--<el-col :span="8">-->
									<!--<el-form-item label="联系方式" prop="lxrsjh" :rules="this.publicUtil.phone">-->
										<!--<el-input v-model="xmxxForm.lxrsjh" :readonly="isReadonly" class="inputBox"></el-input>-->
									<!--</el-form-item>-->
								<!--</el-col>-->
								<!--<el-col :span="8">-->
									<!--<el-form-item label="受理日期">-->
										<!--<el-date-picker type="date" placeholder="" v-model="xmxxForm.slsj"-->
														<!--:readonly="true"	style="padding: 0px;width: 130%;">-->
										<!--</el-date-picker>-->
									<!--</el-form-item>-->
								<!--</el-col>-->
								<!--<el-col :span="8" >-->
									<!--<el-form-item label="立项日期">-->
										<!--<el-date-picker type="date"  v-model="xmxxForm.lxsj"-->
														<!--:readonly="true" style="padding: 0px;width: 130%;">-->
										<!--</el-date-picker>-->
									<!--</el-form-item>-->
								<!--</el-col>-->
								<!--<el-col :span="8">-->
									<!--<el-form-item label="项目经理">-->
										<!--<el-input v-model="xmxxForm.xmfzr" style="display: none" class="inputBox"></el-input>-->
										<!--<el-input v-model="xmxxForm.xmfzr_str" :readonly="true" class="inputBox"></el-input>-->
									<!--</el-form-item>-->
								<!--</el-col>-->
								<!--<el-col :span="8">-->
									<!--<el-form-item label="联系方式" prop="lxrsjh" :rules="this.publicUtil.phone">-->
										<!--<el-input v-model="xmxxForm.xmfzr_sjh" :readonly="isReadonly" class="inputBox"></el-input>-->
									<!--</el-form-item>-->
								<!--</el-col>-->
							<!--</el-row>-->
						<!--</el-form>-->
					<!--</div>-->
				<!--</el-card>-->
				<!--附件资料-->
				<zlfljxz></zlfljxz>
				<!--勘查反馈-->
				<zkcxxSjst></zkcxxSjst>
				<el-card  class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目配置</div>
					</div>
					<div class="text item">
						<el-tabs v-model="activeName" type="card">
							<el-tab-pane label="项目范围配置" name="1"><xmfwpz v-if="activeName=='1'" :isxmtc="isxmtc"></xmfwpz></el-tab-pane>
							<el-tab-pane label="材料清单配置" name="2"><wlqdpz :xmid="xmid" v-if="activeName=='2'" :name="0":isxmtc="isxmtc"></wlqdpz></el-tab-pane>
							<el-tab-pane label="工商表配置" name="3"><dbpz :xmid="xmid" v-if="activeName=='3'":isxmtc="isxmtc"></dbpz></el-tab-pane>
							<el-tab-pane label="五级计量表配置" name="4"><wjjlbpz :xmid="xmid" v-if="activeName=='4'":isxmtc="isxmtc"></wjjlbpz></el-tab-pane>
							<el-tab-pane label="居民表配置" name="5"><hbpz :xmid="xmid" v-if="activeName=='5'":isxmtc="isxmtc"></hbpz></el-tab-pane>
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
                                        <label title="拍摄" v-if="isxmtc!='2'"><i @click="openVisible(scope.$index, scope.row)" class="iconfont icon-paishe" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                        <label title="预览"><i @click="stPreview_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										<span>(附件：{{scope.row.pictureNumber}})</span>
									</template>
								</el-table-column>
								<el-table-column
										prop="glxz"
										label="管理下载"
										align="center">
									<template slot-scope="scope">
                                        <label title="管理"><i @click="stManage_vue(scope.$index, scope.row,'stcg')" class="iconfont icon-renyuanguanli" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										<!--<a :href="'#'" @click="stDownLoad_vue(scope.$index, scope.row)">下载</a>-->
										<span>(附件：{{scope.row.fjNumber}})</span>
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
                                        <label title="拍摄" v-if="isxmtc!='2'"><i @click="openVisible(scope.$index, scope.row)" class="iconfont icon-paishe" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                        <label title="预览"><i @click="stPreview_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										<span>(附件：{{scope.row.pictureNumber}})</span>
									</template>
								</el-table-column>
								<el-table-column
										prop="glxz"
										label="给施资料"
										align="center">
									<template slot-scope="scope">
                                        <label title="管理"><i @click="stManage_vue(scope.$index, scope.row,'sjcg')" class="iconfont icon-renyuanguanli" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										<!--<a :href="'#'" @click="stDownLoad_vue(scope.$index, scope.row)">下载</a>-->
										<span>(附件：{{scope.row.fjNumber}})</span>
									</template>
								</el-table-column>
							</el-table>
						</el-row>
						<el-row class="row"></el-row>
					</div>
				</el-card>
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">备注</div>
					</div>
					<div>
						<el-input
								type="textarea"
								:rows="4"
								placeholder="请输入内容"
								v-model="bz">
						</el-input>
					</div>
				</el-card>
				<el-row style="text-align: center;margin-top: 20px;">
					<router-link :to="tabUrl">
						<el-button size="mini" type="info" @click="cancel()"v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取消</el-button>
						<!--<el-button @click="htyyVisible = true">退回</el-button>-->
						<el-button size="mini" type="primary"@click="save_vue()"  v-if="isxmtc!='2'"><i class="iconfont icon-save"></i>保存</el-button>
						<el-button size="mini" type="primary"@click="submit_vue()" :loading="loading" v-if="isxmtc=='0'"><i class="iconfont icon-queren"></i>提交</el-button>
					</router-link>
					<el-button size="mini" type="primary"@click="zddialogOpen()"  v-if="iszdshow&&isyqshow&&isxmtc!='2'" style="margin-left: 10px;"><i class="iconfont icon-tuxing"></i>转单</el-button>
					<el-button size="mini" type="danger"@click="yqdialogOpen()"  v-if="isyqshow&&isxmtc!='2'" style="margin-left: 10px;"><i class="iconfont icon-gaojing"></i>延期</el-button>
					<el-button size="mini" type="primary" @click="goxmqj()" v-if="isxmtc!='2'" style="margin-left: 10px;"><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
				</el-row>
			</el-col>
		</el-row>

		<el-dialog title="申请延期" :visible.sync = "sqyqVisible" :close-on-click-modal="false" width="50%" >
			<el-form :model="sqyqForm"  ref="sqyqForm" :rules="sqyqrules">
				<el-row>
					<el-col :span="12">
						<el-form-item label="申请人" prop="sqr" style="margin-bottom: 15px" size="small" label-width="110px">
							<el-input v-model="sqyqForm.sqr"  readonly></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="审批人" prop="spr" style="margin-bottom: 15px" size="small" label-width="110px">
							<el-input v-model="sqyqForm.spr"  readonly></el-input>
							<el-input v-model="sqyqForm.sprid" style="display: none" ></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="要求完成时间" prop="yqwcsj" style="margin-bottom: 15px" size="small" label-width="110px">
							<el-input v-model="sqyqForm.yqwcsj"  readonly></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="预计完成时间" prop="jhyqjz_vue" style="margin-bottom: 15px" size="small" label-width="110px">
							<el-date-picker :picker-options="{ selectableRange: '8:00:00 - 20:00:00' }" style="width: 100%" format="yyyy-MM-dd HH:mm"value-format="yyyy-MM-dd HH:mm" size="small" type="datetime" placeholder="选择日期" v-model="sqyqForm.jhyqjz_vue"></el-date-picker>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-form-item label="延期说明" prop="csyy" style="margin-bottom: 15px" size="small" label-width="110px">
						<el-input
								type="textarea"
								:rows="4"
								placeholder="请输入内容"
								v-model="sqyqForm.csyy">
						</el-input>
					</el-form-item>
				</el-row>
				<el-row>
					<el-upload
							class="upload-demo"
							ref="upload"
							action="#"
							:on-preview="handlePreview"
							:http-request="submitUpload"
							:on-remove="handleRemove"
							:on-change="handleChange"
							:file-list="fileList"
							:auto-upload="true"
							:show-file-list=false>
						<el-button slot="trigger" size="mini" type="success" ><i class="iconfont icon-shangchuan_f"></i>&nbsp;上传</el-button>
						<el-button  size="mini" type="success" @click="delayps"><i class="iconfont icon-paishe"></i>&nbsp;拍摄</el-button>
						<!--<el-button size="mini" type="success" style="margin-left: 10px" @click="download"><i class="iconfont icon-xiazai_f"></i>下载</el-button>-->
						<!--<el-button size="mini" type="primary" style="margin-left: 10px" @click="look"><i class="iconfont icon-yulan"></i>预览</el-button>-->
					</el-upload>

				</el-row>
				<el-table :data="delayfjData" :stripe="true" style="padding-bottom: 10px">
					<el-table-column property="FJMC" label="附件名称" align="center"></el-table-column>
					<el-table-column property="SCSJ" label="上传时间" align="center"></el-table-column>
					<el-table-column property="USERNAME" label="上传人" align="center"></el-table-column>
					<el-table-column property="address" label="操作" align="center">
						<template slot-scope="scope">
							<el-row>
								<el-col :span="24">
									<div v-if="scope.row.FJHZ!=null&&(scope.row.FJHZ.toLowerCase()=='png'||scope.row.FJHZ.toLowerCase()=='jpg')">
										<el-col :span="24">
											<label title="删除"><i class="iconfont delete" @click="deleteDelayFj_vue(scope.$index, scope.row)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
											<label title="预览"><i class="iconfont icon-yulan" @click="downloadDelayFile_vue(scope.$index, scope.row,1)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										</el-col>
									</div>
									<div v-else>
										<el-col :span="24">
											<label title="删除"><i @click="deleteDelayFj_vue(scope.$index, scope.row)" class="iconfont delete" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
											<label title="下载"><i @click="downloadDelayFile_vue(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
										</el-col>
									</div>
								</el-col>
							</el-row>
						</template>
					</el-table-column>
				</el-table>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button size="mini" type="info" @click="sqyqVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
				<el-button type="primary" size="mini" @click="sqyq_vue('sqyqForm')"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>
		</el-dialog>

		<el-dialog title="退回原因" :visible.sync = "htyyVisible" :close-on-click-modal="false">
			<el-input
					type="textarea"
					:rows="4"
					placeholder="请输入内容"
					v-model="htyy">
			</el-input>
			<div slot="footer" class="dialog-footer">
				<el-button size="mini" type="info" @click="htyyVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
				<el-button type="primary" size="mini" @click="sjcgscBack_vue()"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>
		</el-dialog>
		<el-dialog title="拍摄" :visible.sync = "creamVisible" top="5vh" :close-on-click-modal="false">
			<DocCamera  v-bind:dirName ="dirName" v-bind:fileName ="fileName" v-on:listenToChildEvent="showMsg"></DocCamera>
		</el-dialog>
		<el-dialog title="拍摄" :visible.sync = "creamVisible_Delay" top="5vh" :close-on-click-modal="false">
			<DocCamera  v-bind:dirName ="dirName" v-bind:fileName ="fileName_Delay" v-on:listenToChildEvent="showMsg_delay"></DocCamera>
		</el-dialog>
		<el-dialog title="设计人员" :visible.sync = "designerVisible"  width="30%" >
			<el-row v-model="designerData" v-for="item in designerData" class="col" :key="item.USERID">
				<el-radio v-model="radio" :disabled=item.DISABLED :label="item.USERID">{{item.USERNAME}}（在建项目{{item.NUM}}个）</el-radio>
			</el-row>
			<div slot="footer" class="dialog-footer">
				<el-button size="mini" type="info" @click="designerVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
				<el-button type="primary" size="mini" @click="zdsubmit_vue()"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>
		</el-dialog>
		<el-dialog  :title="title" :visible.sync="stcgDialogFormVisible" width="50%" :close-on-click-modal="false">
			<bigFileUpload @resultPath="getResultPath" ref="mychild" :xmid="xmid"  v-if="isxmtc!='2'&&stcgDialogFormVisible"></bigFileUpload>
			<el-table :data="stcgxqData"style="width: 100%" :stripe="true" v-loading="downLoad" element-loading-text="文件下载中，请稍后" element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.1)">
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
                        <label title="删除" v-if="isxmtc!='2'"><i class="iconfont delete" @click="stDelete_vue(scope.$index, scope.row)" style="cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
					</template>
				</el-table-column>

			</el-table>

		</el-dialog>

    </section>
</template>

<style>

</style>
<script>
	import DocCamera from '@/views/template/DocCamera.vue';
	import hhldsx from '@/views/template/hhldsx.vue';
	import bigFileUpload from '@/views/template/bigFileUpload.vue';
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import zlfljxz from '@/views/template/zlfljxz.vue';
	import xmfwpz from '@/views/template/xmfwpz.vue';
	import wlqdpz from '@/views/template/wlqdpz.vue';
	import dbpz from '@/views/template/dbpz.vue';
	import hbpz from '@/views/template/hbpz.vue';
	import zkcxx_sjst from '@/views/xkylx/zkcxx_sjst.vue';
	import wjjlbpz from '@/views/template/wjjlbpz.vue';
	import ImageView from '../../common/components/form/img-preview/ImageView.vue'
	import util from '../../common/js/util'
	import apiUrl from "../../../src/httpConfig/baseUrl";
	//import {get,post} from '../../httpConfig/axioss.js'
	import axios from 'axios';
	import { initStcgTable,stManage,stDelete,stPreview,stcgsc,scsjcgSubmit,sjcgscBack,download_sjst,saveXmjbxx,buttonshow,getzdry,zdsubmit,sqyqxx,sqyq,yqbuttonshow,rwtxfsdx,importFile_delay,toDetial_delay,deleteDelayFj,stcgsc_delay,fsdx} from '../../api/sjst';
	import { findXmxx } from '../../api/xkylx';
	export default {

		components:{
			ImageView
		},
		data() {
			return {
				xmxxForm: {
					sqbh : '',//申请单编号
					xmbh : '',//项目编号
					sqlx : '',//受理类型
					lxr : '',//联系人
					lxrsjh : '',//联系人联系方式
					slsj : '',//受理时间
					lxrq : '',//立项时间
					xmdz : '',//项目地址
					xmmc : '',//项目名称
					xmfzr_str : '',//项目经理
					xmfzr:'',
					xmfzr_sjh : ''//项目经理联系方式
				},
				//延期
				sqyqVisible:false,
				creamVisible_Delay:false,
				delayfjData:[],
				sqyqForm:{
					sqr:"",
					spr:"",
					yqwcsj:"",
					jhyqjz_vue:"",
					csyy:"",
					sprid:"",
				},
				sqyqrules:{
					jhyqjz_vue:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],

					csyy:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
				//--------------------------
				isReadonly:false,
				iszdshow:false,
				isyqshow:false,
				designerVisible:false,
				designerData:[],
				radio:"",
				htyy:"",
				fileName: 'sjcgsc',//给子组件的文件名字
				fileName_Delay:'sjcgdelay',
				dirName:"",
				path:null,
				activeName:'1',
				type:"",
				title:"",
				fjlxEj:"",
				fjlxYj:"",
				// fjlxEj_delay:"",
				// fjlxYj_delay:"",
				slide1:[],
				fileList:[],
				Data:[],
				sjData:[],
				stcgxqData:[],
				downLoad:false,
				loading:false,
				stcgDialogFormVisible:false,
				delayid:"",
				creamVisible:false,
				htyyVisible:false,
				xmid:this.$route.query.xmid,
				activityId:this.$route.query.activityId,
				sjstrzwid:this.$route.query.sjstrzwid,//设计审图任务id
				isxmtc:this.$route.query.isxmtc,//入口标志0：由我的待办，我的工作进入；1是由设计补充进入；2：是由项目全景进入
				bz:"",
				options: {
					target: apiUrl+'uploader/chunk',
					testChunks: true,
					simultaneousUploads: 1,
					chunkSize: 10 * 1024 * 1024,
					checkChunkUploadedByResponse: function (chunk, message) {
						let objMessage = {}
						try {
							objMessage = JSON.parse(message)
						} catch (e) {
						}
						// fake response
						// objMessage.uploaded_chunks = [2, 3, 4, 5, 6, 8, 10, 11, 12, 13, 17, 20, 21]
						// check the chunk is uploaded
						return (objMessage.uploaded_chunks || []).indexOf(chunk.offset + 1) >= 0
					}
				},
				attrs: {
					accept: 'image/*'
				},
				statusText: {
					success: '成功了',
					error: '出错了',
					uploading: '上传中',
					paused: '暂停中',
					waiting: '等待中'
				},
				tabUrl:'/scsjcg?xmid='+this.$route.query.xmid+'&activityId='+this.$route.query.activityId+'&sjstrzwid='+this.$route.query.sjstrzwid+'&isxmtc='+this.$route.query.isxmtc
			}
		},
		methods: {
			//跳转至项目全景
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
			},
			//延期申请
			yqdialogOpen(){
				let params={
					xmid:this.xmid,
					activityId:this.activityId
				}
				sqyqxx(params).then((res)=>{
					if(res.data!=null){
						this.sqyqForm.sqr=res.data.sqr;
						this.sqyqForm.spr=res.data.spr;
						this.sqyqForm.sprid=res.data.sprid;
						this.sqyqForm.yqwcsj = res.data.yqwcsj;
						this.sqyqForm.jhyqjz_vue = "";
						this.sqyqForm.csyy = "";
						this.sqyqVisible=true;
						this.toDetial_vue();
					}
				})
			},
			//申请延期
			sqyq_vue(formName){
				this.$refs[formName].validate((valid) => {
					if (valid) {
						let yqwcsjdate = util.formatDate.parse(this.sqyqForm.yqwcsj,'yyyy-MM-dd hh:mm');
						let jhyqjz_vuedate = util.formatDate.parse(this.sqyqForm.jhyqjz_vue,'yyyy-MM-dd hh:mm');
						if(jhyqjz_vuedate<yqwcsjdate){
							this.$message.error('预计完成时间不得小于要求完成时间');
							return;
						}
						let params={
							delayid:this.delayid,
							xmid:this.xmid,
							activityId:this.activityId,
							yqwcsj:this.sqyqForm.yqwcsj,
							jhyqjz_vue:this.sqyqForm.jhyqjz_vue,
							csyy:this.sqyqForm.csyy,
							sprid:this.sqyqForm.sprid
						}
						sqyq(params).then((res)=>{
							if(res.data.flag){
								this.$message.success('保存成功！');
								this.sqyqVisible=false;
								this.isyqshow = false;
								let param={
									id:res.data.id
								}
								fsdx(param).then((res)=>{

								})
							}else{
								this.$message.error('保存失败！');
							}
						})
					} else {
						return false;
					}
				});

			},

			//项目基本信息
			findXmxx_vue(){
				let params = {
					xmid :this.xmid
				};
				findXmxx(params).then((res) => {
					let obj = eval("("+ res.data.xmxx +")");
					if(obj != null){
						for(var i in obj){
							if(obj[i] == 'null'){
								obj[i] = '';
							}
						}
						this.xmxxForm = obj;
					}
				});
			},
			save_vue(){
				let params={
					xmid:this.xmid,
					xmmc:this.xmxxForm.xmmc,
					xmdz:this.xmxxForm.xmdz,
					lxr:this.xmxxForm.lxr,
					lxrsjh:this.xmxxForm.lxrsjh,
					xmfzr_sjh:this.xmxxForm.xmfzr_sjh,
					xmfzr:this.xmxxForm.xmfzr,
					bz:this.bz
				}
				saveXmjbxx(params).then((res)=>{
					if(res.data==true){
						this.$message({
							message: '保存成功',
							type: 'success'
						});
						if(this.isxmtc=='1'){
							this.tabUrl='/supplementDesign'
						}else{
							this.tabUrl='/Backlog'
						}
						this.$routerTab.close();
						this.$routerTab.refresh(this.tabUrl);
						this.$routerTab.refresh('/MyWork');
					}else {
						this.$message({
							message: '保存失败',
							type: 'error'
						});
					}
				})
			},

			buttonshow_vue(){
				let params={
					activityId:this.activityId,
					direction:2
				}
				buttonshow(params).then((res)=>{
					this.iszdshow = res.data;
				})
			},
			yqbuttonshow_vue(){
				let params={
					activityId:this.activityId,
					xmid:this.xmid
				}
				yqbuttonshow(params).then((res)=>{
					this.isyqshow = res.data;
				})
			},
			zddialogOpen(){
				this.designerVisible = true;
				let params = {
					roleid : "369fd3cbf46a46809af4167a75f123b9",
					xmid:this.xmid
				};
				getzdry(params).then((res)=>{
					this.designerData = res.data.zpry;
					this.radio="";
				})
			},
			zdsubmit_vue(){
				if(this.radio==""){
					this.$message.error('请选择指派人员！');
					return;
				}
				let params={
					radio:this.radio,
					xmid:this.xmid,
					activityId:this.activityId
				}
				this.tabUrl='/Backlog';
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');
				this.$routerTab.refresh('/MyWork');
				zdsubmit(params).then((res)=>{
					if(res.data){
						this.$message({
							message: '指派成功',
							type: 'success'
						});
						let param={
							"xmid":this.xmid,
							"activitiId":this.activityId,
							"direction":2
						}
						rwtxfsdx(param).then((res)=>{})
					}else{
						this.$message({
							message: '指派失败',
							type: 'error'
						});
					}
				})
			},

			// init(){//初始化参数
			// 	let JSONstr = sessionStorage.getItem("user");
			// 	let JSON = eval("("+JSONstr+")");
			// 	//console.log(JSON);
			// 	//console.log(JSON.username);
			// 	this.dirName = JSON.id;//文件夹名字
			// },
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
						this.bz=res.data.bz;
						this.dirName=res.data.xmbh;
					}
				})
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
					// let image = new Image();
					// console.log(this.$route.params.path)
					//
					// image.src = require('../../assets/Photo.jpg');
					// image.onload = function(){
					// 	let base64 = this.getBase64Image(image);
					// 	console.log(base64);
					// }
				})
			},
			cancel(){
				if(this.isxmtc=='1'){
					this.tabUrl='/supplementDesign'
				}else{
					this.tabUrl='/Backlog'
				}
				this.$routerTab.close();
				this.$routerTab.refresh(this.tabUrl);
			},
			getBase64Image(img) {
				var canvas = document.createElement("canvas");
				canvas.width = img.width;
				canvas.height = img.height;
				var ctx = canvas.getContext("2d");
				ctx.drawImage(img, 0, 0, img.width, img.height);
				var ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
				var dataURL = canvas.toDataURL("image/" + ext);
				return dataURL;
			},
			//打开详情
			stManage_vue(index, row, type) {
				this.$forceUpdate();
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
			//删除单个文件
			stDelete_vue(index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {
						id: row.id,
						flag: 1
					}
					stDelete(params).then((res) => {
						let row = {
							parentid: this.fjlxYj,
							id: this.fjlxEj
						}
						this.stManage_vue("", row, this.type);
						this.initStcgTable_vue(this.type);
					})
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
				// let instance = axios.create({
				// 	baseURL: 'http://localhost:8080',
				// 	headers: {
				// 		'Content-Type': 'application/json; application/octet-stream'
				// 	},
				// 	responseType: 'blob',
				// });
				// instance.post('/sjstController/download', formData).then((res) => { // 处理返回的文件流
				// 	const content = res.data
				// 	const blob = new Blob([content])
				// 	const fileName = row.fjmc
				// 	if ('download' in document.createElement('a')) { // 非IE下载
				// 		const elink = document.createElement('a')
				// 		elink.download = fileName
				// 		elink.style.display = 'none'
				// 		elink.href = URL.createObjectURL(blob)
				// 		document.body.appendChild(elink)
				// 		elink.click()
				// 		URL.revokeObjectURL(elink.href) // 释放URL 对象
				// 		document.body.removeChild(elink)
				// 	} else { // IE10+下载
				// 		navigator.msSaveBlob(blob, fileName)
				// 	}
				// 	//}
				// })
			},
			openVisible(index,row){
				this.fjlxYj=row.parentid;
				this.fjlxEj=row.id;
				this.creamVisible = true;
			},
			showMsg:function(data){//存储子组件保存的图片名称
				let name = data;
				let path = 'upload/'+this.dirName+'/'+data;
				this.submitUpload_vue(name, path,0);
				//接口预留位置  data为图片名称 将图片名称保存到数据库
			},
			showMsg_delay:function(data){//存储子组件保存的图片名称
				let name = data;
				let path = 'upload/'+this.dirName+'/'+data;
				this.submitUpload_delay(name, path);

				//接口预留位置  data为图片名称 将图片名称保存到数据库
			},

			submitUpload_delay(name, path){
					let params = {
						name: name,
						path: path,
						delayid: this.delayid,
					}
					stcgsc_delay(params).then((res) => {
						let msg = res.data.msg;
						if (msg == '上传成功') {
							this.$message({
								message: '上传成功',
								type: 'success'
							});
							this.delayid=res.data.delayid;
							this.toDetial_vue();
							this.creamVisible_Delay=false;
						} else {
							this.$message.error('上传失败！');
						}
					})
			},
			handlePreview(file) {

			},
			handleChange(file, fileList) {
				this.fileList = fileList;
			},
			handleRemove(file, fileList) {

			},
			submitUpload(){
				if(this.fileList.length>0){
					let file = this.fileList[this.fileList.length-1];
					let formData = new FormData();
					formData.append("multipartFiles",file.raw);
					formData.append("filename",file.name);
					formData.append("delayid",this.delayid);
					formData.append("xmid",this.xmid);
					// formData.append("fjlxEj",this.fjlxEj_delay);
					// formData.append("fjlxYj",this.fjlxYj_delay);
					importFile_delay(formData).then((res)=>{
						let msg = res.data.msg;
						if(msg == '上传成功'){
							this.$message({
								message: '上传成功',
								type: 'success'
							});
							this.fileList=[];
							this.delayid=res.data.delayid;
							this.toDetial_vue();
						}else{
							this.$message.error('上传失败！');
						}
					})
				}
			},
			toDetial_vue() {
				let params = {
					delayid: this.delayid
				}
				toDetial_delay(params).then((res) => {
					if (res.data != null && res.data.row != null) {
						this.delayfjData = res.data.row;
					}
				})
			},
			deleteDelayFj_vue(index,row){
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {
						id: row.ID,
					};
					deleteDelayFj(params).then((res) => {
						let msg = res.data;
						if(msg){
							this.$message({
								message: '删除成功',
								type: 'success'
							});
							this.toDetial_vue();
						}else{
							this.$message.error('删除失败！');
						}
					});
				})
			},
			downloadDelayFile_vue(index,row,flag){
				let formData = new FormData()
				formData.append("path",row.FJLJ);
				download_sjst(formData).then((res)=>{
					if(flag==1){
						this.slide1=[];
						let reader = new FileReader()
						reader.onload = (e) => {
							let img = new Image();
							img.src =e.target.result;
							let item = {};
							item.msrc = item.src = e.target.result;
							item.h = img.height;
							item.w = img.width;
							this.slide1.push(item);
							this.$preview.openList(0, this.slide1);
						}
						reader.readAsDataURL(res.data)
					}else{
						const content = res.data
						const blob = new Blob([content])
						const fileName = row.FJMC
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
			delayps(){
				this.creamVisible_Delay = true;
			},
			getResultPath(result) {
				this.submitUpload_vue(result.name, result.path,1)
			},
			// 上传
			submitUpload_vue(name, path,flag) {
				let params = {
					name: name,
					path: path,
					xmid: this.xmid,
					sjstrzwid: this.sjstrzwid,
					fjlxYj: this.fjlxYj,
					fjlxEj: this.fjlxEj
				}
				stcgsc(params).then((res) => {
					let msg = res.data.msg;
					if (msg == '上传成功') {
						this.$message({
							message: '上传成功',
							type: 'success'
						});
					} else {
						this.$message.error('上传失败！');
					}
					let row = {
						id: this.fjlxEj,
						parentid: this.fjlxYj
					}
					if(flag==1){
						this.stManage_vue("", row, this.type);
					}
					//this.initStcgTable_vue(this.type);
					//初始化审图成果
					this.initStcgTable_vue('stcg');
					//初始化设计成果
					this.initStcgTable_vue('sjcg');
				})
			},
			//退回
			sjcgscBack_vue(){
				if(this.htyy==''||this.htyy==null){
					this.$message.error('请填写退回原因！');
					return;
				}
				let params={
					htyy:this.htyy,
					xmid:this.xmid,
					activityId:this.activityId
				}
				sjcgscBack(params).then((res)=>{
					if(res.data){
						this.$router.push({path: '/Backlog', query: {}});
						this.$message.success('退回成功');
					}
				})
			},
			//提交
			submit_vue(){
				this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.loading=true;
					let params={
						sjrybz:this.bz,
						xmid:this.xmid,
						activityId:this.activityId,
						type:"sjst"
					}

					scsjcgSubmit(params).then((res)=>{
						this.loading=false;
						if(res.data){
							this.$message({
								message: '提交成功',
								type: 'success'
							});
							let param={
								"xmid":this.xmid,
								"activitiId":this.activityId,
								"direction":3
							}
							rwtxfsdx(param).then((res)=>{})
							this.tabUrl='/Backlog'
							this.$routerTab.close();
							this.$routerTab.refresh('/Backlog');
							this.$routerTab.refresh('/MyWork');
						}else{
							this.$message({
								message: '提交失败',
								type: 'error'
							});
						}
					})
				})

			}
		},

			mounted() {
				if(this.isxmtc=='1'){
					this.isReadonly=false;
				}else{
					this.isReadonly=true;
				};
				this.buttonshow_vue();
				this.yqbuttonshow_vue();
				this.findXmxx_vue();
				// this.init();
				//初始化审图成果
				this.initStcgTable_vue('stcg');
				//初始化设计成果
				this.initStcgTable_vue('sjcg');
			},
			components: {
				'XMJBXX': XMJBXX,  //将别名demo 变成 组件 Demo
				'zlfljxz': zlfljxz,
				'xmfwpz': xmfwpz,
				'wlqdpz': wlqdpz,
				'dbpz': dbpz,
				'hbpz': hbpz,
				'wjjlbpz': wjjlbpz,
				'bigFileUpload': bigFileUpload,
				'DocCamera':DocCamera,
				'hhldsx':hhldsx,
				'zkcxxSjst':zkcxx_sjst
			}

	}

</script>
<style scoped>
	/*.itemRow{
    width:32%;
}
.itemRow .el-input {
    width: 150%;
}*/
	.inputBox{
		width: 150% !important;
	}
</style>
<style>

	.uploader-example {
		padding: 15px;
		margin: 40px auto 0;
		font-size: 12px;
		box-shadow: 0 0 10px rgba(62, 146, 203, 0.4);
	}

	.uploader-example .uploader-btn {
		margin-right: 4px;
	}

	.uploader-example .uploader-list {
		max-height: 440px;
		overflow: auto;
		overflow-x: hidden;
		overflow-y: auto;
	}
</style>