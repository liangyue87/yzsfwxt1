<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
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

		<el-row >
			<el-card  class="box-card"  style="margin-top: 15px;">
				<div slot="header" class="clearfix">
					<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目配置</div>
				</div>
				<div class="text item">
					<el-tabs v-model="activeName" type="card" >
						<el-tab-pane label="项目范围配置" name="1"><xmfwpz :qb="0" :isxmtc="'2'" v-if="activeName=='1'"></xmfwpz></el-tab-pane>
						<el-tab-pane label="物料清单配置" name="2"><wlqdpz :qb="0" :xmid="xmid" v-if="activeName=='2'" :name="1"></wlqdpz></el-tab-pane>
						<el-tab-pane label="工商表配置" name="3"><dbpz :qb="'0'" :isxmtc="'2'" :xmid="xmid" v-if="activeName=='3'"></dbpz></el-tab-pane>
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

			<!--设计图会审结果-->
			<el-card class="box-card"  style="margin-top: 15px;">
				<div slot="header" class="clearfix">
					<div class="div_title">会议记录
						<span class="span_cz" @click="openBody('1')" v-if="!xmxxsfzk"><i class="el-icon-caret-right">点击展开</i></span>
						<span class="span_cz" @click="closeBody('1')" v-if="xmxxsfzk"><i class="el-icon-caret-bottom">点击收起</i></span>
					</div>
				</div>
				<div class="text item" v-if="xmxxbody">
					<el-form :model="kcfk" label-width="150px" ref="xmxxForm">
						<el-form-item label="参与人：" style="width: 45%">
							<span id="usercheck">
                                    &nbsp;&nbsp;{{username}}
                                </span>
						</el-form-item>
						<el-form-item label="会议时间：" style="width: 45%">
							<el-col :span="12">
								<el-date-picker type="datetime" :readonly="true" value-format="yyyy-MM-dd HH:mm" format="yyyy-MM-dd HH:mm" placeholder="选择日期" v-model="kcfk.hysj" style="width: 100%;"></el-date-picker>
							</el-col>
						</el-form-item>

						<el-form-item label="会审结果：" style="width: 45%">
							<el-input type="textarea" autosize v-model="kcfk.info" :readonly="true"></el-input>
						</el-form-item>

						<el-form-item label="附件上传：" style="width: 100%">
							<el-table :data="row" border highlight-current-row v-loading="listLoading"
									  style="width: 100%;">
								<el-table-column prop="fjmc" label="资料名称" width="200" align="center">
								</el-table-column>
								<el-table-column prop="fjhz" label="资料类型" width="200" align="center">
								</el-table-column>
								<el-table-column prop="scsj_str" label="上传时间" width="200" align="center">
								</el-table-column>
								<el-table-column prop="username" label="上传人" width="150" align="center">
								</el-table-column>
								<el-table-column label="操作" width="150" align="center">
									<template slot-scope="scope">
										<label title="下载"  @click="downloadFile_hsjlfj(scope.$index, scope.row,2)">
											<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
										</label>
									</template>
								</el-table-column>
							</el-table>
							<el-pagination background layout="total, prev, pager, next, sizes , jumper"
										   @current-change="handleCurrentChange" @size-change="handleSizeChange"
										   :page-sizes="[5, 10, 20, 50]"
										   :page-size="this.size" :total="this.total" style="float:right;">
							</el-pagination>
						</el-form-item>
					</el-form>
				</div>
			</el-card>

			<el-card style="margin-top: 10px">
				<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;"><span>预算资料</span></div>
				<div class="text item" >
					<el-table :data="Early_stage" style="width: 100%">
						<el-table-column property="fjmc" label="资料名" width="280"></el-table-column>
						<el-table-column property="fjhz" label="资料类型" width="180"></el-table-column>
						<el-table-column property="scsj" label="上传时间" width="200"></el-table-column>
						<el-table-column property="username" label="上传人" width="200"></el-table-column>
						<el-table-column label="操作" width="200">
							<template slot-scope="scope">
								<a href="#" @click="downloadFile_vue(scope.$index, scope.Early_stage,2)" >
									<label title="下载">
										<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
									</label>
								</a>
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

		<el-row>
			<el-col>
				<el-card class="box-card" style="margin-top: 10px">
					<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
						<span>预算成果</span>
					</div>

					<div class="text item">
						<el-row>
							<el-col :span="24">
								<el-card class="box-card" style="width: 98%;">
									<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;"><span>预算范围</span></div>
									<div class="text item">
										<el-col :span="6" style="text-align: center;font-size: 16px">
											<span><b>预算分类</b></span>
										</el-col>
										<el-col :span="3" style="text-align: center;font-size: 16px">
											<span><b>金额</b></span>
										</el-col>
										<el-col :span="9" style="text-align: center;font-size: 16px">
											<span><b>备注</b></span>
										</el-col>

										<el-form ref="form" :model="formData">
											<el-form-item
												v-for="(a, index) in formData.domains"
												:label="a.mc"
												label-width="200px"
												:key="index"
												:prop="'domains.' + index + '.ysje'"
											>
												<el-input
												  v-if="a.mc != null"
												  v-model="a.ysje"
												  @change="getZje(a.ysje)"
												  placeholder="预算金额"
												  class="cd"
												  id="n1"
												  :min = 0
												  type="number"
												></el-input>
												<el-input class="cd" v-if="a.mc != null" v-model="a.bz" placeholder="预算范围说明"></el-input>
											</el-form-item>
											<JEJS :value="zje"></JEJS>
										</el-form>
									</div>
								</el-card>
							</el-col>
						</el-row>
					</div>
				</el-card>
			</el-col>
			<el-col :span="24" style="text-align: center;margin-top: 0px;"></el-col>
		</el-row>
		<YSCG :zbid="Ysfrom.zbid" :xmid="xmid" :xmbh="Ysfrom.xmbh" :type_qjt="'1'"></YSCG>
		<el-row>
			<el-col :span="24">
				<el-card class="box-card">
					<div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;"><span>预算说明</span></div>
					<div class="text item"><el-input type="textarea" :rows="2" autosize placeholder="请输入内容" v-model="textarea"></el-input></div>
				</el-card>
			</el-col>
		</el-row>


		<el-dialog :visible.sync="dialogVisible" width="25%" :center="true" :close-on-click-modal="true">
			<el-row>
				<el-col >
					<el-card class="box-card">
						<div slot="header" class="clearfix"><span>预算项</span></div>
						<div class="text item">
							<el-checkbox-group v-model="checkList">
								<el-row v-model="YsxData" v-for="item in YsxData" :key="item.index" style="height: 25px">
									<el-checkbox :label="item.bh">{{item.mc}}</el-checkbox>
								</el-row>
							</el-checkbox-group>
						</div>
					</el-card>
				</el-col>
				<el-col :span="24" style="text-align: center;margin-top: 20px;margin-bottom: -15px">
					<el-button size="mini" type="info" @click="dialogVisible = false"><i class="iconfont icon-quxiao"></i>取消</el-button>
					<el-button size="mini" type="primary" @click="Selected"><i class="iconfont icon-queren"></i>确定</el-button>
				</el-col>
			</el-row>
		</el-dialog>
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
							<el-date-picker style="width: 100%" format="yyyy-MM-dd HH:mm"value-format="yyyy-MM-dd HH:mm" size="small" type="datetime" placeholder="选择日期" v-model="sqyqForm.jhyqjz_vue"></el-date-picker>
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
		<el-dialog title="拍摄" :visible.sync = "creamVisible_Delay" top="5vh" :close-on-click-modal="false">
			<DocCamera  v-bind:dirName ="dirName" v-bind:fileName ="fileName_Delay" v-on:listenToChildEvent="showMsg_delay"></DocCamera>
		</el-dialog>
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
	import DocCamera from '@/views/template/DocCamera.vue';
import XMJBXX from '@/views/template/xmjbxx.vue';
import hhldsx from '@/views/template/hhldsx.vue';
import xmfwpz from '@/views/template/xmfwpz.vue';
import wlqdpz from '@/views/template/wlqdpz.vue';
import dbpz from '@/views/template/dbpz.vue';
import hbpz from '@/views/template/hbpz.vue';
import wjjlbpz from '@/views/template/wjjlbpz.vue';
import util from '../../common/js/util';
import axios from 'axios';
import JEJS from '../../components/Jejs.vue';
import YSZLSC from '../../components/yswt/yszlUpload.vue';
import YSCG from '../../components/yswt/yscg.vue';
import YSZLXZ from '../../components/yswt/yszlDownload.vue';

import ElImage from "element-ui/packages/image/src/main";

import { updateQszt,ysbg,saveYsbz,setYsbz,getYsx,getYssm,getsjstrzwid,getEarly_stage, getTable, download, gethsjljbxx ,getYszyById,getYszyxmById,ToYsZpzr,ToBackward} from '../../api/Yswt';
import { stPreview,getHsjgLsit,stManage,initStcgTable,sqyqxx,yqbuttonshow,sqyq,importFile_delay,download_sjst,toDetial_delay,deleteDelayFj,stcgsc_delay,fsdx} from '../../api/sjst';


export default {
	data() {
		return {
			delayid:"",
			fileName_Delay:'ysbzdelay',
			fileList:[],
			slide1:[],
			creamVisible_Delay:false,
			delayfjData:[],
			total: 5,//表格数据总数量
			page: 1,//当前页码
			size: 5,//每页容量
			listLoading:'',
			row: [],
			kcfk : {
				hysj : '',
				info : '',
			},
			title:"",
			username:'',//会审记录参与人
			tabUrl:'',
			stcgDialogFormVisible:false,
			isyqshow:false,
			sqyqVisible:false,
			sqyqForm:{
				sqr:"",
				spr:"",
				yqwcsj:"",
				jhyqjz_vue:"",
				csyy:"",
				sprid:"",
			},
			type:{
				required: true,
			},
			sqyqrules:{
				jhyqjz_vue:[
					{ required: true, message: '该字段不可为空', trigger: 'blur' }
				],

				csyy:[
					{ required: true, message: '该字段不可为空', trigger: 'blur' }
				]},
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
			zje: 0,
			yshz: [],
			yshzFrom: {
				mc: '',
				je: '',
				bz: ''
			},
			xmxxsfzk:false,
			xmxxbody:false,
			downLoad:false,
			xmid:this.$route.query.xmid,//项目id
			activityId:'40003',
			checkList: [],//checkbox select
			dialogVisible : false,
			old_formData : [],//保存改变加载项前的预算数据
			ysfwfrom: [], //
			dialogTableVisible: false,
			textarea: '', //备注
			YsxData: [], //预算项的数据
			Ysfrom: this.$route.query.tow1, //上个页面传来的项目信息
			kcsj: '', //预约勘查时间
			zbid:'',//任务主表id
			tableData: [],
			Early_stage:[],//前期预算资料参考
			ysRwcb:[],//查询出来的任务从表数据
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
	methods: {
		//跳转至项目全景
		goxmqj(){
			this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})

		},
		//跳转至项目资料
		goxmzl(){
			this.$router.push({path:'/lookZl', query:{ xmid:this.xmid }})
		},
		//延期--------------------
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
		delayps(){
			this.creamVisible_Delay = true;
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
		showMsg_delay:function(data){//存储子组件保存的图片名称
			let name = data;
			let path = 'upload/'+this.dirName+'/'+data;
			this.submitUpload_delay(name, path);

			//接口预留位置  data为图片名称 将图片名称保存到数据库
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
		//------------------------------
		yqbuttonshow_vue(){
			let params={
				activityId:this.activityId,
				xmid:this.xmid
			}
			yqbuttonshow(params).then((res)=>{
				this.isyqshow = res.data;
			})
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
		handleCurrentChange(val) {
			this.page = val;
			this.gethsjg();
		},
		handleSizeChange(val) {
			this.size = val;
			this.page = 1;
			this.gethsjg();
		},
		//会审结果资料附件查询
		gethsjg() {
			let params = Object.assign({xmid: this.xmid, page: this.page, size: this.size});
			this.listLoading = true;
			getHsjgLsit(params).then((res) => {
				if(res.data!=null&&res.data!=undefined){
					if(res.data.total>0&&res.data.list.length<=0){
						this.page=this.page-1
						this.gethsjg();
					}
					this.total = res.data.total;
					this.row = res.data.list;
					this.listLoading = false;
				}

			});
		},
		//初始化参数
		init(){
			let JSONstr = sessionStorage.getItem("user");
			let JSON = eval("("+JSONstr+")");
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
		handleClose(done) {
			this.$confirm('确认关闭？')
					.then(_ => {
						done();
					})
					.catch(_ => {});
		},
		updateQszt(){
			let params = {
				xmid : this.xmid,
				activityId:'40003'
			}
			updateQszt(params).then(res =>{

			});
		},
		//保存预算表
		saveForm() {
				let list = this.formData.domains;
				let params1 = this.ysbz;
				let i=1;
				list.forEach((a, index) => {
					params1['yslx' + index] = a.ysfl;
					params1['ysje' + index] = a.ysje;
					params1['bz' + index] = a.bz;
					i=index;
				});
				params1.i = i;
				params1.zje = this.zje;
				params1.ysbzbz = this.textarea;
				params1.xmid = this.Ysfrom.id;
				params1.zbid = this.Ysfrom.zbid;
				let json = JSON.stringify(params1);
				let params = {
					json: json
				};
				//提交预算金额
			this.sumbitLoading=true;
				saveYsbz(params).then(res => {
					this.sumbitLoading=false;
					if (res!=null){
						this.$message({
							type: 'success',
							message: '保存已成功!'
						});
					}
				});

				this.tabUrl = '/Backlog';
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');

		},
		//提交预算表
		submitForm() {
			this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'warning'
			}).then(() => {
				this.$confirm('请您再次确认是否递交审核,一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '二次确认选项', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {

					let list = this.formData.domains;
					let params1 = this.ysbz;
					let i=1;
					list.forEach((a, index) => {
						params1['yslx' + index] = a.ysfl;
						params1['ysje' + index] = a.ysje;
						params1['bz' + index] = a.bz;
						i=index;
					});
					params1.i = i;
					params1.zje = this.zje;

					//params1.ysrwb = this.Ysfrom.id;
					params1.ysbzbz = this.textarea;
					params1.xmid = this.Ysfrom.id;
					params1.zbid = this.Ysfrom.zbid;
					params1.procinstid=this.Ysfrom.procinstid;
					let json = JSON.stringify(params1);
					let params = {
						json: json
					};
					//提交预算金额

					setYsbz(params).then(res => {
						if (res == "ok"){
							this.$message({
								type: 'success',
								message: '编制成功!'
							});
						}

					});
					//this.$router.push({path: '/Backlog',query:{}})
					this.$routerTab.close();
					this.$routerTab.refresh('/Backlog');//刷新我的代办
					this.$routerTab.refresh('/MyWork');//刷新我的工作

				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消递交'
					});
				});



				
			}).catch(() => {
			  this.$message({
				type: 'info',
				message: '提交已取消!'
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
			//回显已经保存的预算数据(预算变更)
			this.rwcbsj();
			if (this.Ysfrom.yszt=='2' ){
				//this.rwcbsj();
			}

		},
		rwcbsj(){//查询任务从表中数据
			let params = {
				zbid: this.Ysfrom.zbid
			};
			ysbg(params).then(res => {
				this.ysRwcb = res.ysrwcb;
				for (let i = 0; i < this.ysRwcb.length; i++) {
					this.checkList[i]=this.ysRwcb[i].ysfl;
					this.formData.domains.push(this.ysRwcb[i]);
				}
				this.getZje();
			});
		},
		openYsxx() {
			this.dialogVisible = true;
		},
		//初始化项目基本信息
		getParams() {
			this.getYssm();//获取预算说明（预算人员备注）
		},
		//跳转页面Backlog
		toXckcrwcj() {
			//this.tabUrl = '';
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
		getYssm(){
			let params = {
				zbid: this.Ysfrom.zbid,
			};
			getYssm(params).then(res => {
				if (typeof(res.ysRwzb[0].ysrybz) != "undefined") {
					this.textarea = res.ysRwzb[0].ysrybz;
				}
			});

		},
		//选中预算项动态渲染
		Selected() {
			//this.old_formData = this.formData;
			//let old = this.formData.domains;
			let old = this.ysRwcb;
			this.formData = { domains: [{}] };

			// this.yshz = row;
			let list = this.checkList;//已经选中的预算项
			let ysxx = this.YsxData;//所有的预算项
			let allxz = this.ysRwcb;//已经保存到数据库的预算项
			for (let i = 0; i < list.length; i++) {
				for(let j = 0; j < ysxx.length; j++){
						if(ysxx[j].bh == list[i]){
							this.formData.domains.push({
								bz: '',
								ysje: '',
								ysfl: ysxx[j].bh,
								mc: ysxx[j].mc
							});
						}

				}
			}
			let formData = this.formData.domains;
			for(let x=0;x<formData.length;x++){
				for(let k=0;k<old.length;k++){
					if(formData[x].ysfl == old[k].ysfl){
						formData[x].ysje = old[k].ysje;
						formData[x].bz = old[k].bz;

					}
				}
			}
			this.formData.domains = formData;
			this.dialogVisible = false;
			this.getZje();
		},
		//得到总金额
		getZje(res) {
			let data = this.formData.domains;
			this.zje = 0;
/*			var totalCount=0;
			if (res!=null){
				totalCount+=res;
			}
			this.zje=totalCount;*/
			data.forEach((a, index) => {
				if (a.ysje != '' && index != 0) {
					let num = Number(a.ysje);
					if(  isNaN(num)){
						num = 0;
					}
					if (this.zje == 0) {
						this.zje = num;
					} else {
						this.zje = num + Number(this.zje);
					}
				}
				this.zje = parseFloat(this.zje).toFixed(2);
			});

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

		downloadFile_hsjlfj(index,row,flag){debugger
			row=this.row[index];
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
				}
					reader.readAsDataURL(res)
				}else{
					const content = res;
					const blob = new Blob([content]);
					const fileName = row.fjmc;

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
		//下载
		downloadFile_vue(index,Early_stage,flag){
			Early_stage=this.Early_stage[index];
			let formData = new FormData();
			formData.append("path",Early_stage.fjlj);

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
		gethsjljbxx(){
			let params = {
				xmid:this.xmid,
			}

			gethsjljbxx(params).then((res) => {
				if(res.gethsjljbxx.length>0){
					this.username = res.gethsjljbxx[0].CYRY;
					this.kcfk.hysj = res.gethsjljbxx[0].CJSJ_STR;
					this.kcfk.info = res.gethsjljbxx[0].XMJLYJ;
				}
			});

		},
		openBody(xs) {
			switch (xs) {
				case '1':
					this.xmxxbody = true;
					this.xmxxsfzk = true;
					break;

			}
		},
		closeBody(xs) {
			switch (xs) {
				case '1':
					this.xmxxbody = false;
					this.xmxxsfzk = false;
					break;
			}
		},
	},

	//页面启动调用的方法
	mounted() {
		this.init();
		this.getEarly_stage();//初始化前期预算资料
		this.gethsjg();//会审结果资料附件查询
		this.gethsjljbxx();//会审结果基本信息
		this.getParams();
		this.ysxx();
		this.getsjstrzwid();
		this.updateQszt();
		this.yqbuttonshow_vue();
	},

	components: {
		YSZLXZ,
		YSZLSC,
		"YSCG": YSCG,
		XMJBXX,
		JEJS,
		hhldsx,
		'xmfwpz': xmfwpz,
		'wlqdpz': wlqdpz,
		'dbpz': dbpz,
		'hbpz': hbpz,
		'wjjlbpz': wjjlbpz,
		'DocCamera':DocCamera,
	}
};
</script>

<style>
	.div_title {
		margin:-18px -20px;
		padding:10px 20px;
		background: #f5f7fa;
		height: 18px;
	}
	.span_cz {
		display: inline-block;
		float: right;
		cursor: pointer;
		color: #666;
	}
	.cd {
		width: 25%;
		padding-left: 50px;
	}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
	-webkit-appearance: none;
}
input[type="number"] {
	-moz-appearance: textfield;
}
</style>
